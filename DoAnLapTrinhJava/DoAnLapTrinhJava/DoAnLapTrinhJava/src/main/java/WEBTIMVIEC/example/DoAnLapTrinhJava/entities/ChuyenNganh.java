package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenNganh {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String chuyenNganhId;

    @Column(name = "TenChuyenNganh", nullable = false)
    private String tenChuyenNganh;

}