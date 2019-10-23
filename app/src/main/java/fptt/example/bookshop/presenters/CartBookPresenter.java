package fptt.example.bookshop.presenters;

import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.repositories.FCartRepository;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.CartBookView;

import java.util.List;

public class CartBookPresenter {
    private CartBookView cartBookView;
    private FCartRepository repo;

    public CartBookPresenter(CartBookView cartBookView) {
        this.cartBookView = cartBookView;
        repo = new FCartRepositoryImp() {
        };
    }

    public void getAllInCart() {
        repo.getAllInCart( new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                cartBookView.getCartBookSuccess(cartBooks);
            }

            @Override
            public void onFail(String message) {
                cartBookView.getCartBookFailed(message);
            }
        });
    }
}
