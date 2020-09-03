package com.sinictek.spm.dao;

import com.sinictek.spm.model.SPcb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * spi-pcb表 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SPcbMapper extends BaseMapper<SPcb> {


    @Select({"SELECT " +
               "COUNT(pcb.id) total," +
               "SUM(pcb.inspectResult = 1)/COUNT(pcb.id)*100  ngPcbYeild," +
               "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100  goodPcbYeild," +
               "SUM(pcb.inspectResult= 2 )/COUNT(pcb.id)*100  passPcbYeild," +
               " SUM(pcb.inspectResult = 1)  ngPcbCount,"+
                "SUM(pcb.inspectResult = 0)  goodPcbCount,"+
                "SUM(pcb.inspectResult = 2)  passPcbCount,"+
                "SUM(pcb.ngpadCount)  ngpadCount,"+
                "SUM(pcb.passpadCount) passpadCount," +
               "SUM(pcb.lowareaCount) lowareaCount," +
               "SUM(pcb.overareaCount) overareaCount," +
                "SUM(pcb.lowheightCount) lowheightCount," +
                "SUM(pcb.overheightCount) overheightCount," +
                "SUM(pcb.shiftyCount) shiftyCount," +
                "SUM(pcb.bridgeCount) bridgeCount," +
                "SUM(pcb.shapeerrorCount) shapeerrorCount," +
                "SUM(pcb.smearedCount) smearedCount," +
                "SUM(pcb.coplanarityCount) coplanarityCount," +
                "SUM(pcb.prebridgeCount) prebridgeCount," +
                "SUM(pcb.padareapercentCount) padareapercentCount," +
                "SUM(pcb.shiftxCount) shiftxCount," +
                "SUM(pcb.otherCount) otherCount," +
                "SUM(pcb.excessCount) excessCount," +
                "SUM(pcb.insufficientCount) insufficientCount," +
                "SUM(pcb.missingCount) missingCount," +
                "pcb.lineNo lineNo, Min(pcb.inspectStarttime) inspectStarttime,Max(pcb.inspectEndtime) inspectEndtime,  " +
                "ROUND(AVG(pcb.hCpk),3) AS hCpk ,ROUND(AVG(pcb.aCpk),3) AS aCpk,ROUND(AVG(pcb.VCPK),3) AS vcpk  "+
          /// "SUM(pcb.ngpadCount) ngpadCount  "+
            "FROM  " +
            "s_pcb pcb WHERE  pcb.inspectStarttime >= #{inspectStarttime} and pcb.inspectEndtime< #{inspectEndtime}   " +
            "  GROUP BY pcb.lineNo"
    })
    public List<SPcb> getPcbListWithALLLine(@Param("inspectStarttime") String inspectStarttime ,@Param("inspectEndtime") String inspectEndtime);


    @Select({"SELECT " +
            "COUNT(pcb.id) total," +
            "SUM(pcb.inspectResult = 1)/COUNT(pcb.id)*100  ngPcbYeild," +
            "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100 goodPcbYeild," +
            "SUM(pcb.inspectResult= 2 )/COUNT(pcb.id)*100 passPcbYeild," +
            "SUM(pcb.inspectResult = 1)  ngPcbCount,"+
            "SUM(pcb.inspectResult = 0)  goodPcbCount,"+
            "SUM(pcb.inspectResult = 2)  passPcbCount,"+
            //totalpadCount
            "SUM(pcb.totalpadCount)  totalpadCount,"+
            "SUM(pcb.ngpadCount)  ngpadCount,"+
            "SUM(pcb.lowareaCount) lowareaCount," +
            "SUM(pcb.overareaCount) overareaCount," +
            "SUM(pcb.lowheightCount) lowheightCount," +
            "SUM(pcb.overheightCount) overheightCount," +
            "SUM(pcb.shiftyCount) shiftyCount," +
            "SUM(pcb.bridgeCount) bridgeCount," +
            "SUM(pcb.shapeerrorCount) shapeerrorCount," +
            "SUM(pcb.smearedCount) smearedCount," +
            "SUM(pcb.coplanarityCount) coplanarityCount," +
            "SUM(pcb.prebridgeCount) prebridgeCount," +
            "SUM(pcb.padareapercentCount) padareapercentCount," +
            "SUM(pcb.shiftxCount) shiftxCount," +
            "SUM(pcb.otherCount) otherCount," +
            "SUM(pcb.excessCount) excessCount," +
            "SUM(pcb.insufficientCount) insufficientCount," +
            "SUM(pcb.missingCount) missingCount," +
            "pcb.lineNo lineNo  " +
            /// "SUM(pcb.ngpadCount) ngpadCount  "+
            "FROM  " +
            "s_pcb pcb WHERE  pcb.inspectStarttime >= #{inspectStarttime} and pcb.inspectEndtime< #{inspectEndtime}   "
            //"  GROUP BY pcb.lineNo"
    })
    public SPcb getPcbListWithALLLineByDateNoGroup(@Param("inspectStarttime") String inspectStarttime ,@Param("inspectEndtime") String inspectEndtime);


    @Select({"SELECT " +
            "COUNT(pcb.id) total," +
            "SUM(pcb.inspectResult = 1)/COUNT(pcb.id)*100  ngPcbYeild," +
            "SUM(pcb.inspectResult = 0)/COUNT(pcb.id)*100  goodPcbYeild," +
            "SUM(pcb.inspectResult= 2 )/COUNT(pcb.id)*100  passPcbYeild," +
            "SUM(pcb.inspectResult = 1)  ngPcbCount,"+
            "SUM(pcb.inspectResult = 0)  goodPcbCount,"+
            "SUM(pcb.inspectResult = 2)  passPcbCount,"+
            "SUM(pcb.ngpadCount)  ngpadCount,"+
            "SUM(pcb.passpadCount) passpadCount," +
            "SUM(pcb.lowareaCount) lowareaCount," +
            "SUM(pcb.overareaCount) overareaCount," +

            "SUM(pcb.lowareaCount) lowareaCount," +
            "SUM(pcb.overareaCount) overareaCount," +
            "SUM(pcb.lowheightCount) lowheightCount," +
            "SUM(pcb.overheightCount) overheightCount," +
            "SUM(pcb.shiftyCount) shiftyCount," +
            "SUM(pcb.bridgeCount) bridgeCount," +
            "SUM(pcb.shapeerrorCount) shapeerrorCount," +
            "SUM(pcb.smearedCount) smearedCount," +
            "SUM(pcb.coplanarityCount) coplanarityCount," +
            "SUM(pcb.prebridgeCount) prebridgeCount," +
            "SUM(pcb.padareapercentCount) padareapercentCount," +
            "SUM(pcb.shiftxCount) shiftxCount," +
            "SUM(pcb.otherCount) otherCount," +
            "SUM(pcb.excessCount) excessCount," +
            "SUM(pcb.insufficientCount) insufficientCount," +
            "SUM(pcb.missingCount) missingCount," +
            "pcb.lineNo lineNo,Min(pcb.inspectStarttime) inspectStarttime,Max(pcb.inspectEndtime) inspectEndtime   " +
            /// "SUM(pcb.ngpadCount) ngpadCount  "+
            "FROM  " +
            "s_pcb pcb WHERE pcb.lineNo = #{lineNo} "  + //})
            "and pcb.inspectStarttime >= #{inspectStarttime}  " +
            "and pcb.inspectEndtime <= #{inspectEndtime}"})
    public SPcb getPcbListWithLineNo(@Param("lineNo") String lineNo,
                                     @Param("inspectStarttime") String inspectStarttime,
                                     @Param("inspectEndtime") String inspectEndtime);

    @Select({"SELECT  COUNT(pcb.id) total, " +
            "AVG(pcb.hCpk) hCpk, AVG(pcb.VCPK) VCPK, AVG(pcb.aCpk) aCpk, AVG(pcb.shithxCpk) shithxCpk, AVG(pcb.shiftyCount) shiftyCount, " +
            "MAX(pcb.ucl) ucl, MAX(pcb.lcl) lcl FROM s_pcb pcb  " +
            "WHERE pcb.lineNo=#{lineNo} AND pcb.inspectStarttime >= #{inspectStarttime} AND pcb.inspectEndtime< #{inspectEndtime}"})
    public SPcb getPcbListProductCPKWithLineNo(@Param("lineNo") String lineNo,
                                            @Param("inspectStarttime") String inspectStarttime,
                                            @Param("inspectEndtime") String inspectEndtime);


    @Select({"SELECT " +
            "pcb.id id,"+
            "pcb.jobName jobName,"+
            "pcb.lineNo lineNo, "+
            "pcb.pcbIdLine pcbIdLine, "+
            "pcb.boardBarcode boardBarcode, "+
            "pcb.inspectResult inspectResult, "+
            "pcb.arrayBarcode arrayBarcode, "+
            "pcb.arrayinspectResult arrayinspectResult, "+
            "pcb.laneNo laneNo, "+
            "pcb.inspectStarttime inspectStarttime, "+
            "pcb.inspectEndtime inspectEndtime," +
            "pcb.ngpadCount ngpadCount," +
            "pcb.hCpk hCpk, " +
            "pcb.aCpk aCpk, " +
            "pcb.vcpk vcpk, " +
            "pcb.shithxCpk shithxCpk, " +
            "pcb.shithyCpk shithyCpk " +
            "FROM  " +
            "s_pcb pcb WHERE pcb.lineNo = #{lineNo}   " +
            "and pcb.inspectStarttime >= #{inspectStarttime} " +
            "and pcb.inspectEndtime <= #{inspectEndtime}  " +
            "and FIND_IN_SET(pcb.inspectResult,#{pcbResult})   "
           })
    public List<SPcb> getPcbListWithDateAndLineNoPcbResult(@Param("lineNo") String lineNo,
                                                           @Param("inspectStarttime")String inspectStarttime,
                                                           @Param("inspectEndtime") String inspectEndtime,
                                                           @Param("pcbResult") String pcbResult);
}
