package com.mark_ainad.task_7.controller;

import com.mark_ainad.task_7.Repository.RoleRepository;
import com.mark_ainad.task_7.Service.UserService;
import com.mark_ainad.task_7.Service.UserServiceImpl;
import com.mark_ainad.task_7.entity.Role;
import com.mark_ainad.task_7.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminContoller {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final Role role;

    @Autowired
    public AdminContoller(UserServiceImpl userService, RoleRepository roleRepository, Role role) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.role = role;
    }

    @GetMapping("/myinfo")
    public String MyInfo(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByUserName(principal.getName()));
        return "user";
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @PostMapping("/add")
    public String add(@RequestParam("username") String name,
                      @RequestParam("lastname") String lastname,
                      @RequestParam("dicksize") int dicksize,
                      @RequestParam("password") String password,
                      @RequestParam(value = "roles", required = false) List<String> roles,
                      Model model) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String rolename : roles) {
                Role role = roleRepository.findByRolename(rolename);
                roleSet.add(role);
            }
        }
        userService.addUser(new User(name, lastname, dicksize, password, roleSet));
        return "redirect:/admin";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("username") String name,
                         @RequestParam("lastname") String lastname,
                         @RequestParam("dicksize") int dicksize,
                         @RequestParam(value = "roles", required = false) List<String> roles,
                         Model model) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String rolename : roles) {
                Role role = roleRepository.findByRolename(rolename);
                roleSet.add(role);
            }
        }
        User user = userService.getUserById(id);
        if (user != null) {
            user.setUsername(name);
            user.setLastName(lastname);
            user.setDickSize(dicksize);
            user.setRoles(roleSet);
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("gotouserpage")
    public String gotoUserPage(Model model, Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "redirect:/user";
    }


}

