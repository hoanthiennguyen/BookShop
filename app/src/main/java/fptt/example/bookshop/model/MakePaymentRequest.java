package fptt.example.bookshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MakePaymentRequest implements Serializable {
    @SerializedName("deliveryAddress")
    String deliveryAddress;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public MakePaymentRequest(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
