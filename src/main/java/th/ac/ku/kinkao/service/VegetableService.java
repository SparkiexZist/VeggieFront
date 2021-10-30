package th.ac.ku.kinkao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.kinkao.model.Cart;
import th.ac.ku.kinkao.model.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class VegetableService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Vegetable> getAll() {
        String url = "http://localhost:8090/vegetable";
        ResponseEntity<Vegetable[]> response = restTemplate.getForEntity(url, Vegetable[].class);
        Vegetable[] vegetables = response.getBody();
        return Arrays.asList(vegetables);
    }

    public List<Cart> getOrder(){
        String url = "http://localhost:8090/vegetable";
        ResponseEntity<Vegetable[]> response = restTemplate.getForEntity(url, Vegetable[].class);
        Vegetable[] vegetables = response.getBody();
        ArrayList orders = new ArrayList();
        for (int i=0; i < vegetables.length;i++){
            orders.add(new Cart(vegetables[i],0));
        }
        return orders;
    }

    public void addVegetable(Vegetable vegetable) {
        String url = "http://localhost:8090/vegetable";

        restTemplate.postForObject(url, vegetable, Vegetable.class);
    }

    public Vegetable getOneById(UUID id)
    {
        String url = "http://localhost:8090/vegetable/" + id;
        ResponseEntity<Vegetable> response =
                restTemplate.getForEntity(url, Vegetable.class);
        Vegetable vegetable = response.getBody();
        return vegetable;
    }
    public void update(Vegetable vegetable) {
        String url = "http://localhost:8090/vegetable/" + vegetable.getId();
        restTemplate.put(url, vegetable, Vegetable.class);
    }

    public void updateCart(List<Cart> cart){
        for (int i =0; i <cart.size();i++){
            Vegetable update = this.getOneById(cart.get(i).getVegetable().getId());
            update.setAmount(update.getAmount()-cart.get(i).getQuantity());
            String url = "http://localhost:8090/vegetable/" + update.getId();
            if (update.getAmount() != 0){
                restTemplate.put(url, update, Vegetable.class);
            }
            else {
                restTemplate.delete(url, update, Vegetable.class);
            }
        }
    }


}
