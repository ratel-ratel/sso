package com.nice.controller;


import com.nice.domain.UserB;
import com.nice.service.UserBService;
import com.nice.utils.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/userB")
public class UserBController extends BaseController {
    private UserBService userBService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserB vo) {
        return userBService.add(vo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserB vo) {
        return userBService.update(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserB vo) {
        return userBService.query(vo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserB vo) {
        return userBService.delete(vo);
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse queryList(@RequestBody UserB vo) {
        return userBService.queryList(vo);
    }
    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public BaseResponse userLogin(String userNo, String pwd,
                                  HttpServletRequest request, HttpServletResponse response) {
        return userBService.userLogin(userNo,pwd,request,response);
    }
}
