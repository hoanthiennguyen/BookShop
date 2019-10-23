package fptt.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import fptt.example.bookshop.R;

import fptt.example.bookshop.model.Token;
import fptt.example.bookshop.presenters.RegisterPresenter;
import fptt.example.bookshop.views.RegisterView;
import com.kaopiz.kprogresshud.KProgressHUD;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText userEmail;
    private EditText userPass;
    private EditText userPassConfirm;
    private RegisterPresenter mRegisterPresenter;
    private EditText userName;
    private RadioButton check;
    KProgressHUD kProgressHUD;
    private String accessToken;
    private String tokenType;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userEmail = (EditText) findViewById(R.id.userEmail2);
        userPass = (EditText) findViewById(R.id.userPassword2);
        userPassConfirm = (EditText) findViewById(R.id.userConfirm2);
        userName = (EditText) findViewById(R.id.userName2);
        check = (RadioButton) findViewById(R.id.radioButton);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    public void onRegisterDone(View view) {
        String email = userEmail.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        String passConfirm = userPassConfirm.getText().toString().trim();
        String username = userName.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email) ) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Tài khoản không được để trống", Toast.LENGTH_LONG).show();
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
        } else if (!passConfirm.equals(pass)) {
            Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else if (!check.isChecked()) {
            Toast.makeText(this, "Bạn phải chấp nhận điều khoản của chúng tôi", Toast.LENGTH_LONG).show();
        } else {
            mRegisterPresenter.register(username, email, pass);
            kProgressHUD = KProgressHUDManager.showProgessBar(this, "Đang xử lý");

        }
    }

    public void onExit(View view) {
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void registerSuccess(Token token) {
        accessToken = token.getAccessToken();
        tokenType = token.getTokenType();
        Bundle bundle = new Bundle();
        bundle.putString("accessToken", accessToken);
        bundle.putString("tokenType", tokenType);
        preferences.setAccessToken(this, tokenType + " " + accessToken);
        kProgressHUD.dismiss();
        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));

    }

    @Override
    public void registerFailed(String s) {
        kProgressHUD.dismiss();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
