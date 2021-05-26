package com.sinictek.spm.dao;

import com.sinictek.spm.model.SPad;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    /***
     *  清空数据库中所有表数据 保留库结构
     * @param baseName
     */
   /* @Select({
            "<script> TRUNCATE TABLE ${baseName}.`a_component_1`;" + "TRUNCATE TABLE ${baseName}.`a_component_3`;" + "TRUNCATE TABLE ${baseName}.a_component_4;" + "TRUNCATE TABLE ${baseName}.a_component_5;" +
                    "TRUNCATE TABLE ${baseName}.a_component_6;" + "TRUNCATE TABLE ${baseName}.a_component_7;" + "TRUNCATE TABLE ${baseName}.a_component_8;" + "TRUNCATE TABLE ${baseName}.a_component_9;" +
                    "TRUNCATE TABLE ${baseName}.a_component_10;" + "TRUNCATE TABLE ${baseName}.a_component_11;" + "" + "TRUNCATE TABLE ${baseName}.a_component_12;" + "TRUNCATE TABLE ${baseName}.a_component_13;" +
                    "TRUNCATE TABLE ${baseName}.a_component_14;" + "TRUNCATE TABLE ${baseName}.a_component_15;" + "TRUNCATE TABLE ${baseName}.a_component_16;" + "TRUNCATE TABLE ${baseName}.a_component_17;" +
                    "TRUNCATE TABLE ${baseName}.a_component_18;" + "TRUNCATE TABLE ${baseName}.a_component_19;" + "TRUNCATE TABLE ${baseName}.a_component_20;" + "TRUNCATE TABLE ${baseName}.a_component_21;" +
                    "TRUNCATE TABLE ${baseName}.a_component_22;" + "TRUNCATE TABLE ${baseName}.a_component_23;" + "TRUNCATE TABLE ${baseName}.a_component_24;" + "TRUNCATE TABLE ${baseName}.a_component_25;" +
                    "TRUNCATE TABLE ${baseName}.a_component_26;" + "TRUNCATE TABLE ${baseName}.a_component_27;" + "TRUNCATE TABLE ${baseName}.a_component_28;" + "TRUNCATE TABLE ${baseName}.a_component_29;" +
                    "TRUNCATE TABLE ${baseName}.a_component_30;" + "TRUNCATE TABLE ${baseName}.a_component_31;" + "" + "TRUNCATE TABLE ${baseName}.a_fov;" + "TRUNCATE TABLE ${baseName}.a_job;" + "TRUNCATE TABLE ${baseName}.a_line;" +
                    "TRUNCATE TABLE ${baseName}.a_pcb;" + "TRUNCATE TABLE ${baseName}.a_status;" + "TRUNCATE TABLE ${baseName}.a_window;" + "TRUNCATE TABLE ${baseName}.s_component;" + "TRUNCATE TABLE ${baseName}.s_job;" +
                    "TRUNCATE TABLE ${baseName}.s_line;" + "TRUNCATE TABLE ${baseName}.s_pad_1;" + "TRUNCATE TABLE ${baseName}.s_pad_3;" + "TRUNCATE TABLE ${baseName}.s_pad_4;" + "TRUNCATE TABLE ${baseName}.s_pad_5;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_6;" + "TRUNCATE TABLE ${baseName}.s_pad_7;" + "TRUNCATE TABLE ${baseName}.s_pad_8;" + "TRUNCATE TABLE ${baseName}.s_pad_9;" + "TRUNCATE TABLE ${baseName}.s_pad_10;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_11;" + "" + "TRUNCATE TABLE ${baseName}.s_pad_12;" + "TRUNCATE TABLE ${baseName}.s_pad_13;" + "TRUNCATE TABLE ${baseName}.s_pad_14;" + "TRUNCATE TABLE ${baseName}.s_pad_15;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_16;" + "TRUNCATE TABLE ${baseName}.s_pad_17;" + "TRUNCATE TABLE ${baseName}.s_pad_18;" + "TRUNCATE TABLE ${baseName}.s_pad_19;" + "TRUNCATE TABLE ${baseName}.s_pad_20;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_21;" + "" + "TRUNCATE TABLE ${baseName}.s_pad_22;" + "TRUNCATE TABLE ${baseName}.s_pad_23;" + "TRUNCATE TABLE ${baseName}.s_pad_24;" + "TRUNCATE TABLE ${baseName}.s_pad_25;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_26;" + "TRUNCATE TABLE ${baseName}.s_pad_27;" + "TRUNCATE TABLE ${baseName}.s_pad_28;" + "TRUNCATE TABLE ${baseName}.s_pad_29;" + "TRUNCATE TABLE ${baseName}.s_pad_30;" +
                    "TRUNCATE TABLE ${baseName}.s_pad_31;" + "" + "TRUNCATE TABLE ${baseName}.s_pcb;" + "" + "TRUNCATE TABLE ${baseName}.s_realTimeAutoRefresh;" + "TRUNCATE TABLE ${baseName}.s_realTimeConfig;" +
                    "TRUNCATE TABLE ${baseName}.s_realTimeSituation;" + "TRUNCATE TABLE ${baseName}.s_status;" + "</script>"
    })
    */
    @Update({"<script> TRUNCATE TABLE ${baseName};</script> "})
    public void truncateDataBaseWithBaseName(@Param("baseName")String baseName);
}
