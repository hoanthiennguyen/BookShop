package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("productName")
    private String productName;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("inventory")
    private int inventory;
    @SerializedName("updateDate")
    private Date updateDate;
    @SerializedName("status")
    private String status;
    @SerializedName("description")
    private String description;
    @SerializedName("author")
    private String author;
    @SerializedName("publishedBy")
    private String publishedBy;
    @SerializedName("providedBy")
    private String providedBy;
    @SerializedName("rating")
    private int rating;
    @SerializedName("price")
    private int price;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("discount")
    private double discount;
    @SerializedName("category")
    private String category;
    @SerializedName("delete")
    private boolean delete;

    public Book() {
    }

    public Book(String productName, int price, String imgUrl) {
        this.productName = productName;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Book(String productName, int quantity, int inventory, Date updateDate, String status, String description, String author, String publishedBy, String providedBy, int rating, int price, String imgUrl, double discount, String category, boolean delete) {
        this.productName = productName;
        this.quantity = quantity;
        this.inventory = inventory;
        this.updateDate = updateDate;
        this.status = status;
        this.description = description;
        this.author = author;
        this.publishedBy = publishedBy;
        this.providedBy = providedBy;
        this.rating = rating;
        this.price = price;
        this.imgUrl = imgUrl;
        this.discount = discount;
        this.category = category;
        this.delete = delete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public String getProvidedBy() {
        return providedBy;
    }

    public void setProvidedBy(String providedBy) {
        this.providedBy = providedBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
