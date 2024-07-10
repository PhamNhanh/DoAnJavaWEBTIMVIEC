package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiTuyenDung {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String baiTuyenDungId;

    @Column(name = "tieu_de", nullable = true)
    private String tieuDe;

    @Column(name = "mo_ta_cong_viec", nullable = true)
    private String moTaCongViec;

    @Column(name = "yeu_cau_ky_nang", nullable = true)
    private String yeuCauKyNang;

    @Column(name = "muc_luongmin", nullable = true)
    private String mucLuongMin;

    @Column(name = "muc_luong_max", nullable = true)
    private String mucLuongMax;


    @Column(name = "is_active", nullable = true)
    private String isActive;

    @Column(name = "crate_at", nullable = true)
    private LocalDateTime create_at;

    @Column(name = "update_at", nullable = true)
    private LocalDateTime update_at;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = true)
    private User nhaTuyenDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thanhPhoId", nullable = true)
    private ThanhPho thanhPho;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = true)
    private User nhaTuyenDung;
*/


    @OneToMany(mappedBy = "baiTuyenDung", cascade = CascadeType.ALL)
    private List<DonUngTuyen> donUngTuyenList;

    // Getters and setters
}
