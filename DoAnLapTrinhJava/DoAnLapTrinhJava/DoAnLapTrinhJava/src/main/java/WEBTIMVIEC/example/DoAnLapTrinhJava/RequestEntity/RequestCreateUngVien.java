package WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateUngVien {
    private String username;
    private String password;
    private String email;
}
