package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonUngTuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String donUngTuyenId;

    @Column(name = "ho_ten_ung_vien", nullable = true)
    private String hoTen;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "url_cv", nullable = true)
    private String url_cv;

    @Column(name = "TrangThai", nullable = true)
    private String trangThai;

    @Column(name = "thu_gioi_thieu", nullable = true)
    private String thuGioiThieu;

    @Column(name = "create_at", nullable = true)
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "nhaTuyenDungId", nullable = false)
    private User nhaTuyenDung;

    @ManyToOne
    @JoinColumn(name = "ungVienId", nullable = false)
    private User ungVien;

    @ManyToOne
    @JoinColumn(name = "baiTuyenDungId", nullable = false)
    private BaiTuyenDung baiTuyenDung;
}
