package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.dao.SUserMapper;
import com.sinictek.spm.service.SUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Service

@CacheConfig(cacheNames = "spmUserCache")
@Slf4j
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements SUserService {

    @Autowired
    SUserMapper sUserMapper;

    @Override
    @Cacheable(unless = "#userSize > #result.size()")
    public List<SUser> getTestAllUser(Integer userSize) {
        try {
            //模拟耗时操作
            //log.info("queryUserstart---");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<SUser> lstUser =  sUserMapper.selectList(null);
      // ConstParam.LIST_USER_SIZE = lstUser.size();
        log.info("queryUserEnd---");
        return lstUser;
    }

    @Override
    @CachePut(key = "'getAllSUser'")
    public int addTestAllUser(SUser sUser) {
        int iTmp = sUserMapper.insert(sUser);
        return iTmp;
    }
}
