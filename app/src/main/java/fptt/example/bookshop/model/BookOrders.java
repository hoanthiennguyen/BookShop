package fptt.example.bookshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookOrders implements Serializable {

    private List<Order> orders;

    public BookOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BookOrders() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = (ArrayList<Order>)orders;
    }
    @Override
    public String toString() {
        return getOrders().toString();
    }
}
