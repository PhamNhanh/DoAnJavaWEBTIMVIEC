package WEBTIMVIEC.example.DoAnLapTrinhJava.controllers;

import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestCreateNhaTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestCreateUngVien;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestUpdateUngVien;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.CustomUserDetailJava;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender emailSender;




    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/registerNhaTuyenDung")
    public String registerNTD(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "user/registerNhaTuyenDung";
    }

    @PostMapping("/registerNhaTuyenDung")
    public String registerNTD(@Valid @ModelAttribute("user") RequestCreateNhaTuyenDung requestCreateNhaTuyenDung,
                           @NotNull BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "user/registerNhaTuyenDung";
        }
        userService.CreateNhaTuyenDung(requestCreateNhaTuyenDung);
        return "redirect:/users/login";
    }

    @GetMapping("/registerUngVien")
    public String registerUV(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "user/registerUngVien";
    }

    @PostMapping("/registerUngVien")
    public String registerUV(@Valid @ModelAttribute("user") RequestCreateUngVien requestCreateUngVien,
                              @NotNull BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "user/registerUngVien";
        }
        userService.CreateUngVien(requestCreateUngVien);
        return "redirect:/users/login";
    }

    @GetMapping("/profileUngVien")
    public String profileUngVien(@NotNull Model model) {
        User editUngVien = new User();
        String tenUngVien = userService.getCurrentUsername();
        User ungVien = userService.findUserByUsername(tenUngVien);
        model.addAttribute("saveUngVien", editUngVien);
        model.addAttribute("ungVien", ungVien);
        return "user/profileUngVien";
    }

    @GetMapping("/forgotpassword")
    public String ForgotPassword() {
        return "user/forgotpassword";
    }

    @PostMapping("/forgot_password")
    public String ForgotPassword_Submit(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        if (user != null) {
            user = userService.createTokenResetPassword(user);
            String token = user.getResetPasswordToken();
            String URL = "http://localhost:8080/reset_password?token=" + token;
            SendMail(user.getEmail(), URL);
            return "redirect:/users/forgotpassword?done";
        }
        return "redirect:/users/forgotpassword?error";
    }

    @GetMapping("/changepassword")
    public String ChangePassword(@AuthenticationPrincipal CustomUserDetailJava customUserDetail, Model model) {
        User user = customUserDetail.getUser();
        model.addAttribute("user", user);
        return "user/changepassword";
    }

    @PostMapping("/change_password")
    public String ChangePassword_Submit(@AuthenticationPrincipal CustomUserDetailJava customUserDetail, @RequestParam("password") String password, Model model) {
        try {
            User user = customUserDetail.getUser();
            userService.ChangePassword(user, password);
            return "redirect:/users/changepassword?done";
        } catch (Exception e) {
            return "redirect:/users/changepassword?error";
        }
    }

    public void SendMail(String DesMail, String URL){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("phiphai@gmail.com");
        message.setTo(DesMail);
        message.setSubject("Thư xác nhận quên mật khẩu");
        message.setText("<p>Hello,</p>" +
                "<p>You have requested to reset your password.</p>" +
                "<p>Click the link below to change your password:</p>" +
                "<p><a href=" + URL + ">Change my password</a></p>" +
                "<br><p>Ignore this email if you do remember your password," +
                "or you have not made the request.</p>");
        emailSender.send(message);
    }

    @GetMapping("/profileNTD")
    public String profileNTD(@NotNull Model model) {
        User editUngVien = new User();
        String tenUngVien = userService.getCurrentUsername();
        User ungVien = userService.findUserByUsername(tenUngVien);
        model.addAttribute("saveUngVien", editUngVien);
        model.addAttribute("ungVien", ungVien);
        return "user/profileUngVien";
    }

    @GetMapping("/editProfileUngVien/{id}")
    public String editProfileUngVien(@NotNull Model model, @PathVariable String id) {
        User editUngVien = new User();
        String tenUngVien = userService.getCurrentUsername();
        User ungVien = userService.findUserByUsername(tenUngVien);
        model.addAttribute("saveUngVien", editUngVien);
        model.addAttribute("ungVien", ungVien);
        return "user/editProfileUngVien";
    }

    @PostMapping("/saveEditUngVien")
    private String saveEditUngVien(@ModelAttribute RequestUpdateUngVien ungVien, @RequestParam String idUngVien){
        userService.saveEditUngVien(ungVien, idUngVien);
        return "redirect:/users/profileUngVien";
    }


}
