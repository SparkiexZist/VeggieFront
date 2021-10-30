package th.ac.ku.kinkao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController
{
    @RequestMapping("/contact")
    // คืนค่าเป็น str เสมอ เพราะจะคืนค่าชื่อไฟล์ที่เป็นเทมเพลต
    public String getContact(Model model) {

        return "contact";
    }
}
