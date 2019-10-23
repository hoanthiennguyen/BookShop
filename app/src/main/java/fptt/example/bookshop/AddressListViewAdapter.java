package fptt.example.bookshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fptt.example.bookshop.R;

import java.util.List;

public class AddressListViewAdapter extends BaseAdapter {
    List<String> addresses;

    public AddressListViewAdapter(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override

    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return addresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_address, parent, false);
        }

        TextView address = convertView.findViewById(R.id.addressView);
        address.setText(addresses.get(position));
        return convertView;
    }
}
