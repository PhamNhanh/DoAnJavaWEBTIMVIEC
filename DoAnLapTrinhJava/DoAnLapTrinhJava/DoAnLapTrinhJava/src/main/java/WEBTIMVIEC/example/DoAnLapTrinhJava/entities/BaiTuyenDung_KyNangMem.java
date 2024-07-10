package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiTuyenDung_KyNangMem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String baiTuyenDung_KyNangMemid;

    @ManyToOne
    @JoinColumn(name = "baiTuyenDungId", nullable = false)
    private BaiTuyenDung baiTuyenDung;

    @ManyToOne
    @JoinColumn(name = "kyNangMemId", nullable = false)
    private KyNangMem kyNangMem;

}
