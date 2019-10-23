package fptt.example.bookshop.presenters;

import android.content.Context;

import fptt.example.bookshop.repositories.FCartRepository;
import fptt.example.bookshop.repositories.FCartRepositoryImp;
import fptt.example.bookshop.views.BillView;

public class BillPresenter {
    private BillView billView;
    private FCartRepository repo;
    Context context;
    String token;


    public BillPresenter(BillView billView, Context context) {
        this.billView = billView;
        this.context = context;
        repo = new FCartRepositoryImp(context) {
        };
    }


}
