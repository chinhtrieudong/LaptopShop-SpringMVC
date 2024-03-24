package vn.hoidanit.laptopshop.controller;

import org.springframework.ui.Model;
import vn.hoidanit.laptopshop.domain.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Home page
    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("Chinh", "from controller with model");
        return "hello";
    }

    // Get all user
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    // Get user by Id
    @RequestMapping("/admin/user/{userId}")
    public String getUserDetailPage(Model model, @PathVariable long userId) {
        model.addAttribute("newUser", new User());
        User userInfo = this.userService.getUserById(userId);
        model.addAttribute("userInfo", userInfo);
        return "admin/user/user-detail";
    }

    // Create user Page
    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // Create user
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    // Remove user
    @RequestMapping("/admin/user/deleteByUserId/{userId}")
    public String removeUserById(Model model, @PathVariable long userId) {
        System.out.println("User id" + userId);
        this.userService.deleteUserById(userId);
        return "redirect:/admin/user";
    }

    // Update user page
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User curUser = this.userService.getUserById(id);
        model.addAttribute("updateUser", curUser);
        return "/admin/user/update";
    }

    // Update user
    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User hoidanit) {
        User curUser = this.userService.getUserById(hoidanit.getId());
        if (curUser != null) {
            curUser.setFullName(hoidanit.getFullName());
            curUser.setPhone(hoidanit.getPhone());
            curUser.setAddress(hoidanit.getAddress());

            this.userService.handleSaveUser(curUser); // auto update when exist.
        }
        return "redirect:/admin/user";
    }

}
