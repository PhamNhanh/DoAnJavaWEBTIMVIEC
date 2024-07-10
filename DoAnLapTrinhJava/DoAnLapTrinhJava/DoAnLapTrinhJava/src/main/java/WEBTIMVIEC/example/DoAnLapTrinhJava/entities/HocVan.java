package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HocVan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hocVanId; // Sử dụng String cho ID

    @Column(name = "GPA", nullable = false)
    private Double gpa;

    @Column(name = "NgayBatDau", nullable = false)
    private Date ngayBatDau;

    @Column(name = "NgayTotNghiep", nullable = false)
    private Date ngayTotNghiep;

    @Column(name = "LoaiTotNghiep", nullable = false)
    private String loaiTotNghiep;

    @ManyToOne
    @JoinColumn(name = "truongDaiHocId", nullable = false)
    private TruongDaiHoc truongDaiHoc;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User ungVien;
}
