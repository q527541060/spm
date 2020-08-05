package com.sinictek.spm.service;

import com.sinictek.spm.model.SStatus;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * spi-设备状态 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SStatusService extends IService<SStatus> {

    public List<SStatus> getAllStatusWithLineNoLimt();
}
