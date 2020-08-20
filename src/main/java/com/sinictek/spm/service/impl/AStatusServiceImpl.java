package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.AStatus;
import com.sinictek.spm.dao.AStatusMapper;
import com.sinictek.spm.service.AStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * aoi-设备状态 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
@Service
public class AStatusServiceImpl extends ServiceImpl<AStatusMapper, AStatus> implements AStatusService {


    @Autowired
    AStatusMapper aStatusMapper;

    @Override
    public List<AStatus> getAllStatusWithLineNoLimt_AOI() {
        return aStatusMapper.getAllStatusWithLineNoLimt_AOI();
    }
}
