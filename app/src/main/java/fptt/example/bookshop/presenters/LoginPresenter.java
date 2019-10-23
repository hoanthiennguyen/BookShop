package fptt.example.bookshop.presenters;


import fptt.example.bookshop.model.Token;
import fptt.example.bookshop.repositories.FAccountRepository;
import fptt.example.bookshop.repositories.FAccountRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.LoginView;

public class LoginPresenter {
    private LoginView loginView;
    private FAccountRepository repo;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        repo = new FAccountRepositoryImp();
    }

    public void login(String username, String password) {
        repo.login(username, password, new CallBackData<Token>() {
            @Override
            public void onSuccess(Token token) {
                loginView.loginSuccess(token);
            }

            @Override
            public void onFail(String message) {
                loginView.loginFailed(message);
            }
        });
    }
}

