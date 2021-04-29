package com.sinictek.spm.model.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @Author sinictek-pd
 * @Date 2021/3/19 18:17
 * @Version 1.0
 */
public class PreciseModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    /**
     * @param collection 存放的是所有的库的列表，这里代表master04091,master04092
     *                   master04091(2020~2024),master04092(2025~2029)
     * @return 将数据写入的哪个库
     */
    @Override
    public String doSharding(Collection<String> collection,
                             PreciseShardingValue<String> pre) {
        try {
            //配置的分库分片的sharding-column对应的值,也就是具体时间
            String str=pre.getValue();
            if (str.isEmpty()) {
                throw new UnsupportedOperationException("pre is null");
            }
            //得到月
            String value= StringUtils.substring(str,4,6);
            //each为每个库的名字
            for (String each:collection) {
                //如果此时时间为21000101 则数据路由至 job/line/setting专用库
                if(com.alibaba.druid.util.StringUtils.equals(str,"21000101")){
                    return each;
                }
                //以一个月为一个库,例如：20210301
                if(each.endsWith((Integer.parseInt(value))%12+"")){
                    //扔到后缀是database_hou_zhui+1的库,判断当前这个库是否符合我条件，
                    return each;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}