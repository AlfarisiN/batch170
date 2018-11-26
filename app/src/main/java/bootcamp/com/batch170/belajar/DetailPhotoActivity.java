package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.ModelPhotos;
import bootcamp.com.batch170.utility.TemporaryData;

public class DetailPhotoActivity extends Activity {
    private Context context = this;

    private ModelPhotos modelPhotos;

    private ImageView imagePhoto;
    private TextView judulPhoto, idPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photo);

        modelPhotos = TemporaryData.getModelPhotos();

        imagePhoto = (ImageView) findViewById(R.id.imagePhoto);
        judulPhoto = (TextView) findViewById(R.id.judulPhoto);
        idPhoto = (TextView) findViewById(R.id.idPhoto);

        //set isinya
        judulPhoto.setText(modelPhotos.getTitle());

        String idP = "ID : "+modelPhotos.getId()
                + ", Album ID: " + modelPhotos.getAlbumId();
        idPhoto.setText(idP);

        String urlImage = modelPhotos.getUrl();
        Picasso.get().load(urlImage).into(imagePhoto);
    }
}
