package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("id")
    private long id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;

    @SerializedName("addressList")
    private List<String> listAddress;

    public List<String> getListAddress() {
        return listAddress;
    }

    public void setListAddress(List<String> listAddress) {
        this.listAddress = listAddress;
    }

    public User() {
    }

    public User(long id, String username, String email, String fullname, String phone, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public User( String email, String fullname, String phone, List<String> listAddress) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.listAddress = listAddress;
    }

    public User(String fullname, String email, String phone, String address) {
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
