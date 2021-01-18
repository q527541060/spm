package com.sinictek.spm.dao;

import com.sinictek.spm.model.SPad;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 焊盘 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
public interface SPadMapper extends BaseMapper<SPad> {


    @Select({
            "<script> SELECT * "+//pad.padId padId,pad.padInspectResult padInspectResult,pad.defectTypeName defectTypeName," +
                    //"pad.height height,pad.area area,pad.volume volume,pad.offsetx offsetx,pad.offsety offsety,pad.arrayId arrayId,pad.componentId componentId  " +
                    " FROM ${padTableName} pad <where> pad.pcbidLine= #{pcbIdLine}" +
                    "  <if test=\"defectTypeCode !=''\"> AND pad.padInspectResult=1  AND pad.defectTypeCode = #{defectTypeCode}   </if> " +
                    " </where>" +
                    "</script>"
    })
    public List<SPad> getPadListWithPCbidLineDao(@Param("padTableName") String padTableName,
                                                 @Param("pcbIdLine") String pcbIdLine,
                                                 @Param("defectTypeCode") String defectTypeCode);

    @Select({
            /*"<script> SELECT pad.pad2dImage pad2dImage,pad.remark remark,pad.pad2dImageBase64 pad2dImageBase64,pad.pad3dImageBase64 pad3dImageBase64 FROM ${padTableName} pad  " +
                    "<where>  pad.pcbidLine=#{pcbidLine} and pad.padId=#{padId}   " +
                    "</where>" +
            " </script>"*/
            "<script> SELECT pad.pad2dImage pad2dImage,pad.pad2dImageBase64 pad2dImageBase64,pad.pad3dImageBase64 pad3dImageBase64,pad.remark remark FROM ${padTableName} pad  " +
                    "<where>  pad.pcbidLine=#{pcbidLine} and pad.padId=#{padId}   " +
                    "</where>" +
                    " </script>"
    })
    public SPad getPadWithPCbidLineDao(@Param("padTableName") String padTableName,@Param("pcbidLine") String pcbIdLine,@Param("padId")String padId);

    @Select({"<script> DROP TABLE IF EXISTS ${padTableName}</script>"})
    public void deletePadTableWithName(@Param("padTableName") String padTableName);

}
