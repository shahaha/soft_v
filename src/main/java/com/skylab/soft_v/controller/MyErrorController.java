package com.skylab.soft_v.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lwc
 * @date 2020/7/23 11:04
 * 自定义处理/error、404
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        System.out.println(statusCode);
        if (statusCode == 404) {
            return "error/404";
        } else {
            return "error/error";
        }

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}