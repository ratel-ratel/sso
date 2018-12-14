package com.nice.controller;


import com.nice.domain.UserA;
import com.nice.service.UserAService;
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
@RequestMapping("/userA")
public class UserAController extends BaseController {
    private UserAService userAService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserA vo) {
        return userAService.add(vo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserA vo) {
        return userAService.update(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserA vo) {
        return userAService.query(vo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserA vo) {
        return userAService.delete(vo);
    }

    @RequestMapping(value = "/queryList")
    @ResponseBody
    public BaseResponse queryList() {
        UserA vo=new UserA();
        return userAService.queryList(vo);
    }
    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public BaseResponse userLogin(String userNo, String pwd,
                                  HttpServletRequest request, HttpServletResponse response) {
        return userAService.userLogin(userNo,pwd,request,response);
    }
}
