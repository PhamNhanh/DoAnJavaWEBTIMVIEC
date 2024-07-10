package WEBTIMVIEC.example.DoAnLapTrinhJava.repositories;

import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r FROM Role r where r.name=?1 ")
    Role findOneByName(String name);
    Role findRolesById(String id);
    @Query("SELECT r.id FROM Role r WHERE r.name =?1")
    String getRoleIdByName(String roleName);
}
