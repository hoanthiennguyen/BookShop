package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bill implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("user")
    private User user;

    @SerializedName("dateCreated")
    private String dateCreated;

    @SerializedName("deliveryAddress")
    private String deliveryAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Bill(long id, User user, String dateCreated) {
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
    }

    public Bill() {
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
