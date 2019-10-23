package fptt.example.bookshop.presenters;

import android.content.Context;

import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.repositories.FCartRepository;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.CartView;

import java.util.List;

public class CartPresenter {
    private CartView cartView;
    private FCartRepository repo;

    public CartPresenter(CartView cartView, Context context) {
        this.cartView = cartView;
        repo = new FCartRepositoryImp(context) {
        };
    }

    public void postAddToCart(long id, int quantity) {
        repo.addToCart(id, quantity, new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cart) {
                cartView.getCartSuccess(cart);
            }

            @Override
            public void onFail(String message) {
                cartView.getCartFailed(message);
            }
        });
    }


}
