package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.dao.SStatusMapper;
import com.sinictek.spm.service.SStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spi-设备状态 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Service
public class SStatusServiceImpl extends ServiceImpl<SStatusMapper, SStatus> implements SStatusService {

    @Autowired
    SStatusMapper sStatusMapper;

    @Override
    public List<SStatus> getAllStatusWithLineNoLimt(){
        return  sStatusMapper.getAllStatusWithLineNoLimt();
    }

    @Override
    public SStatus getStatusWithLineNo(String lineNo) {
        return sStatusMapper.getStatusWithLineNo(lineNo);
    }


}
