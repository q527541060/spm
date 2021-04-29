package com.sinictek.spm.model;

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
@TableName("a_defaultType")
@Getter
@Setter
public class ADefaulttype implements Serializable {

    private static final long serialVersionUID = 1L;

    private String errorcode;
    private String content;

    private  String create_time;
}
