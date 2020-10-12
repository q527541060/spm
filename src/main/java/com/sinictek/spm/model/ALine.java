package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2020-08-05
 */
@TableName("a_line")
public class ALine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线体ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 线体编号
     */
    private String LineNo;
    /**
     * 线体说明
     */
    private String lineContent;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * remark
     */
    private String remark;



}
