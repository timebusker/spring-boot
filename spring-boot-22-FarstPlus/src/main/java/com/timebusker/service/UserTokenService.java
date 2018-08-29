package com.timebusker.service;

import com.timebusker.common.R;

/**
 * @DESC:UserTokenService
 * @author:timebusker
 * @date:2018/8/29
 */
public interface UserTokenService {

    /**
     * 登录token生成
     *
     * @param userId
     * @return
     */
    public R createToken(String userId);
}
