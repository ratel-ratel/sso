package com.nice.service;


import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.UserA;
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
public interface UserAService extends IService<UserA> {
    public BaseResponse add(UserA model);

    public BaseResponse delete(UserA model);

    public BaseResponse update(UserA model);

    public BaseResponse query(UserA model);
    public BaseResponse queryList(UserA model);

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
