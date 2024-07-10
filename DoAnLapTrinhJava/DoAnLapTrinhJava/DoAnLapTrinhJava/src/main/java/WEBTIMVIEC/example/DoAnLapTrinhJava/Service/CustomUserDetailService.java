package WEBTIMVIEC.example.DoAnLapTrinhJava.Service;

import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.CustomUserDetailJava;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetailJava loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetailJava(user);
    }
}
