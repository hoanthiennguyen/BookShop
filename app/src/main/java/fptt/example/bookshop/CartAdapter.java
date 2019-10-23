package fptt.example.bookshop;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fptt.example.bookshop.R;

import fptt.example.bookshop.home.BookRecyclerAdapter;
import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.model.CartBook;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CartViewHolder extends RecyclerView.ViewHolder{

    public TextView cart_name, book_price, cart_quantity, cart_id;
    public ImageView cart_img;
    public TextView btnAdd, btnRemove, btnDelete;



    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cart_id = itemView.findViewById(R.id.cart_id);
        cart_name = itemView.findViewById(R.id.cart_item_name);
        book_price = itemView.findViewById(R.id.cart_item_price);
        cart_quantity = itemView.findViewById(R.id.cart_item_quantity);
        cart_img = itemView.findViewById(R.id.cart_img);
        btnAdd = itemView.findViewById(R.id.btAdd);
        btnRemove = itemView.findViewById(R.id.btSub);
        btnDelete = itemView.findViewById(R.id.btDelete);
    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<CartBook> listData = new ArrayList<>();
    private Context context;


    public CartAdapter(List<CartBook> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_item,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Book book = listData.get(position).getBook();
        holder.cart_id.setText(listData.get(position).getId()+"");
        holder.cart_name.setText(book.getProductName());
        holder.cart_quantity.setText(listData.get(position).getQuantity()+"");
        long sellPrice =Math.round( book.getPrice()*(1-book.getDiscount()));
        holder.book_price.setText(BookRecyclerAdapter.convertPriceToFormatString(sellPrice));

        Picasso.get().load(listData.get(position).getBook().getImgUrl()).into(holder.cart_img);
        holder.btnAdd.setTag(listData.get(position).getId());
        holder.btnRemove.setTag(listData.get(position).getId());
        holder.btnDelete.setTag(listData.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
