package WEBTIMVIEC.example.DoAnLapTrinhJava.controllers;


import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.BaiTuyenDungService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.BaiTuyenDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private BaiTuyenDungService baiTuyenDungService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getAllBaiTuyenDung(Model model) {
        List<BaiTuyenDung> baiTuyenDungs = baiTuyenDungService.getAllBaiTuyenDung();
        model.addAttribute("baiTuyenDungs", baiTuyenDungs);
        return "Home/index";
    }

    @GetMapping("details/{id}")
    public String getBaiTuyenDungById(@PathVariable String id, Model model) {
        BaiTuyenDung baiTuyenDung = baiTuyenDungService.getBaiTuyenDungById(id).get();
        model.addAttribute("baiTuyenDung", baiTuyenDung);
        return "Home/details";
    }



}