package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.SPad;
import com.sinictek.spm.dao.SPadMapper;
import com.sinictek.spm.service.SPadService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 焊盘 服务实现类
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
@Service
public class SPadServiceImpl extends ServiceImpl<SPadMapper, SPad> implements SPadService {


    @Autowired
    SPadMapper sPadMapper;

    @Override
    public List<SPad> getPadListWithPCbidLineService(String padTableName,String pcbIdLine,String defectTypeCode) {
        return sPadMapper.getPadListWithPCbidLineDao(padTableName,pcbIdLine,defectTypeCode);
    }

    @Override
    public SPad getPadWithPCbidLineService(String padTableName, String pcbIdLine, String padId) {
        return sPadMapper.getPadWithPCbidLineDao(padTableName,pcbIdLine,padId);
    }

    @Override
    public void deletePadTableWithName(String padTableName) {
         sPadMapper.deletePadTableWithName(padTableName);
    }

    @Override
    public void truncateDataBaseWithBaseName(String baseName) {
        sPadMapper.truncateDataBaseWithBaseName(baseName);
    }

}
