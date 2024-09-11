package vn.hungbuishop.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hungbuishop.laptopshop.domain.Role;
import vn.hungbuishop.laptopshop.domain.User;
import vn.hungbuishop.laptopshop.domain.dto.RegisterDTO;
import vn.hungbuishop.laptopshop.repository.RoleRepository;
import vn.hungbuishop.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "Hello service";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserByID(Long id) { return this.userRepository.findById(id).orElse(null); }

    public void deleteUserByID(Long id) { this.userRepository.deleteById(id); }

    public Role getRoleByName(String name) { return this.roleRepository.findByName(name); }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName()+" "+registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkExistsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
