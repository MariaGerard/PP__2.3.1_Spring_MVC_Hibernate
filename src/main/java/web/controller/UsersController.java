package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showById(id));

        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showById(id));

        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id) {
        userService.updateUser(id, user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.removeUser(id);

        return "redirect:/users";

    }
}
