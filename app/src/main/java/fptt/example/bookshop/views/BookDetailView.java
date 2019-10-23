package fptt.example.bookshop.views;

import fptt.example.bookshop.model.Book;

public interface BookDetailView {
    void getSuccess(Book book);
    void getFailed(String s);
}
