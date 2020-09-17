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
 * job总表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_job")
@Getter
@Setter
public class SJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "jobId", type = IdType.AUTO)
    private Long jobId;
    private String jobName;
    private String jobVersion;
    private String lineNo;
    private Date creatDate;
    private Date updateDate;
    private String remark;



}
