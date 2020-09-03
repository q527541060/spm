package com.sinictek.spm.model.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sinictek-pd
 * @Date 2020/8/31 10:22
 * @Version 1.0
 */
@Getter
@Setter

public class Message<T> implements Serializable {

    private String id;
    private T content;
}
