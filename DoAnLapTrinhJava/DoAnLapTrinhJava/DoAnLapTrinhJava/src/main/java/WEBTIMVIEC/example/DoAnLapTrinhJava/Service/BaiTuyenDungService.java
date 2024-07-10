package WEBTIMVIEC.example.DoAnLapTrinhJava.Service;


import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.CreateBaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestBaiTuyenDungUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.BaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.ThanhPho;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.BaiTuyenDungRepository;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.ThanhPhoRepository;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BaiTuyenDungService {

    @Autowired
    private BaiTuyenDungRepository baiTuyenDungRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ThanhPhoRepository thanhPhoRepository;

    public List<BaiTuyenDung> getAllBaiTuyenDung() {
        return baiTuyenDungRepository.findAll();
    }

    public Optional<BaiTuyenDung> getBaiTuyenDungById(String id) {
        return baiTuyenDungRepository.findById(id);
    }

    public BaiTuyenDung saveBaiTuyenDung(CreateBaiTuyenDung createBaiTuyenDung) {
        try{
            BaiTuyenDung baiTuyenDung = new BaiTuyenDung();
            Optional<ThanhPho> thanhPho = thanhPhoRepository.findById(createBaiTuyenDung.getThanhPhoId());
            String tenNhaTuyenDung = userService.getCurrentUsername();
            Optional<User> nhaTuyenDung = Optional.ofNullable(userRepository.findByUsername(tenNhaTuyenDung));
            baiTuyenDung.setTieuDe(createBaiTuyenDung.getTieuDe());
            baiTuyenDung.setYeuCauKyNang(createBaiTuyenDung.getYeuCauKyNang());
            baiTuyenDung.setMucLuongMin(createBaiTuyenDung.getMucLuongMin());
            baiTuyenDung.setMucLuongMax(createBaiTuyenDung.getMucLuongMax());
            baiTuyenDung.setMoTaCongViec(createBaiTuyenDung.getMoTaCongViec());
            baiTuyenDung.setNhaTuyenDung(nhaTuyenDung.get());
            baiTuyenDung.setThanhPho(thanhPho.get());
            baiTuyenDung.setCreate_at(LocalDateTime.now());
            baiTuyenDung.setUpdate_at(LocalDateTime.now());
            baiTuyenDung.setIsActive("True");
            return baiTuyenDungRepository.save(baiTuyenDung);
        }catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());

        }


    }
    public BaiTuyenDung updateBaiTuyenDung(RequestBaiTuyenDungUpdate requestBaiTuyenDungUpdate, String idThanhPho) {
        try{
            BaiTuyenDung baiTuyenDung = baiTuyenDungRepository.findById(requestBaiTuyenDungUpdate.getBaiTuyenDungId()).get();
            Optional<ThanhPho> thanhPho = thanhPhoRepository.findById(idThanhPho);
            String tenNhaTuyenDung = userService.getCurrentUsername();
            Optional<User> nhaTuyenDung = Optional.ofNullable(userRepository.findByUsername(tenNhaTuyenDung));
            baiTuyenDung.setBaiTuyenDungId(requestBaiTuyenDungUpdate.getBaiTuyenDungId());
            baiTuyenDung.setTieuDe(requestBaiTuyenDungUpdate.getTieuDe());
            baiTuyenDung.setMoTaCongViec(requestBaiTuyenDungUpdate.getMoTaCongViec());
            baiTuyenDung.setYeuCauKyNang(requestBaiTuyenDungUpdate.getYeuCauKyNang());
            baiTuyenDung.setMucLuongMin(requestBaiTuyenDungUpdate.getMucLuongMin());
            baiTuyenDung.setMucLuongMax(requestBaiTuyenDungUpdate.getMucLuongMax());
            baiTuyenDung.setNhaTuyenDung(nhaTuyenDung.get());
            baiTuyenDung.setThanhPho(thanhPho.get());
            baiTuyenDung.setUpdate_at(LocalDateTime.now());
            baiTuyenDung.setIsActive(requestBaiTuyenDungUpdate.getIsActive());
            return baiTuyenDungRepository.save(baiTuyenDung);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }

    public void deleteBaiTuyenDung(String id) {
        baiTuyenDungRepository.deleteById(id);
    }
}