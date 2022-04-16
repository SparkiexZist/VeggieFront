package th.ac.ku.kinkao.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import th.ac.ku.kinkao.model.Vegetable;
import th.ac.ku.kinkao.service.VegetableService;
import th.ac.ku.kinkao.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/vegetable")
public class VegetableController {

    @Autowired
    private VegetableService service;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Vegetable vegetable = service.getOneById(id);
        model.addAttribute("vegetable", vegetable);
        return "vegetable-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Vegetable vegetable, Model model) {
        service.update(vegetable);
        return "redirect:/vegetable";
    }

    // Get ที่ไม่ได้ใส่อะไรคือดึงมาจากหน้า "/restaurant" และต้องมี Model เพราะต้องส่งตัวแปรอาร์เรย์ลิชไปให้
    @GetMapping
    public String getVegetables(Model model,Authentication authentication)
    {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("vegetables", service.getAll());
        return "vegetables";
    }

    @GetMapping("/add")
    public String getAddForm(){
        // return vegetable-add.html
        return "vegetable-add";
    }
    @PostMapping("/add")
    public String addVegetable(@ModelAttribute Vegetable vegetable, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addVegetable(vegetable);

        return "redirect:/vegetable";
    }

}
