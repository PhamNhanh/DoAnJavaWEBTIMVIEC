package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruongDaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String truongDaiHocId;

    @Column(name = "TenTruong", nullable = false)
    private String tenTruong;

    // Các cột khác nếu có
}
