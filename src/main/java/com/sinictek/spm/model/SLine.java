package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 线体总表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_line")
@Getter
@Setter
public class SLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String LineNo;
    private String lineContent;
    private Date createDate;
    private Date updateDate;
    private String remark;

    @TableField(exist = false)
    private String idStr;
    @TableField(exist = false)
    private String createDateStr;
    @TableField(exist = false)
    private String updateDateStr;
    @TableField(exist = false)
    private Integer modeType;


}
