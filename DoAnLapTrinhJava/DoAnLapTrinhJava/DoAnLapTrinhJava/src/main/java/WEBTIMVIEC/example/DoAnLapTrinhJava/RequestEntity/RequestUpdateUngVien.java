package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateUngVien {
    private String email;
    private String tenUngVien;
    private String avatarUrl;
    private String sdt;
    private String diaChi;
}
