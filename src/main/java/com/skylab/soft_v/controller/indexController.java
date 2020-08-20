package com.skylab.soft_v.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lwc
 * @date 2020/7/9 9:39
 * 直接访问templates下的静态页面是无法获取static中的样式的
 * 用该控制器进行去访问, 该控制器没有其他作用, 只是为了访问界面而已
 */
@Controller
@Slf4j
@Api(tags = "跳转页面")
@RequestMapping("index")
public class indexController {
    @GetMapping("/success")
    public String toIndex() {
        return "main";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "user/reg";
    }

    @GetMapping("/dict/dict")
    public String dict() { return "dict/dict"; }

    @GetMapping("/addSoft")
    public String addSoft() { return "softCenter/addSoft"; }

    @GetMapping("/editSoft")
    public String editSoft() { return "softCenter/editSoft"; }

    @GetMapping("/home")
    public String home() { return "home/home"; }

    @GetMapping("/download")
    public String download() { return "softCenter/download"; }

    @GetMapping("/upload")
    public String upload() { return "softCenter/upload"; }

    @GetMapping("/dict/dictData")
    public String dictData() { return "dict/dictData"; }

    @GetMapping("/addSelect")
    public String addSelect() { return "softCenter/addSelect"; }

    @GetMapping("/demo")
    public String demo() { return "softCenter/demo"; }

    @GetMapping("/log")
    public String log() { return "dataCenter/log"; }

    @GetMapping("/role")
    public String role() { return "user/role"; }

    @GetMapping("/addRole")
    public String addRole() { return "user/addRole"; }

    @GetMapping("/editRole")
    public String editRole() { return "user/editRole"; }

    @GetMapping("/account")
    public String account() { return "user/account"; }

    @GetMapping("/addAccount")
    public String addAccount() { return "user/addAccount"; }

    @GetMapping("/editAccount")
    public String editAccount() { return "user/editAccount"; }

    @GetMapping("/password")
    public String password() { return "user/password"; }

    @GetMapping("/downloadTools")
    public String downloadTools() { return "softCenter/downloadTools"; }

}
