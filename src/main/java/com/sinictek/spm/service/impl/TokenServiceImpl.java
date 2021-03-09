package com.sinictek.spm.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author sinictek-pd
 * @Date 2021/3/2 10:14
 * @Version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * 过期时间一周
     */
    private static final long EXPIRE_TIME = 7 * 24 * 3600 * 1000;//7 * 24 * 3600 * 1000 ;

    @Override
    public String getToken(SUser user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token="";
        token= JWT.create().withAudience(user.getName()) // 将 user id 保存到 token 里面
                .withExpiresAt(date) //五分钟后token过期
                .sign(Algorithm.HMAC256(user.getName())); // 以 password 作为 token 的密钥
        return token;
    }
}
