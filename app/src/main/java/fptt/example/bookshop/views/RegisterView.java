package fptt.example.bookshop.views;

import fptt.example.bookshop.model.Token;

public interface RegisterView {
    void registerSuccess(Token token);

    void registerFailed(String s);
}
