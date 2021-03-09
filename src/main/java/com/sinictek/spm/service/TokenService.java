package com.sinictek.spm.service;

import com.sinictek.spm.model.SUser;

/**
 * @Author sinictek-pd
 * @Date 2021/3/2 10:12
 * @Version 1.0
 */
public interface TokenService {
    public String getToken(SUser user);
}
