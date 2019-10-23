package fptt.example.bookshop.repositories;

import fptt.example.bookshop.model.Bill;
import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.model.MakePaymentRequest;
import fptt.example.bookshop.utils.CallBackData;

import java.util.List;

public interface FCartRepository {

    void addToCart( long id, int quantity, CallBackData<List<CartBook>> data);
    void getAllInCart( CallBackData<List<CartBook>> data);
    void payment(MakePaymentRequest request, CallBackData<Bill> data);
    void editCart(long id, int quantity, CallBackData<List<CartBook>> data);
    void delete(long id, CallBackData<List<CartBook>> data);

}
