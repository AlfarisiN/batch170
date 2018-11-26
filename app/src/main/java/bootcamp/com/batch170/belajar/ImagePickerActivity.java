package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import bootcamp.com.batch170.R;

public class ImagePickerActivity extends Activity {
    private Context context = this;

    private ImageView showImage;
    private Button buttonImagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        showImage = (ImageView) findViewById(R.id.showImage);

        buttonImagePicker = (Button) findViewById(R.id.buttonImagePicker);
        buttonImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilImageDenganImagePicker();
            }
        });
    }

    private void ambilImageDenganImagePicker(){
        ImagePicker.create(this)
                .single()
                .limit(1)
                .folderMode(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(ImagePicker.shouldHandle(requestCode, resultCode, data)){
            Image image = ImagePicker.getFirstImageOrNull(data);
            if(image != null){
                //logic tampilkan image
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(image.getPath(), options);

                Glide.with(context).load(bitmap).into(showImage);
//                showImage.setImageBitmap(bitmap);
            }
        }
    }
}
