package WEBTIMVIEC.example.DoAnLapTrinhJava.repositories;

import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.Role;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.ThanhPho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhPhoRepository extends JpaRepository<ThanhPho, String> {
}
