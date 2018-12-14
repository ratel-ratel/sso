package com.nice.service;


import com.baomidou.mybatisplus.service.IService;
import com.nice.domain.UserRelevance;
import com.nice.utils.BaseResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
public interface UserRelevanceService extends IService<UserRelevance> {
    public BaseResponse add(UserRelevance model);

    public BaseResponse delete(UserRelevance model);

    public BaseResponse update(UserRelevance model);

    public BaseResponse query(UserRelevance model);
    public BaseResponse queryList(UserRelevance model);


}
