package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartBook implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("book")
    private Book book;
    @SerializedName("user")
    private User user;
    @SerializedName("quantity")
    private int quantity;

    public CartBook(long id, Book book, User user, int quantity) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public CartBook(Book book, User user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public CartBook(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
