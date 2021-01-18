package com.sinictek.spm.service;

import com.sinictek.spm.model.AComponent;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
public interface AComponentService extends IService<AComponent> {
    public List<AComponent> getComponentListWithPCbidLineDao( String componentTableName,
                                                              String pcbIdLine,
                                                              String defectType,
                                                              String aoiType);
    public AComponent getComponentWithPCbidLineDao( String componentTableName, String pcbIdLine,String comID,String aoiType);

}
