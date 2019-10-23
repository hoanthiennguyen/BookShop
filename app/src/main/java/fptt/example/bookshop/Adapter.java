package fptt.example.bookshop;

import android.content.Context;
import android.graphics.Paint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import fptt.example.bookshop.R;

import fptt.example.bookshop.home.BookRecyclerAdapter;
import fptt.example.bookshop.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Book> books;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Book> models, Context context) {
        this.books = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_adapter_item, container, false);
         ImageView bookImg;
         TextView bookTitle,bookPrice,bookDiscount,bookId,bookOriginalPrice;
        bookImg = view.findViewById(R.id.imageBook);
        bookTitle = view.findViewById(R.id.txtBookTitle);
        bookPrice = view.findViewById(R.id.txtBookPrice);
        bookId = view.findViewById(R.id.bookId);
        bookDiscount = view.findViewById(R.id.txtBookDiscount);
        bookOriginalPrice  = view.findViewById(R.id.txtBookOriginalPrice);

        Book book = books.get(position);
        bookPrice.setText(BookRecyclerAdapter.convertPriceToFormatString(book.getPrice()));
        bookTitle.setText(books.get(position).getProductName()+"");
        bookId.setText(books.get(position).getId()+"");
        bookTitle.setTag(book);
        Picasso.get().load(book.getImgUrl()).into(bookImg);
        if(book.getDiscount() >0){
            bookDiscount.setText("-" + Math.round(book.getDiscount()*100) + "%");
            bookDiscount.setVisibility(View.VISIBLE);
            bookOriginalPrice.setText(BookRecyclerAdapter.convertPriceToFormatString(book.getPrice()));
            bookOriginalPrice.setVisibility(View.VISIBLE);
            bookOriginalPrice.setPaintFlags(bookOriginalPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            long sellPrice =Math.round( book.getPrice()*(1-book.getDiscount()));
            bookPrice.setText(BookRecyclerAdapter.convertPriceToFormatString(sellPrice));
        }
//        ImageView imageView;
//        TextView title, desc;
//        imageView = view.findViewById(R.id.image);
//        title = view.findViewById(R.id.title);
//        desc = view.findViewById(R.id.desc);
//        Picasso.get().load(books.get(position).getImgUrl()).into(imageView);
//        title.setText(books.get(position).getProductName());
//        desc.setText(BookRecyclerAdapter.convertPriceToFormatString(books.get(position).getPrice()));

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
