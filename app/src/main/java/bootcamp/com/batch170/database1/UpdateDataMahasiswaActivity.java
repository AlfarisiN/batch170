package bootcamp.com.batch170.database1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.DatabaseHelper;

public class UpdateDataMahasiswaActivity extends Activity {
    private Context context = this;

    private EditText NIM, namaLengkap, gender, tanggalLahir, alamat;
    private ImageView fotoDiri;
    private Button buttonSimpan;

    private String IMAGE_PATH = "";

    private DatabaseHelper databaseHelper;
    private Cursor cursor;

    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_mahasiswa);

        NIM = (EditText) findViewById(R.id.NIM);
        namaLengkap = (EditText) findViewById(R.id.namaLengkap);
        gender = (EditText) findViewById(R.id.gender);
        tanggalLahir = (EditText) findViewById(R.id.tanggalLahir);
        alamat = (EditText) findViewById(R.id.alamat);

        fotoDiri = (ImageView) findViewById(R.id.fotoDiri);
        fotoDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilFoto();
            }
        });

        buttonSimpan = (Button) findViewById(R.id.buttonSimpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiInput();
            }
        });

        databaseHelper = new DatabaseHelper(context);

        int id = getIntent().getIntExtra(Constanta.KEY_ID_TABEL_BIODATA, 0);
        ID = id;
        loadDataFromDb(id);
    }

    private void validasiInput(){
        if(NIM.getText().toString().trim().length() == 0){
            Toast.makeText(context, "NIM belum diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(namaLengkap.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Nama Lengkap belum diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(gender.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Jenis Kelamin belum diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(tanggalLahir.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Tanggal Lahir belum diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(alamat.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Alamat belum diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(IMAGE_PATH.equalsIgnoreCase("")){
            Toast.makeText(context, "Foto belum dipilih!", Toast.LENGTH_SHORT).show();
        }
        else{
            updateToDatabase();
        }
    }

    private void ambilFoto(){
        //dengan lib image picker
        ImagePicker.create(this)
                .single()
                .limit(1)
                .folderMode(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(ImagePicker.shouldHandle(requestCode, resultCode, data)){
            Image foto = ImagePicker.getFirstImageOrNull(data);
            if(foto != null){
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(foto.getPath(), options);

                IMAGE_PATH = foto.getPath();

                Glide.with(context).load(bitmap).into(fotoDiri);
            }
        }
    }

    private void loadDataFromDb(int id){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE id='"+id+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);

            String nama = cursor.getString(2).toString();
            if(nama != null){
                namaLengkap.setText(nama);
            }

            String nim = ""+cursor.getInt(1);
            NIM.setText(nim);

            String tanggal = cursor.getString(4).toString();
            if(tanggal != null){
                tanggalLahir.setText(tanggal);
            }

            String genderTxt = cursor.getString(3).toString();
            if(genderTxt != null){
                gender.setText(genderTxt);
            }

            String alamatTxt = cursor.getString(5).toString();
            if(alamatTxt != null){
                alamat.setText(alamatTxt);
            }

            String image_path = cursor.getString(6).toString();
            IMAGE_PATH = image_path;
            Glide.with(this).load(image_path).into(fotoDiri);
        }
        else{
            System.out.println("Empty result!");
        }
    }

    private void updateToDatabase(){
        //insert data ke tabel dalam database
        String queryInsert = "update biodata set " +
                "nim='" +
                NIM.getText().toString().trim() +
                "'" +
                ", " +
                "nama_lengkap='" +
                namaLengkap.getText().toString().trim() +
                "', " +
                "gender='" +
                gender.getText().toString().trim() +
                "', " +
                "tanggal_lahir='" +
                tanggalLahir.getText().toString().trim() +
                "', " +
                "alamat='" +
                alamat.getText().toString().trim() +
                "', " +
                "path_foto='" +
                IMAGE_PATH +
                "' " +
                "WHERE id='" +
                ID +
                "'";

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL(queryInsert);

        Toast.makeText(context, "Update data sukses!", Toast.LENGTH_LONG).show();
        finish();
    }
}
