package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("book_id")
    private int book_id;
    @SerializedName("user_id")
    private int user_id;

    public Cart() {
    }

    public Cart(long id, int quantity, int book_id, int user_id) {
        this.id = id;
        this.quantity = quantity;
        this.book_id = book_id;
        this.user_id = user_id;
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

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
