package com.sinictek.spm.service.impl;

import com.sinictek.spm.model.AComponent;
import com.sinictek.spm.dao.AComponentMapper;
import com.sinictek.spm.service.AComponentService;
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
public class AComponentServiceImpl extends ServiceImpl<AComponentMapper, AComponent> implements AComponentService {

    @Autowired
    AComponentMapper aComponentMapper;
    @Override
    public List<AComponent> getComponentListWithPCbidLineDao(String componentTableName, String pcbIdLine, String defectType, String aoiType) {
        return aComponentMapper.getComponentListWithPCbidLineDao(componentTableName,pcbIdLine,defectType,aoiType);
    }

    @Override
    public AComponent getComponentWithPCbidLineDao(String componentTableName, String pcbIdLine, String comID,String aoiType) {
        return aComponentMapper.getComponentWithPCbidLineDao(componentTableName,pcbIdLine,comID,aoiType);
    }
}
