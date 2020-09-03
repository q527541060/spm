package com.sinictek.spm.model.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author sinictek-pd
 * @Date 2020/8/31 10:25
 * @Version 1.0
 */
@Setter
@Getter
public class AddMessageReq {
    private String topic;

    private String tag;

    private Message<String> message;
}
