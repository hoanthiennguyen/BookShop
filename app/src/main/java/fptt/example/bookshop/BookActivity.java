package fptt.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fptt.example.bookshop.R;

import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.presenters.BookDetailPresenter;
import fptt.example.bookshop.views.BookDetailView;
import com.squareup.picasso.Picasso;

public class BookActivity extends AppCompatActivity implements BookDetailView {
    private BookDetailPresenter bookDetailPresenter;
    private TextView productName3;
    private TextView des1;
    private TextView by;
    long bookId;
    private ImageView imageBook11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        imageBook11 = findViewById(R.id.imageBook11);
        productName3 = findViewById(R.id.productName3);
        by = findViewById(R.id.by);
        des1 = findViewById(R.id.des1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar02);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BookDetailActivity.class));
            }
        });
        Intent intent = this.getIntent();
        bookId = (long) intent.getLongExtra("bookId", 1);
        bookDetailPresenter = new BookDetailPresenter(this);
        bookDetailPresenter.getBookById(bookId);
    }

    @Override
    public void getSuccess(Book book) {
        Picasso.get().load(book.getImgUrl()).into(imageBook11);
        productName3.setText(book.getProductName());
        des1.setText(book.getDescription());
        by.setText("- Trích "+book.getProductName());
    }

    @Override
    public void getFailed(String s) {

    }
}
