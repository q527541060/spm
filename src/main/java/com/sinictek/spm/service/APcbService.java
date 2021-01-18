package com.sinictek.spm.service;

import com.sinictek.spm.model.APcb;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
public interface APcbService extends IService<APcb> {
    public List<APcb> getPcbListWithALLLineNoAoiMode( String inspectStarttime ,  String inspectEndtime);
    public List<APcb> getPcbListWithALLLine( String inspectStarttime ,  String inspectEndtime,String aoiMode);
    public APcb getPcbListWithALLLineByDateNoGroup( String inspectStarttime ,String inspectEndtime,String aoiMode);
    public List<APcb> getPcbListWithDateAndLineNoPcbResult( String lineNo,
                                                            String inspectStarttime,
                                                            String inspectEndtime,
                                                            String pcbResult,
                                                            String aoiType);
}
