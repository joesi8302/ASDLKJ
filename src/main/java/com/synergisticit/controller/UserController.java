package com.synergisticit.controller;

import com.synergisticit.Validation.UserValidator;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserValidator userValidator;


    @InitBinder
    public void initBinder (WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    @RequestMapping("userForm")
    public String userForm(Model model, Principal principal, HttpServletRequest request,Authentication authentication) {
//        request.getUserPrincipal().getName(); // another way to get username of logged in user
//
//        System.out.println(authentication.getCredentials());
//        System.out.println(request.getUserPrincipal().getName());
        System.out.println("User Page Reached");
        User newUser = new User();
        getModel(model, principal);
        Long maxId = userRepository.getMaxId();
        if(maxId == null){
            maxId = (long) 1;
        }
        else{
            maxId++;
        }

        newUser.setUserId(maxId);
        model.addAttribute("user", newUser);

        return "userForm";
    }

    @RequestMapping("saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Principal principal) {
        if(result.hasErrors()){
            getModel(model, principal);
            return "userForm";
        }

        User foundUser = userService.findById(user.getUserId());
        if(foundUser != null) {
            user.setCreatedDate(foundUser.getCreatedDate());
            user.setCreatedBy(foundUser.getCreatedBy());
        }
        userService.save(user);

        return "redirect:userForm";
    }

    @GetMapping("deleteUser")
    public String deleteUser(User user, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "userForm";
        }
        userService.delete(user.getUserId());
        getModel(model, principal);
        return "redirect:userForm";
    }

    @GetMapping("updateUser")
    public String updateUser(User user, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "userForm";
        }
        getModel(model, principal);
        User foundUser = userService.findById(user.getUserId());
        foundUser.getUserPassword();

        model.addAttribute("user", foundUser);
        model.addAttribute("retrievedRoles",foundUser.getUserRoles());

        return "userForm";
    }




    public Model getModel(Model model, Principal principal) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        if(principal == null){
            Role role = roleService.findById(2L); // grabs the user role;
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            model.addAttribute("roles", roles);
        }
        else{
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            System.out.println("Role Page Reached");
        }

        return model;
    }
}
