package bootcamp.com.batch170.belajar;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import bootcamp.com.batch170.R;

public class CameraActivity extends Activity {
    private Context context = this;

    private ImageView fotoView;
    private Button buttonCamera, buttonGalery;

    private int SELECT_CAMERA = 1;
    private int SELECT_GALERY = 2;
    private int PERMISSION_REQUEST_FOR_CAMERA = 10;
    private int PERMISSION_REQUEST_FOR_GALERY = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        fotoView = (ImageView) findViewById(R.id.fotoView);
        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissionCamera()) {
                    ambilImageDariCamera();
                }
            }
        });

        buttonGalery = (Button) findViewById(R.id.buttonGalery);
        buttonGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissionGalery()) {
                    ambilImageDariGalery();
                }
            }
        });
    }

    private void ambilImageDariCamera(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, SELECT_CAMERA);
    }

    private void ambilImageDariGalery(){
        Intent galery = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galery.setType("image/*");
        galery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galery, "Pilih Foto")
                , SELECT_GALERY);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_CAMERA) {
                //ini callback result dari camera
                showImageFromCamera(data);
            } else if (requestCode == SELECT_GALERY) {
                //ini callback result dari galery
                showImageFromGalery(data);
            }
        }
    }

    private void showImageFromCamera(Intent data){
        //logic menampilkan fotonya
        Bitmap bitmap = null;
        if(data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            //        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            Glide.with(context).load(bitmap).into(fotoView);
            //        fotoView.setImageBitmap(bitmap);
        }
    }

    private void showImageFromGalery(Intent data){
        Bitmap bitmap = null;
        if(data != null){
            try{
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),
                        data.getData());

                Glide.with(context).load(bitmap).into(fotoView);
            }catch (IOException x){
                System.out.println("Failure Image Galery : "+x.getMessage());
            }
        }
    }

    //runtime permission CAMERA
    public boolean checkPermissionCamera(){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion >= Build.VERSION_CODES.M){
            //lakukan runtime permission
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //tampilkan dialog utk meminta permission
                requestPermissions(new String[]{ Manifest.permission.CAMERA },
                        PERMISSION_REQUEST_FOR_CAMERA);
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }

    //runtime permission galery
    public boolean checkPermissionGalery(){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion >= Build.VERSION_CODES.M){
            //lakukan runtime permission
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //tampilkan dialog utk meminta permission
                requestPermissions(new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE },
                        PERMISSION_REQUEST_FOR_GALERY);
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_REQUEST_FOR_CAMERA){
            //hasil request dari camera
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariCamera();
            }
            else{
                Toast.makeText(context, "Anda harus memberikan ijin akses untuk menggunakan camera", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == PERMISSION_REQUEST_FOR_GALERY){
            //hasil request dari galery
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariGalery();
            }
            else{
                Toast.makeText(context, "Anda harus memberikan ijin akses untuk mengambil gambar dari galery", Toast.LENGTH_LONG).show();
            }
        }
    }
}
