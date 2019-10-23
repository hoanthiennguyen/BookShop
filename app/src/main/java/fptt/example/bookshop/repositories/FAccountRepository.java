package fptt.example.bookshop.repositories;


import fptt.example.bookshop.model.Token;
import fptt.example.bookshop.model.User;
import fptt.example.bookshop.utils.CallBackData;

public interface FAccountRepository {
    void login(String username, String password, CallBackData<Token> data);

    void register(String username, String email, String password, CallBackData<Token> data);

    void getProfile(CallBackData<User> data);
    void updateProfile(User user, CallBackData<User> data);
}
