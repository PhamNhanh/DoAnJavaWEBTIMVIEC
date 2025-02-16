package WEBTIMVIEC.example.DoAnLapTrinhJava.controllers;

import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestRole;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.RoleService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/new")
    public String newRole(Model model) {
        model.addAttribute("role", new RequestRole());
        return "Role/new";
    }
    @PostMapping("/save")
    public String saveRole(RequestRole requestRole) {
        roleService.createRole(requestRole);
        return "redirect:/roles";
    }
    @GetMapping("")
    public String index(Model model) {
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("roles", listRoles);
        return "Role/index";
    }
}