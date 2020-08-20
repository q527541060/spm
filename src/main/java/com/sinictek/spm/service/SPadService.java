package com.sinictek.spm.service;

import com.sinictek.spm.model.SPad;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 焊盘 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
public interface SPadService extends IService<SPad> {
    public List<SPad> getPadListWithPCbidLineService(String padTableName,String pcbIdLine,String defectTypeCode);
    public SPad getPadWithPCbidLineService( String padTableName, String pcbIdLine,String padId);


}
