package WEBTIMVIEC.example.DoAnLapTrinhJava.validators;


import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.validators.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {

    private UserService userService;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userService == null)
            return true;
        return userService.findUserByUsername(username) == null;
    }
}
