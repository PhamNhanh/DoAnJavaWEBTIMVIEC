package WEBTIMVIEC.example.DoAnLapTrinhJava.Service;

import WEBTIMVIEC.example.DoAnLapTrinhJava.RequestEntity.RequestRole;
import WEBTIMVIEC.example.DoAnLapTrinhJava.entities.Role;
import WEBTIMVIEC.example.DoAnLapTrinhJava.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    public Role getRoleById(String id) {
        return roleRepository.findById(id).get();
    }
    public Role createRole(RequestRole requestRole) {
        Role role = new Role();
        role.setName(requestRole.getRole_name());
        return roleRepository.save(role);
    }
}