package fptt.example.bookshop.utils;

import fptt.example.bookshop.repositories.FAccountService;
import fptt.example.bookshop.repositories.FBookService;
import fptt.example.bookshop.repositories.FCartService;

public class ClientApi extends BaseApi {
    public FAccountService fAccountService(){
        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
    }
    public FBookService fBookService(){
        return this.getService(FBookService.class, ConfigApi.BASE_URL);
    }
    public FCartService fCartService(){
        return this.getService(FCartService.class, ConfigApi.BASE_URL);
    }
}
