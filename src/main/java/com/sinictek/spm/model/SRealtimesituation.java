package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-04-16
 */
@Data
@TableName("s_realTimeSituation")
public class SRealtimesituation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String lineNo;
    private Date DataTime;
    private String JobName;
    private String PadGroup;
    private String PCBIDs;
    private String PCBBarcodes;
    private String Rules;
    private String FieldType;
    private String Resources;
    private String remark;
    private String remark1;
    private String remark2;
    private String remark3;
    private String create_time;


}
