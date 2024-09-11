package vn.hungbuishop.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hungbuishop.laptopshop.domain.Role;
import vn.hungbuishop.laptopshop.domain.User;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role save(Role role);
    Role findByName(String name);
}
