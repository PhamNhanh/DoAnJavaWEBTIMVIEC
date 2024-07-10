package WEBTIMVIEC.example.DoAnLapTrinhJava.Service;

import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestCreateNhaTuyenDung;


import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestCreateUngVien;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestUpdateUngVien;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.Role;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.RoleRepository;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.UserRepository;

import WEBTIMVIEC.example.DoAnLapTrinhJava.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User CreateUngVien(RequestCreateUngVien requestCreateUngVien) {
        try {
            User user = new User();
            user.setUsername(requestCreateUngVien.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(requestCreateUngVien.getPassword()));
            user.setEmail(requestCreateUngVien.getEmail());
            user.setEnabled(true);
            Role role = roleRepository.findOneByName("UNGVIEN");
            user.setRole(role);
            return userRepository.save(user);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }
    public void resetLockAccount(User user){
        user.setCountFail(0);
        user.setLockExpired(null);
        userRepository.save(user);
    }

    public void UpdateCountFail(User user) {
        if (user.isEnabled()) {
            int count = userRepository.countFail(user.getUsername());
            count += 1;
            user.setCountFail(count);
            if (count == 4) {
                user.setEnabled(false);
                user.setCountFail(0);
                user.setLockExpired(new Date(System.currentTimeMillis() + 10 * 2 * 1000));
            }
        } else {
            if (user.getLockExpired() != null) {
                if (user.getLockExpired().getTime() < System.currentTimeMillis()) {
                    user.setLockExpired(null);
                    user.setEnabled(true);
                }
            }
        }
        userRepository.save(user);
    }

    public User saveEditUngVien(RequestUpdateUngVien requestUpdateUngVien, String idUngVien) {
        try {
            User user = userRepository.findById(idUngVien).get();
            user.setEmail(requestUpdateUngVien.getEmail());
            user.setDiaChi(requestUpdateUngVien.getDiaChi());
            user.setSdt(requestUpdateUngVien.getSdt());
            user.setEnabled(true);
            user.setTenUngVien(requestUpdateUngVien.getTenUngVien());
            Role role = roleRepository.findOneByName("UNGVIEN");
            user.setRole(role);
            return userRepository.save(user);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    public User CreateNhaTuyenDung(RequestCreateNhaTuyenDung requestCreateNhaTuyenDung) {
        try {
            User user = new User();
            user.setUsername(requestCreateNhaTuyenDung.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(requestCreateNhaTuyenDung.getPassword()));
            user.setEmail(requestCreateNhaTuyenDung.getEmail());
            user.setSdt(requestCreateNhaTuyenDung.getSoDienThoai());
            user.setEnabled(true);
            user.setTenCongTy(requestCreateNhaTuyenDung.getTenCongTy());
            Role role = roleRepository.findOneByName("NTD");
            user.setRole(role);
            return userRepository.save(user);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User createTokenResetPassword(User user) {
        String token = Utility.generateRandomString(45);
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpired(new Date(System.currentTimeMillis()+10*60*1000));
        return userRepository.save(user);
    }
    public User ChangePassword(User user, String password){
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return userRepository.save(user);
    }


        @Transactional
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public User getCurrentUser() {
        String username = getCurrentUsername();
        return userRepository.findByUsername(username);
    }


    public Optional<User> findByUserid(Long id) throws
            UsernameNotFoundException {
        Optional<User> user = userRepository.findById(id.toString());
        return user;
    }

    @Transactional
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }





}
