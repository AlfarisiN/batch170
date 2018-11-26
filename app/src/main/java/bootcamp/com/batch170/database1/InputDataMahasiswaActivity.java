package bootcamp.com.batch170.database1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.customview.DialogAutoComplete;
import bootcamp.com.batch170.utility.DatabaseHelper;

public class InputDataMahasiswaActivity extends Activity {
    private Context context = this;

    private EditText NIM, namaLengkap, gender, tanggalLahir, alamat, inputJurusan;
    private ImageView fotoDiri;
    private Button buttonSimpan;

    private String IMAGE_PATH = "";

    private DatabaseHelper databaseHelper;

    private List<String> stringList = new ArrayList<>();
    private DialogAutoComplete dialogChooseJurusan;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_mahasiswa);

        NIM = (EditText) findViewById(R.id.NIM);
        namaLengkap = (EditText) findViewById(R.id.namaLengkap);
        gender = (EditText) findViewById(R.id.gender);

        Calendar today = Calendar.getInstance();
        final int yearStart = today.get(Calendar.YEAR);
        final int monthStart = today.get(Calendar.MONTH);
        final int dateStart = today.get(Calendar.DATE);

        tanggalLahir = (EditText) findViewById(R.id.tanggalLahir);
        tanggalLahir.setFocusable(false);
        tanggalLahir.setClickable(true);
        tanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        R.style.DatePickerBirthdate,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year, month, dayOfMonth);

                        //utk formatting date & konversi ke string
                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                        String birthDate = formatDate.format(selected.getTime());

                        tanggalLahir.setText(birthDate);
                    }
                }, yearStart, monthStart, dateStart
                );
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.show();
            }
        });

        //isi data
        isiListJurusan();

        inputJurusan = (EditText) findViewById(R.id.inputJurusan);
        inputJurusan.setFocusable(false);
        inputJurusan.setClickable(true);
        inputJurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stringList != null){
                    dialogChooseJurusan = new DialogAutoComplete(context, stringList);
                    dialogChooseJurusan.setDialogResult(new DialogAutoComplete.OnAutoCompleteResult() {
                        @Override
                        public void finish(String result) {
                            if(result != null){
                                inputJurusan.setText(result);
                            }
                        }
                    });
                }
            }
        });

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
    }

    private void isiListJurusan(){
        stringList.add("IPA");
        stringList.add("IPS");
        stringList.add("Sosial");
        stringList.add("Hukum");
        stringList.add("Sistem Informasi");
        stringList.add("Manajemen Informatika");
        stringList.add("Psikologi");
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
            insertToDatabase();
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

    private void insertToDatabase(){
        //insert data ke tabel dalam database
        String queryInsert = "INSERT INTO `biodata`(`nim`,`nama_lengkap`,`gender`,`tanggal_lahir`,`alamat`,`path_foto`) VALUES (" +
                NIM.getText().toString().trim() +
                ",'" +
                namaLengkap.getText().toString().trim() +
                "','" +
                gender.getText().toString().trim() +
                "','" +
                tanggalLahir.getText().toString().trim() +
                "','" +
                alamat.getText().toString().trim() +
                "','" +
                IMAGE_PATH +
                "');";

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        db.execSQL(queryInsert);

        //ini cara menggunakan bantuan ContentValues
        ContentValues content = new ContentValues();
        content.put("nim", NIM.getText().toString().trim());
        content.put("nama_lengkap", namaLengkap.getText().toString().trim());
        content.put("gender", gender.getText().toString().trim());
        content.put("tanggal_lahir", tanggalLahir.getText().toString().trim());
        content.put("alamat", alamat.getText().toString().trim());
        content.put("path_foto", IMAGE_PATH);

        db.insert("biodata", null, content);

        Toast.makeText(context, "Input data sukses!", Toast.LENGTH_LONG).show();
        finish();
    }
}
