package bootcamp.com.batch170.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.fragment.FragmentTiga;
import bootcamp.com.batch170.fragment.FragmentDua;
import bootcamp.com.batch170.fragment.FragmentSatu;

/**
 * Created by Eric on 18/10/2018.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private Context context;
    private FragmentManager fm;

    public TabLayoutAdapter(Context context, FragmentManager fm) {
        super(fm);

        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FragmentSatu();
        }
        else if(position == 1){
            return new FragmentDua();
        }
        else {
            return new FragmentTiga();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getString(R.string.title_tab_1);
            case 1:
                return context.getResources().getString(R.string.title_tab_2);
            case 2:
                return context.getResources().getString(R.string.title_tab_3);
            default:
                return "-";
        }
    }
}
