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
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
@TableName("a_job")
@Getter
@Setter
public class AJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String jobName;
    private String jobVersion;
    private String lineNo;
    private Date creatDate;
    private Date updateDate;
    private String remark;
    private Integer aoiMode;

    private  String create_time;

}
