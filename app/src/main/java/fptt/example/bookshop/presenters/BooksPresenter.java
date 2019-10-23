package fptt.example.bookshop.presenters;

import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.repositories.FBookRepositoryImp;
import fptt.example.bookshop.views.BookView;
import fptt.example.bookshop.repositories.FBookRepository;
import fptt.example.bookshop.utils.CallBackData;

import java.util.List;

public class BooksPresenter {
    private BookView bookView;
    private FBookRepository repo;

    public BooksPresenter(BookView bookView) {
        this.bookView = bookView;
        repo = new FBookRepositoryImp() {
        };
    }

    public void getTopDiscount() {
        repo.getBooksByCategory("discount",0, new CallBackData<List<Book>>() {
            @Override
            public void onSuccess(List<Book> book) {
                bookView.getSuccess(book);
            }

            @Override
            public void onFail(String message) {
                bookView.getFailed(message);
            }
        });
    }


}
