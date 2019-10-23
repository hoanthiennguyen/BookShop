package fptt.example.bookshop.views;

import fptt.example.bookshop.model.CartBook;

import java.util.List;

public interface CartView {
    void getCartSuccess(List<CartBook> cart);
    void getCartFailed(String s);
}
