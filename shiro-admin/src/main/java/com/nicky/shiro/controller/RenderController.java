package com.nicky.shiro.controller;

import com.nicky.shiro.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转类
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Controller
public class RenderController {

    @GetMapping("")
    public ModelAndView home() {
        return ResultUtil.view("index");
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return ResultUtil.view("index");
    }

    @GetMapping("/users")
    public ModelAndView user() {
        return ResultUtil.view("user/list");
    }

    @GetMapping("/resources")
    public ModelAndView resources() {
        return ResultUtil.view("resources/list");
    }

    @GetMapping("/roles")
    public ModelAndView roles() {
        return ResultUtil.view("role/list");
    }

}
