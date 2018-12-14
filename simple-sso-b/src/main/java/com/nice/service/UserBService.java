package com.nice.service;


import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.UserA;
import com.nice.domain.UserB;
import com.nice.utils.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
public interface UserBService extends IService<UserB> {
    public BaseResponse add(UserB model);

    public BaseResponse delete(UserB model);

    public BaseResponse update(UserB model);

    public BaseResponse query(UserB model);
    public BaseResponse queryList(UserB model);

    /**
     * 登录
     * @param userNo
     * @param pwd
     * @param request
     * @param response
     * @return
     */
    BaseResponse userLogin(String userNo, String pwd, HttpServletRequest request, HttpServletResponse response);
}
