package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDonUngTuyen {
    private String hoTen;
    private String email;
    private String url_cv;
    private String trangThai;
    private String thuGioiThieu;
    private String idBaiTuyenDung;
}
