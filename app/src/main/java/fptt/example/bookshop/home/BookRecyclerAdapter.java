package fptt.example.bookshop.home;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fptt.example.bookshop.R;
import fptt.example.bookshop.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Book> books;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookImg;
        private TextView bookTitle,bookPrice,bookDiscount,bookId,bookOriginalPrice;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImg = itemView.findViewById(R.id.imageBook);
            bookTitle = itemView.findViewById(R.id.txtBookTitle);
            bookPrice = itemView.findViewById(R.id.txtBookPrice);
            bookId = itemView.findViewById(R.id.bookId);
            bookDiscount = itemView.findViewById(R.id.txtBookDiscount);
            bookOriginalPrice  = itemView.findViewById(R.id.txtBookOriginalPrice);
        }


    }
    public static class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public BookRecyclerAdapter(List<Book> books) {
        this.books = books;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {

            setContentForBookItem((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    public void setContentForBookItem(@NonNull ItemViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookPrice.setText(convertPriceToFormatString(book.getPrice()));
        holder.bookTitle.setText(books.get(position).getProductName()+"");
        holder.bookId.setText(books.get(position).getId()+"");
        holder.bookTitle.setTag(book);
        if(book.getDiscount() >0){
            holder.bookDiscount.setText("-" + Math.round(book.getDiscount()*100) + "%");
            holder.bookDiscount.setVisibility(View.VISIBLE);
            holder.bookOriginalPrice.setText(convertPriceToFormatString(book.getPrice()));
            holder.bookOriginalPrice.setVisibility(View.VISIBLE);
            holder.bookOriginalPrice.setPaintFlags(holder.bookOriginalPrice.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
            long sellPrice =Math.round( book.getPrice()*(1-book.getDiscount()));
            holder.bookPrice.setText(convertPriceToFormatString(sellPrice));
        }

        Picasso.get().load(book.getImgUrl()).into(holder.bookImg);

    }
    public static String convertPriceToFormatString(long price){
        if(price > 999){
            String raw = price +"";
            return raw.substring(0,raw.length()-3)+ "." + raw.substring(raw.length()-3) + " đ";
        }
        else return price + "đ";

    }

    @Override
    public int getItemViewType(int position) {
        return position>= books.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return books.size() + 1;
    }
}
