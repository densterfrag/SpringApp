package com.mark_ainad.task_7.controller;

import com.mark_ainad.task_7.Service.UserService;
import com.mark_ainad.task_7.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserContoller {
    final
    UserService userService;

    @Autowired
    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String MyInfo(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByUserName(principal.getName()));
        return "user";
    }

    @GetMapping("/edit")
    public String MyEdit(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByUserName(principal.getName()));
        return "user_edit";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("username") String name,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("dicksize")int dicksize) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setUsername(name);
            user.setLastName(lastname);
            user.setDickSize(dicksize);
            userService.updateUser(user);
        }
        return "redirect:/user";
    }
    @GetMapping("/go_admin_page")
    public String go_admin_page(Model model) {
        return "redirect:/admin";
    }

}
