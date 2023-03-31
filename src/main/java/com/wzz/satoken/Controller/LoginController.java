package com.wzz.satoken.Controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wzz.satoken.Exception.BusinessException;
import com.wzz.satoken.pojo.User111;
import com.wzz.satoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name,String pwd) {
        User111 user = userService.LoginIn(name, pwd);

        if (user != null) {
            StpUtil.login(user.getId());
            return "success";
        }else {
            return "error0";
        }
    }

    @GetMapping("/islogin")
    public String test(){
        if (StpUtil.isLogin()) {
            return "success1";
        }else {
            return "error12";
        }
    }
    @GetMapping("/logout")
    public String test1(){
        StpUtil.logout();
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(String name, String pwd, String email, String checkCode, HttpSession session) throws BusinessException {
        String code = (String)session.getAttribute("code");
        if (checkCode.equals(code)){
            userService.Register(name, pwd,email);
            return "success";

        }
        else {
            return "error2";
        }

    }
    @RequestMapping("/signup")
    public String disp(){
        return "register";
    }
    @RequestMapping("/goForgetPwd")
    public String disp0(){
        return "forget";
    }


}
