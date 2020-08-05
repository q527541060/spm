package com.sinictek.spm.service;

import com.sinictek.spm.model.SPcb;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * spi-pcb表 服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SPcbService extends IService<SPcb> {

    public List<SPcb> getPcbListWithALLLine(String startTime,String endTime);
    public SPcb getPcbListWithLineNo(String lineNo, String inspectStarttime, String inspectEndtime);
    public SPcb getPcbListProductCPKWithLineNo(String lineNo,String inspectStarttime,String inspectEndtime);
    public SPcb getPcbListWithALLLineByDateNoGroup(String inspectStarttime,String inspectEndtime);
    public List<SPcb> getPcbListWithDateAndLineNoPcbResult( String lineNo,String inspectStarttime,
                                                            String inspectEndtime,
                                                            String pcbResult);
}
