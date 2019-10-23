package fptt.example.bookshop.model;

public class BookViews {

    String title;
    String imageID;
    long price;

    public BookViews(String title, String imageID, long price) {
        this.title = title;
        this.imageID = imageID;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
