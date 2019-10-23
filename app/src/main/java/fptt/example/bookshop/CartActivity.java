package fptt.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import fptt.example.bookshop.R;

import fptt.example.bookshop.home.BookRecyclerAdapter;
import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.presenters.BillPresenter;
import fptt.example.bookshop.presenters.CartBookPresenter;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.BillView;
import fptt.example.bookshop.views.CartBookView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartBookView, BillView {
    Button button;
    CartAdapter adapter;
    FCartRepositoryImp repoCart;
    List<CartBook> listProduct;
    RecyclerView myCartRecyclerView;
    TextView txtTotalPrice;
    int total = 0;
    Button btnPayment;
    KProgressHUD kProgressHUD;

    public CartActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        button = findViewById(R.id.btnPayment);
        txtTotalPrice = findViewById(R.id.txtTotal);
        repoCart = new FCartRepositoryImp(this);

        listProduct = new ArrayList<>();

        myCartRecyclerView = findViewById(R.id.listCart);
        adapter =new CartAdapter(listProduct,this);
        myCartRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        myCartRecyclerView.setAdapter(adapter);
        btnPayment = findViewById(R.id.btnPayment);





    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    public void onPayment(View view) {
        Intent intent = null;
        if(total > 0){
            intent = new Intent(this, ConfirmPaymentActivity.class);
            intent.putExtra("total",txtTotalPrice.getText().toString());
        }
        else
            intent = new Intent(this, HomeActivity.class);


        startActivity(intent);

    }



    @Override
    public void getSuccess(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void initView(){
        kProgressHUD = KProgressHUDManager.showProgessBar(this, "Đang tải dữ liệu, vui lòng chờ");


        repoCart.getAllInCart(new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                kProgressHUD.dismiss();
                listProduct.clear();
                listProduct.addAll(cartBooks);
                updateView();
            }

            @Override
            public void onFail(String message) {
                System.out.println("null");
            }
        });

    }

    @Override
    public void getCartBookSuccess(List<CartBook> cartBook) {

    }

    @Override
    public void getCartBookFailed(String s) {

    }

    public void onAddingItem(View view){
        ViewGroup row = (ViewGroup) view.getParent();
        TextView txtQuantity = row.findViewById(R.id.cart_item_quantity);
        TextView btnSub = row.findViewById(R.id.btSub);

        int quan = Integer.parseInt(txtQuantity.getText().toString());
        quan++;
        txtQuantity.setText(quan+"");
        btnSub.setVisibility(View.VISIBLE);
        total= 0;
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                cartBook.setQuantity(quan);
                break;
            }
        }
        updateView();

        repoCart.editCart((long)view.getTag(), quan, new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onRemoveItem(View view){
        ViewGroup row = (ViewGroup) view.getParent();
        TextView txtQuantity = row.findViewById(R.id.cart_item_quantity);
        int quan = Integer.parseInt(txtQuantity.getText().toString());
        if(quan > 0)
            quan--;
        if(quan == 0)
            view.setVisibility(View.INVISIBLE);
        txtQuantity.setText(quan+"");
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                cartBook.setQuantity(quan);
                break;
            }
        }
        updateView();

        repoCart.editCart((long)view.getTag(), quan, new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void onDeleteBook(View view){
        for(CartBook cartBook: listProduct){
            if(cartBook.getId() == (long) view.getTag()){
                listProduct.remove(cartBook);
                break;
            }
        }
        updateView();
        repoCart.delete((long) view.getTag(), new CallBackData<List<CartBook>>() {

            @Override
            public void onSuccess(List<CartBook> cartBooks) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
    private void updateView(){
        adapter.notifyDataSetChanged();
        float sum = 0;
        for(CartBook cartBook:listProduct){
            sum += cartBook.getBook().getPrice()*(1-cartBook.getBook().getDiscount())*cartBook.getQuantity();
        }
        total = Math.round(sum);
        txtTotalPrice.setText(BookRecyclerAdapter.convertPriceToFormatString(total));
        if(total > 0)
            btnPayment.setText("Tiến hành thanh toán");
        else
            btnPayment.setText("Tiếp tục mua sắm");

    }
}