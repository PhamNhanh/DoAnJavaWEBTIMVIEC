package WEBTIMVIEC.example.DoAnLapTrinhJava.controllers;

import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.CreateBaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestBaiTuyenDungUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.BaiTuyenDungService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.BaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.ThanhPho;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.ThanhPhoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/baiTuyenDungs")
public class BaiTuyenDungController {
    @Autowired
    private BaiTuyenDungService baiTuyenDungService;
    @Autowired
    private ThanhPhoRepository thanhPhoRepository;

    @GetMapping("")
    public String getAllBaiTuyenDung(Model model) {
        List<BaiTuyenDung> baiTuyenDungs = baiTuyenDungService.getAllBaiTuyenDung();
        model.addAttribute("baiTuyenDungs", baiTuyenDungs);
        return "BaiTuyenDung/list";
    }

    @GetMapping("details/{id}")
    public String getBaiTuyenDungById(@PathVariable String id, Model model) {
        BaiTuyenDung baiTuyenDung = baiTuyenDungService.getBaiTuyenDungById(id).get();
        model.addAttribute("baiTuyenDung", baiTuyenDung);
        return "BaiTuyenDung/details";
    }
    @GetMapping("/newBaiTuyenDung")
    public String newBaiTuyenDung(Model model) {
        CreateBaiTuyenDung baiTuyenDung = new CreateBaiTuyenDung();
        List<ThanhPho> thanhPhos = thanhPhoRepository.findAll();
        model.addAttribute("thanhPhos", thanhPhos);
        model.addAttribute("baiTuyenDung", baiTuyenDung);
        return "BaiTuyenDung/create";
    }

    @PostMapping("/createBaiTuyenDung")
    public String createBaiTuyenDung(@ModelAttribute CreateBaiTuyenDung createBaiTuyenDung) {
        baiTuyenDungService.saveBaiTuyenDung(createBaiTuyenDung);
        return "redirect:/baiTuyenDungs";
    }

    @GetMapping("edit/{id}")
    public String updateBaiTuyenDungById(@PathVariable String id, Model model) {
        BaiTuyenDung baiTuyenDung = baiTuyenDungService.getBaiTuyenDungById(id).get();
        List<ThanhPho> thanhPhos = thanhPhoRepository.findAll();
        model.addAttribute("thanhPhos", thanhPhos);
        model.addAttribute("baiTuyenDung", baiTuyenDung);
        return "BaiTuyenDung/edit";
    }


    @PostMapping("saveChange")
    public String updateBaiTuyenDung(RequestBaiTuyenDungUpdate requestBaiTuyenDungUpdate, @RequestParam String thanhPhoId) {
        baiTuyenDungService.updateBaiTuyenDung(requestBaiTuyenDungUpdate, thanhPhoId);
        return "redirect:/baiTuyenDungs";
    }

    @GetMapping("delete/{id}")
    public String deleteBaiTuyenDung(@PathVariable String id) {
        baiTuyenDungService.deleteBaiTuyenDung(id);
        return "redirect:/baiTuyenDungs";
    }
}
