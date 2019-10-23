package fptt.example.bookshop.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fptt.example.bookshop.R;

public class SlideFragmentAdapter extends FragmentPagerAdapter {
    int[] images = {
            R.drawable.banner1,R.drawable.banner2,R.drawable.banner3
    };

    public SlideFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        SlideFragment demoFragment = new SlideFragment();
        Bundle bundle = new Bundle();
        position++;
        if(position >= images.length)
            position = position -images.length;
        bundle.putInt("image",images[position]);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
