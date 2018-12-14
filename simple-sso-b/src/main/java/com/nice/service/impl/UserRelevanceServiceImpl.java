package com.nice.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nice.domain.UserRelevance;
import com.nice.mapper.UserRelevanceMapper;
import com.nice.service.UserRelevanceService;
import com.nice.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
@Slf4j
@Service
public class UserRelevanceServiceImpl extends ServiceImpl<UserRelevanceMapper, UserRelevance> implements UserRelevanceService {
    public UserRelevanceServiceImpl() {
        super();
    }

    public UserRelevanceServiceImpl(UserRelevanceMapper baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public BaseResponse add(UserRelevance model) {
        log.info("add UserRelevance request is : {}", model);
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        log.info("add UserRelevance response is : {}", baseResponse);
        return baseResponse;
    }

    @Override
    public BaseResponse delete(UserRelevance model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.deleteById(model.getId());
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse update(UserRelevance model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.updateById(model);
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse query(UserRelevance model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            UserRelevance data = this.selectById(model.getId());
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
    }

    @Override
    public BaseResponse queryList(UserRelevance model) {
        log.info("queryList request is : {}", model);
        BaseResponse baseResponse;
        EntityWrapper<UserRelevance> ew = new EntityWrapper<UserRelevance>();
        if (StringUtil.isNotEmpty(model.getUserNOB())) {
            ew.eq("userNO_B", model.getUserNOB());
        }
        List<UserRelevance> data = this.selectList(ew);
        if (null != data) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setDataList(data);
        } else {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        log.info("queryList response is : {}", baseResponse);
        return baseResponse;
    }


}
