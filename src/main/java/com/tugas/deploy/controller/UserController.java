package com.tugas.deploy.controller;

import com.tugas.deploy.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private List<String[]> dataMahasiswa = new ArrayList<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userService.saveDefaultUser();
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        if (userService.login(username, password)) {
            model.addAttribute("data", dataMahasiswa);
            return "home";
        } else {
            model.addAttribute("error", "Username atau password salah");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("data", dataMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/addMahasiswa")
    public String addMahasiswa(@RequestParam String nama,
                               @RequestParam String nim,
                               @RequestParam String jk,
                               Model model) {

        dataMahasiswa.add(new String[]{nama, nim, jk});
        model.addAttribute("data", dataMahasiswa);

        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}

//done