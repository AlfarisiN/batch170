package bootcamp.com.batch170.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bootcamp.com.batch170.R;

/**
 * Created by Eric on 18/10/2018.
 */

public class FragmentSatu extends Fragment {

    public FragmentSatu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_layout, container, false);

        return view;
    }
}
