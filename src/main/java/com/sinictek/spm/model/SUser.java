package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2020-06-09
 */
@Getter
@Setter
@TableName("s_user")
public class SUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String psw;
    private String lineNoStr;
    private String remark;
    private  String create_time;


}
