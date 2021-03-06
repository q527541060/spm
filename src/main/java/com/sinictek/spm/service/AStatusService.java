package com.sinictek.spm.service;

import com.sinictek.spm.model.AStatus;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * aoi-设备状态 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
public interface AStatusService extends IService<AStatus> {


    public List<AStatus> getAllStatusWithLineNoLimt_AOI();
    public List<AStatus> getAllStatusWithLineNoLimt(String lane , String aoiType);
    public AStatus getStatusWithLineNo( String lineNo,String aoiType);

}
