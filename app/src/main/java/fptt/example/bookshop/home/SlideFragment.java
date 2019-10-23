package fptt.example.bookshop.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import fptt.example.bookshop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideFragment extends Fragment {

    ImageView imageView;
    public SlideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slide, container, false);
        imageView = view.findViewById(R.id.banner);
        int position = getArguments().getInt("image");
        imageView.setBackgroundResource(position);
        return view;
    }

}
