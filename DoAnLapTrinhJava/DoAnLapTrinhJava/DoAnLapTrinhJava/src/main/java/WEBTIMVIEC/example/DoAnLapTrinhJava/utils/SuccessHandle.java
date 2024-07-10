package WEBTIMVIEC.example.DoAnLapTrinhJava.utils;

import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.CustomUserDetailJava;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class SuccessHandle extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    public SuccessHandle(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = ((CustomUserDetailJava)authentication.getPrincipal()).getUser();
        if(user.isEnabled()){
            userService.resetLockAccount(user);
        }else{
            if(user.getLockExpired().getTime()<System.currentTimeMillis()){
                throw new ServletException("User is locked");
            }else{
                userService.resetLockAccount(user);
            }
        }

        //Lazyload nothing
        String user1 = userService.getCurrentUsername();
        User user2 = userService.findUserByUsername(user1);
        String userEmailAddress = user2.getEmail(); // Thay bằng địa chỉ email của người dùng đã đăng nhập
        sendLoginSuccessEmail(userEmailAddress);
        if ("UNGVIEN".equals(user2.getRole().getName().toString())) {
            setDefaultTargetUrl("/");
        } else if ("NTD".equals(user2.getRole().getName().toString())) {
            setDefaultTargetUrl("/baiTuyenDungs");
        } else {
            setDefaultTargetUrl("/donUngTuyens");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void sendLoginSuccessEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Đăng nhập thành công");
        message.setText("Bạn vừa đăng nhập thành công vào hệ thống của chúng tôi.");

        mailSender.send(message);
    }

}
