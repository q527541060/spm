package com.sinictek.spm.model.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @Author sinictek-pd
 * @Date 2021/3/19 18:29
 * @Version 1.0
 */
public class PreciseModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection,
                             PreciseShardingValue<String> prec) {
        //配置的分片的sharding-column对应的值
        String timeValue = prec.getValue();
        //判断timeValue是否为空
        if(StringUtils.isBlank(timeValue)){
            throw new UnsupportedOperationException("prec is null");
        }
        //取最后两位日期,20210319
        String value= StringUtils.substring(timeValue,6,8);
        for (String each:collection) {
            //如果此时时间为21000101 则数据路由至 job/line/setting专用库
            if(com.alibaba.druid.util.StringUtils.equals(timeValue,"21000101")){
                return each;
            }
            if(     StringUtils.equals(each,"s_pcb") ||
                    StringUtils.equals(each,"s_status") ||
                    StringUtils.equals(each,"s_component") ||
                    StringUtils.equals(each,"s_realTimeConfig") ||
                    StringUtils.equals(each,"s_realTimeAutoRefresh") ||
                    StringUtils.equals(each,"s_realTimeSituation") ||
                    StringUtils.equals(each,"a_pcb") ||
                    StringUtils.equals(each,"a_fov") ||
                    StringUtils.equals(each,"a_window") ||
                    StringUtils.equals(each,"a_status")
            ){
                return each;
            }


            int c=Integer.parseInt(value);
            //循环每个库，看哪个库与当前条件匹配
            if(each.endsWith(Integer.toString(c))){
                return each;
            }
        }
        return null;
    }
}