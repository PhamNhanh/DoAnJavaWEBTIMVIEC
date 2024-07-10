package WEBTIMVIEC.example.DoAnLapTrinhJava.repositories;


import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.BaiTuyenDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiTuyenDungRepository extends JpaRepository<BaiTuyenDung, String> {
    // Add custom query methods if needed
}
