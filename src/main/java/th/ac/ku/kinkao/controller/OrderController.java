package th.ac.ku.kinkao.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import th.ac.ku.kinkao.model.Order;
import th.ac.ku.kinkao.service.OrderService;
import th.ac.ku.kinkao.service.CartService;
import th.ac.ku.kinkao.service.VegetableService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController
{

    @Autowired
    private OrderService service;
    private List<Order> cart = new ArrayList<>();
    @Autowired
    private VegetableService vegetableService;

//    @GetMapping("/edit/{id}")
//    public String getEditForm(@PathVariable UUID id, Model model) {
//        Order order = service.getOneById(id);
//        model.addAttribute("order", order);
//        return "order-edit";
//    }
//
//    @PostMapping("/edit")
//    public String edit(@ModelAttribute Order order, Model model) {
//        service.update(order);
//        return "redirect:/order";
//    }

    // Get ที่ไม่ได้ใส่อะไรคือดึงมาจากหน้า "/restaurant" และต้องมี Model เพราะต้องส่งตัวแปรอาร์เรย์ลิชไปให้
    @GetMapping
    public String getCheckPage(Model model, Authentication authentication){
        model.addAttribute("veglist", service.getDummy(authentication.getName()));
        return "orders";
    }

    @GetMapping("/add")
    public String getAddForm(){
        // return vegetable-add.html
        return "order-add";
    }
    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addOrder(order);

        return "redirect:/order";
    }

    @GetMapping("/list/edit/{id}")
    public String editPayment(@PathVariable UUID id, Model model,Authentication authentication){
        System.out.println(service.getDummyByID(id).getCartList());
        Order set = service.getOneById(id);
        Calendar calndr = Calendar.getInstance();
        set.setPayment(calndr.getTime());
        set.setStatus("Payment");
        vegetableService.updateCart(service.getDummyByID(id).getCartList());
        service.update(set);
        return "redirect:/order";
    }
    @GetMapping("/list/remove/{id}")
    public String removePayment(@PathVariable UUID id, Model model,Authentication authentication){
        Order set = service.getOneById(id);
        service.delete(set);
        return "redirect:/order";
    }


}
