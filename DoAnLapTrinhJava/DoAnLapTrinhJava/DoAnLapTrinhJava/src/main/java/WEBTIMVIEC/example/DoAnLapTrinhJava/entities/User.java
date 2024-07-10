package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    //Admin
    //@Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    //private String userId; // Sử dụng String cho ID
    @Column(name = "TenAdmin", nullable = true)
    private String tenAdmin;

    //UngVien
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId; // Sử dụng String cho ID

    @Column(name = "username", length = 50, unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50characters")
    private String username;

    @Column(name = "password", length = 250)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", length = 50, unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "TenUngVien", nullable = true)
    private String tenUngVien;

    @Column(name = "AvatarUrl", nullable = true)
    private String avatarUrl;


    @Column(name = "SDT", nullable = true)
    private String sdt;

    @Column(name = "DiaChi", nullable = true)
    private String diaChi;



    //NhaTuyenDung
    //@Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    //private String NhaTuyenDungId;

    @Column(name = "tenCongTy", nullable = true)
    private String tenCongTy;

    //@Column(name = "DiaChi", nullable = false)
    //private String diaChi;

    @Column(name = "Logo_Url", nullable = true)
    private String logoUrl;

/*    @Column(name = "SDT", nullable = false)
    private String sdt;*/

    @Column(name = "Website", nullable = true)
    private String website;
    @Column(name = "enabled", nullable = true)
    private boolean enabled;
    @Column(name = "countFail", nullable = true)
    private int countFail;
    @Column(name = "lockExpired", nullable = true)
    private Date lockExpired;

    private String resetPasswordToken;
    private Date resetPasswordTokenExpired;
    //@Column(name = "Email", nullable = false)
    //private String email;
    //@Column(name = "Password", nullable = false)
    //private String password;


    @OneToMany(mappedBy = "ungVien")
    private List<HocVan> hocVanList;
    @OneToMany(mappedBy = "ungVien")
    private List<DonUngTuyen> donUngTuyenList;
    @OneToMany(mappedBy = "nhaTuyenDung")
    private List<BaiTuyenDung> baiTuyenDungList;
    //@OneToMany(mappedBy = "nhaTuyenDung")
    //private List<DonUngTuyen> donUngTuyenList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
