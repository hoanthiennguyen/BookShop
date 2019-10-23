package fptt.example.bookshop.views;

import fptt.example.bookshop.model.Book;

import java.util.List;

public interface BookView {
    void getSuccess(List<Book> book);
    void getFailed(String s);
}
