package com.timebusker.service.impl;

import com.timebusker.common.R;
import com.timebusker.oauth2.TokenGenerator;
import com.timebusker.service.UserTokenService;

import java.util.Date;

/**
 * @DESC:UserTokenServiceImpl
 * @author:timebusker
 * @date:2018/8/29
 */
public class UserTokenServiceImpl implements UserTokenService {

    // 2小时后过期
    private final static int EXPIRE = 1000 * 60 * 60 * 2;

    @Override
    public R createToken(String userId) {
        // 生成一个token
        // String token = TokenGenerator.generateValue();
        String token = userId;
        //过期时间
        Date expireTime = new Date(new Date().getTime() + EXPIRE);
        R r = R.ok().put("token", token).put("expire", expireTime);
        return r;
    }
}
