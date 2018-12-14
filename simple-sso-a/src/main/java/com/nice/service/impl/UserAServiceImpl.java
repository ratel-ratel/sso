package com.nice.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nice.domain.UserA;
import com.nice.mapper.UserAMapper;
import com.nice.service.UserAService;
import com.nice.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
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
public class UserAServiceImpl extends ServiceImpl<UserAMapper, UserA> implements UserAService {
    public UserAServiceImpl() {
        super();
    }

    public UserAServiceImpl(UserAMapper baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public BaseResponse add(UserA model) {
        log.info("add ShortLink request is : {}", model);
        boolean back = this.insert(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        log.info("add ShortLink response is : {}", baseResponse);
        return baseResponse;
    }

    @Override
    public BaseResponse delete(UserA model) {
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
    public BaseResponse update(UserA model) {
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
    public BaseResponse query(UserA model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            UserA data = this.selectById(model.getId());
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
    public BaseResponse queryList(UserA model) {
        log.info("queryList request is : {}", model);
        BaseResponse baseResponse;
        EntityWrapper<UserA> ew = new EntityWrapper<UserA>();
        if (StringUtil.isNotEmpty(model.getUserNo())) {
            ew.eq("userNo", model.getUserNo());
        }
        List<UserA> data = this.selectList(ew);
        if (null != data) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setDataList(data);
        } else {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        log.info("queryList response is : {}", baseResponse);
        return baseResponse;
    }

    @Override
    public BaseResponse userLogin(String userNo, String pwd, HttpServletRequest request, HttpServletResponse response) {
        BaseResponse baseResponse;
        EntityWrapper<UserA> ew = new EntityWrapper<UserA>();
        ew.eq("userNo", userNo);
        UserA data = this.selectOne(ew);
        if (null!=data&&pwd.equals(data.getPassword())){
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
            baseResponse.setDataInfo(data);
            Cookie cookie = new Cookie("UserNoA", data.getUserNo());
            response.addCookie(cookie);

        }else {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1001.getCode());
        }

        return baseResponse;
    }

}
