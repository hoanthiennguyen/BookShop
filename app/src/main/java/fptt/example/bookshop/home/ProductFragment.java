package fptt.example.bookshop.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import fptt.example.bookshop.KProgressHUDManager;
import fptt.example.bookshop.R;
import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.repositories.FBookRepositoryImp;
import fptt.example.bookshop.repositories.FCartRepository;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    AutoCompleteTextView txtSearch;
    List<String> listOfBookName = new ArrayList<>();
    ArrayAdapter<String> searchAdapter;
    FBookRepositoryImp repo ;
    FCartRepository repoCart;
    TextView cartquantityhome;
    private int totalOfCartItem;
    KProgressHUD kProgressHUD;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        repo = new FBookRepositoryImp(getContext());
        repoCart = new FCartRepositoryImp(getActivity());
        cartquantityhome = view.findViewById(R.id.cartquantityhome);
        txtSearch = view.findViewById(R.id.txtSearch);
        searchAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, listOfBookName);
        txtSearch.setAdapter(searchAdapter);
        setDataSetForSearchField();
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    searchEnter(txtSearch.getText().toString());
                }
                return false;
            }
        });
        initView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void setDataSetForSearchField() {
        repo.getAllBookNames( new CallBackData<List<String>>() {
            @Override
            public void onSuccess(List<String> names) {
                listOfBookName.clear();
                listOfBookName.addAll(names);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String message) {

            }
        });

    }

    private void initView() {
        kProgressHUD = KProgressHUDManager.showProgessBar(getActivity(), "Đang tải dữ liệu, vui lòng đợi");
        repoCart.getAllInCart(new CallBackData<List<CartBook>>() {
            @Override
            public void onSuccess(List<CartBook> cartBooks) {
                totalOfCartItem = 0;
                for (CartBook cartBook : cartBooks) {
                    totalOfCartItem += cartBook.getQuantity();
                }
                if(totalOfCartItem > 0){
                    cartquantityhome.setText(totalOfCartItem+"");
                    cartquantityhome.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFail(String message) {

            }
        });
        repo.getBooksByCategory("Truyen",0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Truyện","Truyen",books));
                transaction.commit();
                kProgressHUD.dismiss();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getBooksByCategory("Tieu thuyet",0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Tiểu thuyết","Tieu thuyet",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getBooksByCategory("Van hoc",0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Văn học","Van hoc",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });

        repo.getBooksByCategory("Xa hoi",0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Xã hội","Xa hoi",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
    private void searchEnter(String search){
        repo.search( search, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.search_result, new CategoryFragment("Kết quả",books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

}
