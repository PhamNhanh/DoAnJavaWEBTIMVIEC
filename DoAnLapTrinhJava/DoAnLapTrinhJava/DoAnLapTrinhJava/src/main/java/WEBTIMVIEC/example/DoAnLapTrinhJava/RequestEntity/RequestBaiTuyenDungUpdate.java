package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBaiTuyenDungUpdate {
    private String baiTuyenDungId;
    private String tieuDe;
    private String mucLuongMin;
    private String mucLuongMax;
    private String moTaCongViec;
    private String yeuCauKyNang;
    private String isActive;
    // Getters and setters
}
