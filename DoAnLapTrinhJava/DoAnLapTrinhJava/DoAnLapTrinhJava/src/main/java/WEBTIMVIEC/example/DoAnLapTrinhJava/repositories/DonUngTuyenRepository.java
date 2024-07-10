package WEBTIMVIEC.example.DoAnLapTrinhJava.repositories;


import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.DonUngTuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonUngTuyenRepository extends JpaRepository<DonUngTuyen, String> {
    // Add custom query methods if needed
}
