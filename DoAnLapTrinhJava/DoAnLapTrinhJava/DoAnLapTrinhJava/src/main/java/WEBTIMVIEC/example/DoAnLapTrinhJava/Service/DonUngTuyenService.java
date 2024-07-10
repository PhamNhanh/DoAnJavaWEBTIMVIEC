package WEBTIMVIEC.example.DoAnLapTrinhJava.Service;


import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.CreateBaiTuyenDung;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.CreateDonUngTuyen;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestBaiTuyenDungUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestDonUngTuyenUpdate;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.*;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonUngTuyenService {

    @Autowired
    private DonUngTuyenRepository donUngTuyenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BaiTuyenDungRepository baiTuyenDungRepository;
    @Autowired
    private UserService userService;

    public List<DonUngTuyen> getAllDonUngTuyen() {
        return donUngTuyenRepository.findAll();
    }

    public Optional<DonUngTuyen> getDonUngTuyenById(String id) {
        return donUngTuyenRepository.findById(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = {Exception.class, Throwable.class})
    public DonUngTuyen saveDonUngTuyen(CreateDonUngTuyen createDonUngTuyen, String idBaiTuyenDung) {
        try {
            DonUngTuyen donUngTuyen = new DonUngTuyen();

            // Lấy thông tin ứng viên hiện tại
            String tenUngVien = userService.getCurrentUsername();
            Optional<User> ungVien = Optional.ofNullable(userRepository.findByUsername(tenUngVien));
            if (ungVien.isEmpty()) {
                throw new RuntimeException("Current user not found: " + tenUngVien);
            }

            // Lấy thông tin bài tuyển dụng
            Optional<BaiTuyenDung> baiTuyenDungOpt = baiTuyenDungRepository.findById(idBaiTuyenDung);
            if (baiTuyenDungOpt.isEmpty()) {
                throw new RuntimeException("Bai Tuyen Dung not found with id: " + idBaiTuyenDung);
            }
            BaiTuyenDung baiTuyenDung = baiTuyenDungOpt.get();

            // Lấy thông tin nhà tuyển dụng
            Optional<User> nhaTuyenDung = userRepository.findById(baiTuyenDung.getNhaTuyenDung().getUserId());
            if (nhaTuyenDung.isEmpty()) {
                throw new RuntimeException("Nha Tuyen Dung not found for Bai Tuyen Dung with id: " + idBaiTuyenDung);
            }

            // Thiết lập thông tin đơn ứng tuyển
            donUngTuyen.setHoTen(createDonUngTuyen.getHoTen());
            donUngTuyen.setEmail(createDonUngTuyen.getEmail());
            donUngTuyen.setThuGioiThieu(createDonUngTuyen.getThuGioiThieu());
            donUngTuyen.setUrl_cv(createDonUngTuyen.getUrl_cv());
            donUngTuyen.setUngVien(ungVien.get());
            donUngTuyen.setBaiTuyenDung(baiTuyenDung);
            donUngTuyen.setNhaTuyenDung(nhaTuyenDung.get());
            donUngTuyen.setCreateAt(LocalDateTime.now());
            donUngTuyen.setUpdateAt(LocalDateTime.now());
            donUngTuyen.setTrangThai("Chưa duyệt");

            // Lưu đơn ứng tuyển và trả về kết quả
            return donUngTuyenRepository.save(donUngTuyen);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Don Ung Tuyen: " + e.getMessage());
        }
    }




  /*  public DonUngTuyen updateDonUngTuyen(RequestDonUngTuyenUpdate requestDonUngTuyenUpdate) {
        try{
            DonUngTuyen donUngTuyen = donUngTuyenRepository.findById(requestDonUngTuyenUpdate.getDonUngTuyenId()).get();
            String tamNTD = "91dae22f-5cc3-4f58-8438-b0a169e190ae";
            String tenNhaTuyenDung = userService.getCurrentUsername();
            Optional<User> nhaTuyenDung = Optional.ofNullable(userRepository.findByUsername(tenNhaTuyenDung));
            String tamBTD ="01135c43-52e3-4915-80fb-655b816d8f47";
            Optional<User> nhaTuyenDung = userRepository.findById(tamNTD);
            Optional<User> ungVien = userRepository.findById(tamUV);
            Optional<BaiTuyenDung> baiTuyenDung = baiTuyenDungRepository.findById(tamBTD);
            donUngTuyen.setHoTen(requestDonUngTuyenUpdate.getHoTen());
            donUngTuyen.setEmail(requestDonUngTuyenUpdate.getEmail());
            donUngTuyen.setThuGioiThieu(requestDonUngTuyenUpdate.getThuGioiThieu());
            donUngTuyen.setUrl_cv(requestDonUngTuyenUpdate.getUrl_cv());
            donUngTuyen.setUngVien(ungVien.get());
            donUngTuyen.setBaiTuyenDung(baiTuyenDung.get());
            donUngTuyen.setNhaTuyenDung(nhaTuyenDung.get());
            donUngTuyen.setCreateAt(donUngTuyen.getCreateAt());
            donUngTuyen.setUpdateAt(LocalDateTime.now());
            donUngTuyen.setTrangThai("Chưa duyệt");
            return donUngTuyenRepository.save(donUngTuyen);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

    }*/

    public void deleteDonUngTuyen(String id) {
        donUngTuyenRepository.deleteById(id);
    }
}