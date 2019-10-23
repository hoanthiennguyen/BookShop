package fptt.example.bookshop.views;

import fptt.example.bookshop.model.Token;

public interface LoginView {
    void loginSuccess(Token token);
    void loginFailed(String s);

}
