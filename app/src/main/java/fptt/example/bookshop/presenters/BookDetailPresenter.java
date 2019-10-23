package fptt.example.bookshop.presenters;

import fptt.example.bookshop.model.Book;
import fptt.example.bookshop.repositories.FBookRepository;
import fptt.example.bookshop.repositories.FBookRepositoryImp;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.views.BookDetailView;

public class BookDetailPresenter {
    private BookDetailView bookView;
    private FBookRepository repo;


    public BookDetailPresenter(BookDetailView bookView) {
        this.bookView = bookView;
        repo = new FBookRepositoryImp() {
        };
    }

    public void getBookById( long id) {
        repo.getBookById( id, new CallBackData<Book>() {

            @Override
            public void onSuccess(Book book) {
                bookView.getSuccess(book);
            }

            @Override
            public void onFail(String message) {
                bookView.getFailed(message);
            }
        });
    }
}
