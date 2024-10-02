package com.synergisticit.controller;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import com.synergisticit.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("roleForm")
    public String roleForm(Model model, Principal principal) {

        getModel(model, principal);

        return "roleForm";
    }

    @PostMapping("saveRole")
    public String saveRole(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model){
        if(result.hasErrors()){
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            return "roleForm";
        }

        Role foundRole = roleService.findById(role.getRoleId());
        if(foundRole != null) {
            role.setCreatedDate(foundRole.getCreatedDate());
            role.setCreatedBy(foundRole.getCreatedBy());
        }

        roleService.save(role);

        return "redirect:roleForm";
    }

    @GetMapping("updateRole")
    public String updateRole(Role role, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "accountForm";
        }
        getModel(model, principal);
        Role foundRole = roleService.findById(role.getRoleId());
        model.addAttribute("account", foundRole);



        return "accountForm";
    }


    @GetMapping("deleteRole")
    public String deleteRole(Role role, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            return "roleForm";
        }
        roleService.delete(role.getRoleId());
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "redirect:roleForm";
    }


    public Model getModel(Model model, Principal principal){

        Role newRole = new Role();
        newRole.setRoleId(roleRepository.getMaxId()+1);
        model.addAttribute("role", newRole);

        if(principal.getName().isEmpty()){
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
