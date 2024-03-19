package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/user")
    public String userPage() {
        return "User page.";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin page.";
    }
}
