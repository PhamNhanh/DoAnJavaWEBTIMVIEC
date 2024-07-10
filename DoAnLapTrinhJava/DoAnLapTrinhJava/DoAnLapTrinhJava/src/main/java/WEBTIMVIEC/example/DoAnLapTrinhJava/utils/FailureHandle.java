package WEBTIMVIEC.example.DoAnLapTrinhJava.utils;

import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

@Configuration
public class FailureHandle extends SimpleUrlAuthenticationFailureHandler {
   @Autowired
   private UserService userService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        User user =userService.findUserByUsername(username);
        if(user != null) {
            userService.UpdateCountFail(user);
        }
        System.out.println("That bai");
        super.onAuthenticationFailure(request, response, exception);
    }
}
