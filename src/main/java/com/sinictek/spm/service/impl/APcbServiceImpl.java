package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.APcb;
import com.sinictek.spm.dao.APcbMapper;
import com.sinictek.spm.service.APcbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
@Service
public class APcbServiceImpl extends ServiceImpl<APcbMapper, APcb> implements APcbService {

    @Autowired
    APcbMapper aPcbMapper;

    @Override
    public List<APcb> getPcbListWithALLLineNoAoiMode(String inspectStarttime, String inspectEndtime,String aoiMode) {
        return aPcbMapper.getPcbListWithALLLineNoAoiMode(inspectStarttime,inspectEndtime,aoiMode);
    }

    @Override
    public List<APcb> getPcbListWithALLLine(String inspectStarttime, String inspectEndtime,String aoiMode) {
        return aPcbMapper.getPcbListWithALLLine(inspectStarttime,inspectEndtime,aoiMode);
    }

    @Override
    public APcb getPcbListWithALLLineByDateNoGroup(String inspectStarttime, String inspectEndtime,String aoiMode) {
        return aPcbMapper.getPcbListWithALLLineByDateNoGroup(inspectStarttime,inspectEndtime,aoiMode);
    }

    @Override
    public List<APcb> getPcbListWithDateAndLineNoPcbResult(String lineNo, String inspectStarttime, String inspectEndtime, String pcbResult, String aoiType) {
        return aPcbMapper.getPcbListWithDateAndLineNoPcbResult(lineNo,inspectStarttime,inspectEndtime,pcbResult,aoiType);
    }
}
