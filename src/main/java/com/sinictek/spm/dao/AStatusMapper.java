package com.sinictek.spm.dao;

import com.sinictek.spm.model.AStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sinictek.spm.model.SStatus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * aoi-设备状态 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
public interface AStatusMapper extends BaseMapper<AStatus> {


    @Select({"SELECT s.equipmentNo equipmentNo,s.lineNo lineNo,s.`status` status,s.factory factory,s.errContent errContent," +
            "s.start start,s.run run,s.stop stop,s.idle idle,s.error error ,MAX(s.updateTime) updateTime  " +
            " FROM db_spm.a_status s  GROUP BY s.lineNo desc"})
    //WHERE s.lineNo in( SELECT l.LineNo FROM db_spm.s_line l )
    public List<AStatus> getAllStatusWithLineNoLimt_AOI();

    @Select({"<script>SELECT s.equipmentNo equipmentNo,s.lineNo lineNo,s.`status` status,s.factory factory,s.errContent errContent," +
            "s.start start,s.run run,s.stop stop,s.idle idle,s.error error ,s.lane lane,MAX(s.updateTime) updateTime  " +
            " FROM db_spm.a_status s " +
            "<where> s.updateTime > DATE_SUB(NOW(),INTERVAL 24 HOUR) and s.aoiMode=${aoiType} <if test=\"lane !=''\"> and s.lane=${lane} </if> </where> GROUP BY s.lineNo desc </script>"})
    //WHERE s.lineNo in( SELECT l.LineNo FROM db_spm.s_line l )
    public List<AStatus> getAllStatusWithLineNoLimt(@Param("lane") String lane ,@Param("aoiType")String aoiType);

    @Select({"SELECT s.equipmentNo equipmentNo,s.lineNo lineNo,s.`status` status,s.factory factory,s.errContent errContent," +
            "s.start start,s.run run,s.stop stop,s.idle idle,s.error error ,MAX(s.updateTime) updateTime  " +
            " FROM db_spm.a_status s  where s.lineNo=#{lineNo} and s.aoiMode=#{aoiType}"})
    //WHERE s.lineNo in( SELECT l.LineNo FROM db_spm.s_line l )
    public AStatus getStatusWithLineNo(@Param("lineNo") String lineNo,@Param("aoiType")String aoiType);

}
