package com.koreait.springbootboard.user;

import com.koreait.springbootboard.Const;
import com.koreait.springbootboard.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/login")
    public void login(@ModelAttribute("userEntity") UserEntity userEntity) {
        userEntity.setUid("hong123");
    }

    @PostMapping("/login")
    public String loginProc(UserEntity entity) {
        int result = service.login(entity);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession hs) {
        hs.invalidate();
        return "redirect:/";
    }

    @GetMapping("/join")
    public void join(@ModelAttribute("userEntity") UserEntity userEntity) {}

    @PostMapping("/join")
    public String joinProc(UserEntity userEntity) {
        System.out.println(userEntity);
        int result = service.join(userEntity);
        return "redirect:login";
    }


}
