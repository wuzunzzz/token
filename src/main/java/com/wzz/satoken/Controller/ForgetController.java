package com.wzz.satoken.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.satoken.Exception.BusinessException;
import com.wzz.satoken.Mapper.UserMapper;
import com.wzz.satoken.pojo.User111;
import com.wzz.satoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ForgetController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(String name, HttpSession session) {
        QueryWrapper<User111> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        User111 user = userMapper.selectOne(wrapper);
        if (user != null) {
            String email = user.getEmail();
            session.setAttribute("name",name);
            return "forgetpwd";

        }else {
            return "error0";
        }
    }
    @PostMapping("/forgetpwd")
    public  String forgetPwd(String email, String pwd, String pwd1,String checkCode, HttpSession session) throws BusinessException {
        String code = (String) session.getAttribute("code");
        String name = (String) session.getAttribute("name");
        System.out.println(code);
        System.out.println("+"+checkCode);
        QueryWrapper<User111> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User111 user = userMapper.selectOne(wrapper);
        String email1 =user.getEmail();
        System.out.println(email+","+email1);
        if (!email1.equals(email))
            throw new BusinessException("邮箱不正确");

        if (!checkCode.equals(code)) {
            throw new BusinessException("验证码不正确");
        }
        Long id = user.getId();
        userService.update(id,pwd1);
        return "index";
    }


}
