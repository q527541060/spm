package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2020-06-17
 */
@TableName("s_defaultSetting")
@Getter
@Setter
public class SDefaultsetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String settingName;
    private String settingValue;
    private Date updateTime;
    private String remark;

    @TableField(exist = false)
    private String idStr;
    @TableField(exist = false)
    private String updateTimeStr;

    private  String create_time;
}
