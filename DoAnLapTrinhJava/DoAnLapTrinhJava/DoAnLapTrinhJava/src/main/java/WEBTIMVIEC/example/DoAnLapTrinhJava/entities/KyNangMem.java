package WEBTIMVIEC.example.DoAnLapTrinhJava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KyNangMem")
public class KyNangMem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String kyNangMemId;

    @Column(name = "TenKyNangMem", nullable = false)
    private String tenKyNangMem;




}
