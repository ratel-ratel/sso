package com.nice.controller;


import com.nice.domain.UserRelevance;
import com.nice.service.UserRelevanceService;
import com.nice.utils.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/userRelevance")
public class UserRelevanceController extends BaseController {
    private UserRelevanceService userRelevanceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserRelevance vo) {
        return userRelevanceService.add(vo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserRelevance vo) {
        return userRelevanceService.update(vo);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserRelevance vo) {
        return userRelevanceService.query(vo);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserRelevance vo) {
        return userRelevanceService.delete(vo);
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse queryList(@RequestBody UserRelevance vo) {
        return userRelevanceService.queryList(vo);
    }

}
