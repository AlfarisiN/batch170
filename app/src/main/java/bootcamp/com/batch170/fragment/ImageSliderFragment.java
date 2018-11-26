package bootcamp.com.batch170.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import bootcamp.com.batch170.R;

/**
 * Created by Eric on 15/10/2018.
 */

public class ImageSliderFragment extends Fragment {
    private ImageView sliderImageItem;

    private String imageUrl;

    public ImageSliderFragment() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_slider_fragment,
                container, false);

        this.sliderImageItem = (ImageView) view.findViewById(R.id.sliderImageItem);

        //set isi image view nya
        Picasso.get().load(getImageUrl())
                .placeholder(R.mipmap.eye_circle)
                .error(R.mipmap.icon_clear)
                .into(this.sliderImageItem);

        return view;
    }
}
