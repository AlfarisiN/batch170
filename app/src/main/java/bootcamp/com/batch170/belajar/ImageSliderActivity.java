package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.ImageSliderAdapter;
import bootcamp.com.batch170.fragment.ImageSliderFragment;
import me.relex.circleindicator.CircleIndicator;

public class ImageSliderActivity extends AppCompatActivity {
    private Context context = this;

    private ViewPager imageViewPager;
    private CircleIndicator indicator;
    private ImageSliderAdapter sliderAdapter;

    private String[] IMAGES_URL = {
            "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg",
            "https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg",
            "https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg",
            "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);

        imageViewPager = (ViewPager) findViewById(R.id.imageViewPager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);

        showImageSlider();
    }

    private void showImageSlider(){
        //isi data utk image slider
        List<ImageSliderFragment> imageSliderFragments = new ArrayList<>();
        for(int x=0; x<IMAGES_URL.length; x++){
            ImageSliderFragment fragment = new ImageSliderFragment();
            fragment.setImageUrl(IMAGES_URL[x]);

            imageSliderFragments.add(fragment);
        }

        /*deklarasikan adapter & isi
        //bla */
        sliderAdapter = new ImageSliderAdapter(getSupportFragmentManager(),
                imageSliderFragments);

        //hubungkan adapter dng view pager
        imageViewPager.setAdapter(sliderAdapter);

        //hubungkan view pager dng circle indicator
        indicator.setViewPager(imageViewPager);
    }
}
