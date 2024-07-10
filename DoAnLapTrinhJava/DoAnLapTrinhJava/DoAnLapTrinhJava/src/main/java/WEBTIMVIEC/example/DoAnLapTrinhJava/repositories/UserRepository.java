package WEBTIMVIEC.example.DoAnLapTrinhJava.repositories;

import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
    @Query("SELECT u.userId FROM User u WHERE u.username = ?1")
    String getUserIdByUsername(String username);
    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role(user_id, role_id) VALUE (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long userId, Long roleId);

    @Query("select u.countFail from User u where  u.username=?1")
    int countFail(String username);


}
