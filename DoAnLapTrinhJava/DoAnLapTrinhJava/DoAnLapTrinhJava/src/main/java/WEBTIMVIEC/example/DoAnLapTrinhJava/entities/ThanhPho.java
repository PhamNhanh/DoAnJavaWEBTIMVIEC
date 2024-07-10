package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhPho {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String thanhPhoId;

    @Column(name = "TenThanhPho", nullable = false)
    private String tenThanhPho;

    @OneToMany(mappedBy = "thanhPho", cascade = CascadeType.ALL)
    private List<BaiTuyenDung> baiTuyenDungList;



}