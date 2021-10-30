package th.ac.ku.kinkao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.kinkao.model.Order;
import th.ac.ku.kinkao.model.Vegetable;
import th.ac.ku.kinkao.model.Cart;

import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class OrderService
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    private List<Order> cart = new ArrayList<>();

    public List<Order> getAll() {
        String url = "http://localhost:8090/order";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        Order[] vegetables = response.getBody();
        return Arrays.asList(vegetables);
    }

    public void addOrder(Order vegetable) {
        String url = "http://localhost:8090/order";
        Order vegOrder = new Order();
        vegOrder = vegetable;
        vegOrder.setUsername(userService.getUser().getUsername());
        vegOrder.setVegetable(cartService.getCart().toString());
        vegOrder.setStatus("Underpayment");
        restTemplate.postForObject(url, vegOrder, Order.class);
    }
    public void OrderConfig(){
        cart = this.getAll();
        for (int i=0 ; i < this.getAll().size();i++){
            String hee = new String("");
            hee = this.getAll().get(i).getVegetable();
            hee = hee.replace("[","").replace("]","");
            hee = hee.replace("{","[").replace("}","]");
            hee = hee.replace("[","").replace("]","");
            String[] split = hee.split(",");
            List<String> list = Arrays.asList(split);
            for (int j = 0;j < list.size();j++){
                String hee2 =list.get(j);
                split = list.get(j).trim().split("->");
                List<String> list1 = Arrays.asList(split);
                for (int k =0;k<list1.size();k++){
//                    System.out.println(list1.get(k).trim());
                }
                Vegetable vegetable = new Vegetable(UUID.fromString(list1.get(0)),list1.get(1),Double.parseDouble(list1.get(2)),Double.parseDouble(list1.get(3)),parseInt(list1.get(4)));
                cart.get(i).add(new Cart(vegetable,parseInt(list1.get(5))));
            }
        }
    }
    public List<Order> getDummy(String name){ //getall
        this.OrderConfig();
        List<Order> cart2 = new ArrayList<>();
        if (name.equals("admin")){
            return cart;
        }
        else {
            for (int i =0; i<cart.size();i++){
                if (name.equals(cart.get(i).getUsername())){
                    cart2.add(cart.get(i));
                }
            }
        }
        return cart2;
    }
    public Order getDummyByID(UUID id){
        Order vegOrder = new Order();
        for (int i=0;i < cart.size();i++){
            if (cart.get(i).getOrder_Id().equals(id)){
                vegOrder = cart.get(i);
                return vegOrder;
            }
        }
        return vegOrder;
    }
    public Order getOneById(UUID id)
    {
        String url = "http://localhost:8090/order/" + id;
        ResponseEntity<Order> response =
                restTemplate.getForEntity(url, Order.class);
        Order vegetable = response.getBody();
        return vegetable;
    }
    public void update(Order vegetable) {
        String url = "http://localhost:8090/order/" + vegetable.getOrder_Id();
        restTemplate.put(url, vegetable, Order.class);
    }
    public void delete(Order vegetable) {
        String url = "http://localhost:8090/order/" + vegetable.getOrder_Id();
        restTemplate.delete(url, vegetable, Order.class);
    }

}
