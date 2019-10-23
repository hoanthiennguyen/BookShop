package fptt.example.bookshop.home;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import androidx.annotation.NonNull;

import java.util.List;

public class SearchAdapter extends ArrayAdapter implements Filterable {
    public SearchAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
}
