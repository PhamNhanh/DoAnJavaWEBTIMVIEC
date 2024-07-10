package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBaiTuyenDung {
    private String tieuDe;
    private String moTaCongViec;
    private String mucLuongMin;
    private String mucLuongMax;
    private String yeuCauKyNang;
    private String thanhPhoId;
}
