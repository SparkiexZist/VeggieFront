package th.ac.ku.kinkao.model;



public class Cart {
    private Vegetable vegetable;
    private int quantity;


    public Cart(Vegetable vegetable, int quantity) {
        this.vegetable = vegetable;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return  vegetable.toString() +
                "->" + quantity + "}";
    }

    public Vegetable getVegetable() {
        return vegetable;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }
}
