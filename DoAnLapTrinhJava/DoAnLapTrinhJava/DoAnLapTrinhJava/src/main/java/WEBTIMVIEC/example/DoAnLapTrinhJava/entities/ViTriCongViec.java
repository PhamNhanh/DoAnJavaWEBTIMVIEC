package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ViTriCongViec")
public class ViTriCongViec {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String viTriCongViecId;

    @Column(name = "TenViTriCongViec", nullable = false)
    private String tenViTriCongViec;

}