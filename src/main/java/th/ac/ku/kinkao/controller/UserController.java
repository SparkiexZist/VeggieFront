package th.ac.ku.kinkao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.kinkao.model.User;
import th.ac.ku.kinkao.model.Vegetable;
import th.ac.ku.kinkao.service.UserService;
import th.ac.ku.kinkao.service.VegetableService;

@Controller
@RequestMapping("/user")
public class UserController
{
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService service;


    // Get ที่ไม่ได้ใส่อะไรคือดึงมาจากหน้า "/restaurant" และต้องมี Model เพราะต้องส่งตัวแปรอาร์เรย์ลิชไปให้
    @GetMapping
    public String getUsers(Model model)
    {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping("/add")
    public String getAddForm(){
        // return vegetable-add.html
        return "user-register";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addUser(user);

        return "redirect:/user";
    }
}
