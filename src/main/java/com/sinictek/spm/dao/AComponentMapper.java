package com.sinictek.spm.dao;

import com.sinictek.spm.model.AComponent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sinictek.spm.model.SPad;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
public interface AComponentMapper extends BaseMapper<AComponent> {

    @Select({
            "<script> SELECT * "+//pad.padId padId,pad.padInspectResult padInspectResult,pad.defectTypeName defectTypeName," +
                    //"pad.height height,pad.area area,pad.volume volume,pad.offsetx offsetx,pad.offsety offsety,pad.arrayId arrayId,pad.componentId componentId  " +
                    " FROM ${componentTableName} pad <where> pad.pcbIdLine= #{pcbIdLine} and pad.aoiMode=#{aoiType}" +
                    "  <if test=\"defectType !=''\"> AND pad.result=1  AND pad.defectType = #{defectType}   </if> " +
                    " </where>" +
                    "</script>"
    })
    public List<AComponent> getComponentListWithPCbidLineDao(@Param("componentTableName") String componentTableName,
                                                 @Param("pcbIdLine") String pcbIdLine,
                                                 @Param("defectType") String defectType,
                                                             @Param("aoiType")String aoiType);

    @Select({
            /*"<script> SELECT pad.pad2dImage pad2dImage,pad.remark remark,pad.pad2dImageBase64 pad2dImageBase64,pad.pad3dImageBase64 pad3dImageBase64 FROM ${padTableName} pad  " +
                    "<where>  pad.pcbidLine=#{pcbidLine} and pad.padId=#{padId}   " +
                    "</where>" +
            " </script>"*/
            "<script> SELECT * FROM ${componentTableName} pad  " +
                    "<where>  pad.pcbIdLine=#{pcbIdLine} and pad.id=#{comID} and pad.aoiMode=#{aoiType}  " +
                    "</where>" +
                    " </script>"
    })
    public AComponent getComponentWithPCbidLineDao(@Param("componentTableName") String componentTableName,@Param("pcbIdLine") String pcbIdLine,@Param("comID")String comID,@Param("aoiType")String aoiType);


}
