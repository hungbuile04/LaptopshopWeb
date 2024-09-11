package vn.hungbuishop.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import vn.hungbuishop.laptopshop.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User save(User user);
    void deleteById(Long id);
    List<User> findOneByEmail(String email);
    List<User> findAll();
    User findById(long id);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
