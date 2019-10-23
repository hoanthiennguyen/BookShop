package fptt.example.bookshop.views;

import fptt.example.bookshop.model.CartBook;

import java.util.List;

public interface CartBookView {
    void getCartBookSuccess(List<CartBook> cartBook);

    void getCartBookFailed(String s);
}
