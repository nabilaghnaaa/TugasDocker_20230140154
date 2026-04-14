package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private User dummyUser = new User("admin", "20230140154");

    // TAMBAHAN (buat nampung data)
    private List<String[]> dataMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        if (username.equals(dummyUser.getUsername()) &&
                password.equals(dummyUser.getPassword())) {

            model.addAttribute("data", dataMahasiswa); // TAMBAH
            return "home";
        } else {
            model.addAttribute("error", "Username atau password salah");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("data", dataMahasiswa); // TAMBAH
        return "home";
    }

    // TAMBAHAN ROUTE
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