package WEBTIMVIEC.example.DoAnLapTrinhJava.controllers;


import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.CreateDonUngTuyen;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestBaiTuyenDungUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestDonUngTuyenUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.BaiTuyenDungService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.DonUngTuyenService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.BaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.DonUngTuyen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/donUngTuyens")
public class DonUngTuyenController {

    @Autowired
    private DonUngTuyenService donUngTuyenService;
    @Autowired
    private BaiTuyenDungService baiTuyenDungService;

    @GetMapping("")
    public String getAllDonUngTuyen(Model model) {
        List<DonUngTuyen> donUngTuyenList = donUngTuyenService.getAllDonUngTuyen();
        model.addAttribute("donUngTuyens", donUngTuyenList);
        return "DonUngTuyen/list";
    }

    @GetMapping("details/{id}")
    public String getDonUngTuyenById(@PathVariable String id, Model model) {
        DonUngTuyen donUngTuyen = donUngTuyenService.getDonUngTuyenById(id).get();
        model.addAttribute("donUngTuyen", donUngTuyen);
        return "DonUngTuyen/details";
    }

    @GetMapping("newDonUngTuyen/{idBaiTuyenDung}")
    public String newDonUngTuyen(Model model, @PathVariable String idBaiTuyenDung) {
        CreateDonUngTuyen donUngTuyen = new CreateDonUngTuyen();
        BaiTuyenDung baiTuyenDung = baiTuyenDungService.getBaiTuyenDungById(idBaiTuyenDung).get();
        model.addAttribute("baiTuyenDung", baiTuyenDung);
        model.addAttribute("donUngTuyen", donUngTuyen);
        model.addAttribute("idBaiTuyenDung", idBaiTuyenDung); // Add baiTuyenDungId to the model
        return "DonUngTuyen/create";
    }


    @PostMapping("create")
    public String createDonUngTuyen(@ModelAttribute CreateDonUngTuyen createDonUngTuyen, @RequestParam String idBaiTuyenDung) {
        donUngTuyenService.saveDonUngTuyen(createDonUngTuyen, idBaiTuyenDung);
        return "redirect:/donUngTuyens";
    }

  /*  @PostMapping("saveChange")
    public String updateDonUngTuyen(@ModelAttribute RequestDonUngTuyenUpdate requestDonUngTuyenUpdate) {
        donUngTuyenService.updateDonUngTuyen(requestDonUngTuyenUpdate);
        return "redirect:donUngTuyens";
    }
*/
    @GetMapping("delete/{id}")
    public String deleteDonUngTuyen(@PathVariable String id) {
        donUngTuyenService.deleteDonUngTuyen(id);
        return "redirect:/donUngTuyens";
    }
}