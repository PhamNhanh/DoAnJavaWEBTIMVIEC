package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDonUngTuyenUpdate {
    private String donUngTuyenId;
    private String hoTen;
    private String email;
    private String url_cv;
    private String trangThai;
    private String thuGioiThieu;
}
