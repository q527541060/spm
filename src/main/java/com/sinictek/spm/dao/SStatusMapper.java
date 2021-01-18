package com.sinictek.spm.dao;

import com.sinictek.spm.model.SStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * spi-设备状态 Mapper 接口
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
public interface SStatusMapper extends BaseMapper<SStatus> {



    @Select({"<script>SELECT s.equipmentNo equipmentNo,s.lineNo lineNo,s.`status` status,s.factory factory,s.errContent errContent," +
            "s.start start,s.run run,s.stop stop,s.idle idle,s.error error ,s.lane lane,MAX(s.updateTime) updateTime  " +
            " FROM db_spm.s_status s " +
            "<where> s.updateTime > DATE_SUB(NOW(),INTERVAL 48 HOUR) <if test=\"lane !=''\"> and s.lane=${lane} </if> </where> GROUP BY s.lineNo desc </script>"})
    //WHERE s.lineNo in( SELECT l.LineNo FROM db_spm.s_line l )
    public List<SStatus> getAllStatusWithLineNoLimt(@Param("lane") String lane );

    @Select({"SELECT s.equipmentNo equipmentNo,s.lineNo lineNo,s.`status` status,s.factory factory,s.errContent errContent," +
            "s.start start,s.run run,s.stop stop,s.idle idle,s.error error ,MAX(s.updateTime) updateTime  " +
            " FROM db_spm.s_status s  where s.lineNo=#{lineNo} "})
    //WHERE s.lineNo in( SELECT l.LineNo FROM db_spm.s_line l )
    public SStatus getStatusWithLineNo(@Param("lineNo") String lineNo);

}
