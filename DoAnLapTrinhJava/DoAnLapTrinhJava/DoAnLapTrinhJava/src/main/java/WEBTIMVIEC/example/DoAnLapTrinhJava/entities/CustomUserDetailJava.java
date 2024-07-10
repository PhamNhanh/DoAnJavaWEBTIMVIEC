package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomUserDetailJava implements UserDetails {
    private User user;

    public CustomUserDetailJava(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listAuth = new ArrayList<>();
        for (String role: BuildRolesFromRole(user.getRole().getName())){
            listAuth.add(new SimpleGrantedAuthority(role));
        }
        return listAuth;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getDisplayName() {
        return user.getUsername();
    }
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
    public List<String> BuildRolesFromRole(String role){
        String roles = "ADMIN,NTD,UNGVIEN";
        int index = role.indexOf(role);
        return Arrays.stream(roles.substring(index).split(",")).toList();
    }


}
