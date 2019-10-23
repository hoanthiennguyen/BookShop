package fptt.example.bookshop.utils;

public class ConfigApi {
    public static final String BASE_URL="https://prmapi.herokuapp.com/api/";
    public interface Api{
        String LOGIN ="authen/login";
        String REGISTER ="authen/signup";
        String GET_CLICKED_BOOKS = "user/clickedBooks";
        String POST_CLICKED_BOOKS = "user/clickedBooks";
        String SEARCH = "book";
        String GET_CATEGORY = "book/category/{category}";

        String GET_BOOK_ID = "book/{id}";
        String GET_ALL_BOOK_NAMES = "book/names";
        String PAYMENT = "cart/pay";
        String POST_ADD_TO_CART = "cart";
        String GET_ALL_IN_CART = "cart";
        String GET_PROFILE="user";
        String EDIT_CART = "cart/{id}";
        String DELETE_ITEM_IN_CART = "cart/{id}";
    }
}
