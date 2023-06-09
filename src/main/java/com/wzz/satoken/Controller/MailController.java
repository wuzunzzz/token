package com.wzz.satoken.Controller;

import com.wzz.satoken.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/getCheckCode")
    @ResponseBody
    public String getCheckCode(String email, HttpSession session){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        session.setAttribute("code",checkCode);
        return checkCode;
    }


}
