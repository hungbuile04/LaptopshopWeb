
package vn.hungbuishop.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ProductController {
    @GetMapping("/admin/product")
    public String getDashboard() {
        return "admin/product/show";
    }

}