package bootcamp.com.batch170.database1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.DatabaseHelper;

public class DetailMahasiswaActivity extends Activity {
    private Context context = this;

    private TextView namaMahasiswa, nimMahasiswa, tanggalLahirMahasiswa,
            genderMahasiswa, alamatMahasiswa;
    private ImageView fotoMahasiswa;

    private DatabaseHelper databaseHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        namaMahasiswa = (TextView) findViewById(R.id.namaMahasiswa);
        nimMahasiswa = (TextView) findViewById(R.id.nimMahasiswa);
        tanggalLahirMahasiswa = (TextView) findViewById(R.id.tanggalLahirMahasiswa);
        genderMahasiswa = (TextView) findViewById(R.id.genderMahasiswa);
        alamatMahasiswa = (TextView) findViewById(R.id.alamatMahasiswa);

        fotoMahasiswa = (ImageView) findViewById(R.id.fotoMahasiswa);

        databaseHelper = new DatabaseHelper(context);

        int id = getIntent().getIntExtra(Constanta.KEY_ID_TABEL_BIODATA, 0);
        readDataMahasiswa(id);
    }

    private void readDataMahasiswa(int id){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE id='"+id+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);

            String nama = cursor.getString(2).toString();
            if(nama != null){
                namaMahasiswa.setText(nama);
            }

            String nim = ""+cursor.getInt(1);
            nimMahasiswa.setText(nim);

            String tanggal = cursor.getString(4).toString();
            if(tanggal != null){
                tanggalLahirMahasiswa.setText(tanggal);
            }

            String gender = cursor.getString(3).toString();
            if(gender != null){
                genderMahasiswa.setText(gender);
            }

            String alamat = cursor.getString(5).toString();
            if(alamat != null){
                alamatMahasiswa.setText(alamat);
            }

            String image_path = cursor.getString(6).toString();
            Glide.with(this).load(image_path).into(fotoMahasiswa);
        }
        else{
            System.out.println("Empty result!");
        }
    }
}
