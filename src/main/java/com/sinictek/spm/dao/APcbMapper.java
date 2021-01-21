package com.sinictek.spm.dao;

import com.sinictek.spm.model.APcb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface APcbMapper extends BaseMapper<APcb> {

    @Select({"SELECT " +
            "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100  goodPcbYeildAoi  " +
            "FROM  " +
            "a_pcb pcb WHERE  pcb.inspectStarttime >= #{inspectStarttime} and pcb.inspectEndtime< #{inspectEndtime}   " +
            "   AND pcb.aoiMode=#{aoiMode}   " +
            "  GROUP BY pcb.lineNo"
    })
    public List<APcb> getPcbListWithALLLineNoAoiMode(@Param("inspectStarttime") String inspectStarttime , @Param("inspectEndtime") String inspectEndtime,@Param("aoiMode")String aoiMode);


    @Select({"SELECT " +
            "COUNT(pcb.id) totalAoi," +
            "SUM(pcb.inspectResult = 1)/COUNT(pcb.id)*100  ngPcbYeildAoi," +
            "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100  goodPcbYeildAoi," +
            "SUM(pcb.inspectResult= 2 )/COUNT(pcb.id)*100  passPcbYeildAoi," +
            "SUM(pcb.inspectResult = 1)  ngPcbCountAoi,"+
            "SUM(pcb.inspectResult = 0)  goodPcbCountAoi,"+
            "SUM(pcb.inspectResult = 2)  passPcbCountAoi,"+

            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':1','')) )/2)) ngarrayCountAoi,"+
            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':2','')) )/2)) passarrayCountAoi,"+
            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':0','')) )/2)) goodarrayCountAoi,"+

            "SUM(pcb.ngComponentCount)/SUM(pcb.totalComponentCount)*100  ngcomponentYeildAoi," +
            "SUM(pcb.goodComponentCount)/SUM(pcb.totalComponentCount)*100  goodcomponentYeildAoi," +
            "SUM(pcb.passComponentCount)/SUM(pcb.totalComponentCount)*100  passcomponentYeildAoi," +
            "SUM(pcb.ngComponentCount)  ngComponentCount,"+
            "SUM(pcb.passComponentCount) passComponentCount," +
            "SUM(pcb.goodComponentCount) goodComponentCount,"+
            "SUM(pcb.totalComponentCount) totalComponentCount,"+

            "SUM(pcb.customCount) customCount," +
            "SUM(pcb.defaultCount) defaultCount," +
            "SUM(pcb.missingCount) missingCount," +
            "SUM(pcb.shiftXCount) shiftXCount," +
            "SUM(pcb.shiftYCount) shiftYCount," +
            "SUM(pcb.rotationCount) rotationCount," +
            "SUM(pcb.bridgeCount) bridgeCount," +
            "SUM(pcb.voidCount) voidCount," +
            "SUM(pcb.tombStoneCount) tombStoneCount," +
            "SUM(pcb.pinLiftCount) pinLiftCount," +
            "SUM(pcb.solderBeadCount) solderBeadCount," +
            "SUM(pcb.smearCount) smearCount," +
            "SUM(pcb.polarityCount) polarityCount," +
            "SUM(pcb.reverseCount) reverseCount," +
            "SUM(pcb.wrongPartCount) wrongPartCount," +
            "SUM(pcb.noSolderCount) noSolderCount," +

            "SUM(pcb.copperExposureCount) copperExposureCount," +
            "SUM(pcb.excessSolderCount) excessSolderCount," +
            "SUM(pcb.solderingCount) solderingCount," +
            "SUM(pcb.excessPartsCount) excessPartsCount," +
            "SUM(pcb.barcodeCount) barcodeCount," +
            "SUM(pcb.eNM_Defect_Type_ENMMaxLengthCount) eNM_Defect_Type_ENMMaxLengthCount," +

            "pcb.lineNo lineNo, Min(pcb.inspectStarttime) inspectStarttime,Max(pcb.inspectEndtime) inspectEndtime,  " +
            "ROUND(AVG(pcb.hCpk),3) AS hCpk ,ROUND(AVG(pcb.aCpk),3) AS aCpk,ROUND(AVG(pcb.VCPK),3) AS vcpk  "+

            "FROM  " +
            "a_pcb pcb WHERE  pcb.aoiMode=#{aoiMode} and pcb.inspectStarttime >= #{inspectStarttime} and pcb.inspectEndtime< #{inspectEndtime}   " +
            "  GROUP BY pcb.lineNo"
    })
    public List<APcb> getPcbListWithALLLine(@Param("inspectStarttime") String inspectStarttime , @Param("inspectEndtime") String inspectEndtime,@Param("aoiMode") String aoiMode);

    @Select({"SELECT " +
            "COUNT(pcb.id) totalAoi," +
            "SUM(pcb.inspectResult = 1)/COUNT(pcb.id)*100  ngPcbYeildAoi," +
            "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100 goodPcbYeildAoi," +
            "SUM(pcb.inspectResult= 2 )/COUNT(pcb.id)*100 passPcbYeildAoi," +
            "SUM(pcb.inspectResult = 1)  ngPcbCountAoi,"+
            "SUM(pcb.inspectResult = 0)  goodPcbCountAoi,"+
            "SUM(pcb.inspectResult = 2)  passPcbCountAoi,"+

            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':1','')) )/2)) ngarrayCountAoi,"+
            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':2','')) )/2)) passarrayCountAoi,"+
            "SUM(FLOOR((LENGTH(pcb.arrayInfo)-LENGTH(REPLACE(pcb.arrayInfo,':0','')) )/2)) goodarrayCountAoi,"+

            "SUM(pcb.ngComponentCount)/SUM(pcb.totalComponentCount)*100  ngcomponentYeildAoi," +
            "SUM(pcb.goodComponentCount)/SUM(pcb.totalComponentCount)*100  goodcomponentYeildAoi," +
            "SUM(pcb.passComponentCount)/SUM(pcb.totalComponentCount)*100  passcomponentYeildAoi," +
            "SUM(pcb.ngComponentCount)  ngComponentCount,"+
            "SUM(pcb.passComponentCount) passComponentCount," +
            "SUM(pcb.goodComponentCount) goodComponentCount,"+
            "SUM(pcb.totalComponentCount) totalComponentCount,"+

            "SUM(pcb.customCount) customCount," +
            "SUM(pcb.defaultCount) defaultCount," +
            "SUM(pcb.missingCount) missingCount," +
            "SUM(pcb.shiftXCount) shiftXCount," +
            "SUM(pcb.shiftYCount) shiftYCount," +
            "SUM(pcb.rotationCount) rotationCount," +
            "SUM(pcb.bridgeCount) bridgeCount," +
            "SUM(pcb.voidCount) voidCount," +
            "SUM(pcb.tombStoneCount) tombStoneCount," +
            "SUM(pcb.pinLiftCount) pinLiftCount," +
            "SUM(pcb.solderBeadCount) solderBeadCount," +
            "SUM(pcb.smearCount) smearCount," +
            "SUM(pcb.polarityCount) polarityCount," +
            "SUM(pcb.reverseCount) reverseCount," +
            "SUM(pcb.wrongPartCount) wrongPartCount," +
            "SUM(pcb.noSolderCount) noSolderCount," +

            "SUM(pcb.copperExposureCount) copperExposureCount," +
            "SUM(pcb.excessSolderCount) excessSolderCount," +
            "SUM(pcb.solderingCount) solderingCount," +
            "SUM(pcb.excessPartsCount) excessPartsCount," +
            "SUM(pcb.barcodeCount) barcodeCount," +
            "SUM(pcb.eNM_Defect_Type_ENMMaxLengthCount) eNM_Defect_Type_ENMMaxLengthCount," +
            "pcb.lineNo lineNo  "+

            "FROM  " +
            "a_pcb pcb WHERE pcb.aoiMode=#{aoiMode} and pcb.inspectStarttime >= #{inspectStarttime} and pcb.inspectEndtime< #{inspectEndtime}   "
            //"  GROUP BY pcb.lineNo"
    })
    public APcb getPcbListWithALLLineByDateNoGroup(@Param("inspectStarttime") String inspectStarttime ,@Param("inspectEndtime") String inspectEndtime,@Param("aoiMode") String aoiMode);

    @Select({"SELECT " +
            "pcb.id id,"+
            "pcb.jobName jobName,"+
            "pcb.lineNo lineNo, "+
            "pcb.pcbIdLine pcbIdLine, "+
            "pcb.boardBarcode boardBarcode, "+
            "pcb.inspectResult inspectResult, "+
            "pcb.arrayInfo arrayInfo, "+
            //"pcb.arrayinspectResult arrayinspectResult, "+
            "pcb.laneNo laneNo, "+
            "pcb.inspectStarttime inspectStarttime, "+
            "pcb.inspectEndtime inspectEndtime," +
            "pcb.ngComponentCount ngComponentCount," +
            "pcb.hCpk hCpk, " +
            "pcb.aCpk aCpk, " +
            "pcb.vcpk vcpk, " +
            "pcb.shithxCpk shithxCpk, " +
            "pcb.shithyCpk shithyCpk " +
            "FROM  " +
            "a_pcb pcb WHERE   pcb.lineNo = #{lineNo} and pcb.aoiMode=#{aoiType}  " +
            "and pcb.inspectStarttime >= #{inspectStarttime} " +
            "and pcb.inspectEndtime <= #{inspectEndtime}  " +
            "and FIND_IN_SET(pcb.inspectResult,#{pcbResult})   "
    })
    public List<APcb> getPcbListWithDateAndLineNoPcbResult(@Param("lineNo") String lineNo,
                                                           @Param("inspectStarttime")String inspectStarttime,
                                                           @Param("inspectEndtime") String inspectEndtime,
                                                           @Param("pcbResult") String pcbResult,
                                                           @Param("aoiType")String aoiType);

}
