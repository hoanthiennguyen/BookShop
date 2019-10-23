package fptt.example.bookshop.presenters;


import fptt.example.bookshop.model.Token;
import fptt.example.bookshop.repositories.FAccountRepository;
import fptt.example.bookshop.repositories.FAccountRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.RegisterView;

public class RegisterPresenter {
    private RegisterView registerView;
    private FAccountRepository repo;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        repo = new FAccountRepositoryImp();
    }

    public void register(String username,String email, String password) {
        repo.register(username,email, password, new CallBackData<Token>() {
            @Override
            public void onSuccess(Token token) {
                registerView.registerSuccess(token);
            }

            @Override
            public void onFail(String message) {
                registerView.registerFailed(message);
            }
        });
    }
}
