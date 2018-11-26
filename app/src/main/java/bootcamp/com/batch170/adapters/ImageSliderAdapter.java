package bootcamp.com.batch170.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.fragment.ImageSliderFragment;

/**
 * Created by Eric on 15/10/2018.
 */

public class ImageSliderAdapter extends FragmentPagerAdapter {
    private List<ImageSliderFragment> imageSliderFragments = new ArrayList<>();

    public ImageSliderAdapter(FragmentManager fm,
                              List<ImageSliderFragment> imageSliderFragments) {
        super(fm);

        this.imageSliderFragments = imageSliderFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return imageSliderFragments.get(position);
    }

    @Override
    public int getCount() {
        return imageSliderFragments.size();
    }
}
