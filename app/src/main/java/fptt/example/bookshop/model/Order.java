package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order  implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("quantity")
    private int quantity;

    public Order(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "{"+"id:"+id+","+"quantity:"+quantity+"}";
    }
}
