package fptt.example.bookshop.home;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.kaopiz.kprogresshud.KProgressHUD;

import fptt.example.bookshop.KProgressHUDManager;
import fptt.example.bookshop.R;
import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.repositories.FBookRepositoryImp;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    AutoCompleteTextView txtSearch;
    List<String> listOfBookName = new ArrayList<>();
    ArrayAdapter<String> searchAdapter;

    FBookRepositoryImp repo;
    FCartRepositoryImp repoCart;
    TextView cartquantityhome;
    private int totalOfCartItem;
    KProgressHUD kProgressHUD;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        repo = new FBookRepositoryImp(getActivity());
        repoCart = new FCartRepositoryImp(getActivity());
        //mapping slideshow and set it's adapter
        ViewPager viewPager = view.findViewById(R.id.viewPagerHome);
        SlideFragmentAdapter adapter = new SlideFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        cartquantityhome = view.findViewById(R.id.cartquantityhome);
        //mapping search field and set it's adapter
        txtSearch = view.findViewById(R.id.txtSearch);
        searchAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, listOfBookName);
        txtSearch.setAdapter(searchAdapter);
        setDataSetForSearchField();
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchEnter(txtSearch.getText().toString());
                }
                return false;
            }
        });
        initView();
        return view;

    }

    private void searchEnter(String search) {
        repo.search(search, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.search_result, new CategoryFragment("Kết quả", books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    private void setDataSetForSearchField() {
        repo.search("", new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                for (Book book : books) {
                    listOfBookName.add(book.getProductName());
                }
                searchAdapter.notifyDataSetChanged();
                System.out.println("Data set size :" + listOfBookName.size());
            }

            @Override
            public void onFail(String message) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

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
        repo.getBooksByCategory("topSales", 0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                kProgressHUD.dismiss();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Bán chạy","topSale", books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {
                kProgressHUD.dismiss();
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
        repo.getBooksByCategory("discount",0,new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Giảm giá","discount", books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
        repo.getClickedBooks(new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Sản phẩm đã xem","clickedBooks", books));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });


    }


}
