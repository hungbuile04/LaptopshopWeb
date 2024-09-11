package vn.hungbuishop.laptopshop.controller.admin;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;

import vn.hungbuishop.laptopshop.domain.Role;
import vn.hungbuishop.laptopshop.domain.User;
import vn.hungbuishop.laptopshop.repository.RoleRepository;
import vn.hungbuishop.laptopshop.service.UploadService;
import vn.hungbuishop.laptopshop.service.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private UserRepository userRepository;

    public UserController(UserService userService, UploadService uploadService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
       // this.userRepository = userRepository;
        this.uploadService = uploadService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user, @RequestParam("avtFile") MultipartFile file) throws IOException {
        String avatar = this.uploadService.handleUPloadFile(file,"avatar");
        System.out.println("run here" + user);

        Role role = this.userService.getRoleByName(user.getRole().getName());

        String hashedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());

        // Gán lại role từ cơ sở dữ liệu vào đối tượng User
        user.setRole(role);
        user.setPassword(hashedPassword);
        user.setAvatar(avatar);
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("admin/user")
    public String getUserList(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @RequestMapping("admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable Long id) {
        User user = this.userService.getUserByID(id);
        model.addAttribute("id", id);
        model.addAttribute("name", user.getFullName());
        model.addAttribute("email", user.getEmail());
        return "admin/user/detail";
    }

    @RequestMapping("admin/user/update/{id}")
    public String updateUserDetail(Model model, @PathVariable Long id) {
        User user = this.userService.getUserByID(id);
        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("admin/user/update")
    public String updateUser(Model model, @ModelAttribute("newUser") User user) {
        User currentUser = this.userService.getUserByID(user.getId());
        if(currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            currentUser.setAddress(user.getAddress());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("admin/user/delete/{id}")
    public String deleteUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        User user = this.userService.getUserByID(id);
        model.addAttribute("User", user);
        return "admin/user/delete";
    }

    @PostMapping("admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("User") User user) {
        this.userService.deleteUserByID(user.getId());
        return "redirect:/admin/user";
    }

}
