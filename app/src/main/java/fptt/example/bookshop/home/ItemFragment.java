package fptt.example.bookshop.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fptt.example.bookshop.R;
import fptt.example.bookshop.model.Book;

import com.squareup.picasso.Picasso;


public class ItemFragment extends Fragment {
    Book book;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        TextView textView = view.findViewById(R.id.txtBookTitle);
        TextView txtBookPrice = view.findViewById(R.id.txtBookPrice);
        ImageView imageView = view.findViewById(R.id.imageBook);
        Picasso.get().load(book.getImgUrl()).into(imageView);
        txtBookPrice.setText(book.getPrice() + " đ");
        textView.setText(book.getProductName());
        return view;
    }

    public ItemFragment(Book book) {
        this.book = book;
    }
}
