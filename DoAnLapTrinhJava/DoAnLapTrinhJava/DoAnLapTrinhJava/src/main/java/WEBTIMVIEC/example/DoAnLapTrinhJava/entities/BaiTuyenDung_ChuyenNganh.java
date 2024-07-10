package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiTuyenDung_ChuyenNganh {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String baiTuyenDungChuyenNganhId;

    @ManyToOne
    @JoinColumn(name = "baiTuyenDungId", nullable = false)
    private BaiTuyenDung baiTuyenDung;

    @ManyToOne
    @JoinColumn(name = "chuyenNganhId", nullable = false)
    private ChuyenNganh chuyenNganh;


}