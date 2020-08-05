package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.dao.SPcbMapper;
import com.sinictek.spm.service.SPcbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spi-pcb表 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Service
public class SPcbServiceImpl extends ServiceImpl<SPcbMapper, SPcb> implements SPcbService {

    @Autowired
    public SPcbMapper sPcbMapper;

    @Override
    public List<SPcb> getPcbListWithALLLine(String startTime,String endTime) {
        return sPcbMapper.getPcbListWithALLLine(startTime,endTime);
    }

    @Override
    public SPcb getPcbListWithLineNo(String lineNo, String inspectStarttime, String inspectEndtime) {
        return sPcbMapper.getPcbListWithLineNo(lineNo,inspectStarttime,inspectEndtime);
    }

    @Override
    public SPcb getPcbListProductCPKWithLineNo(String lineNo, String inspectStarttime, String inspectEndtime) {
        return sPcbMapper.getPcbListProductCPKWithLineNo(lineNo,inspectStarttime,inspectEndtime);
    }

    @Override
    public SPcb getPcbListWithALLLineByDateNoGroup(String inspectStarttime, String inspectEndtime) {
        return sPcbMapper.getPcbListWithALLLineByDateNoGroup(inspectStarttime,inspectEndtime);
    }

    @Override
    public List<SPcb> getPcbListWithDateAndLineNoPcbResult(String lineNo, String inspectStarttime, String inspectEndtime, String pcbResult) {
        return sPcbMapper.getPcbListWithDateAndLineNoPcbResult(lineNo,inspectStarttime,inspectEndtime,pcbResult);
    }
}
