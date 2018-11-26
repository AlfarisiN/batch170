package bootcamp.com.batch170.database2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.utility.DbQueryHelper;
import bootcamp.com.batch170.utility.DbSqlHelper;

public class InputBukuActivity extends Activity {
    private Context context = this;

    private EditText judulBuku, kategoriBuku, pengarangBuku,
            penerbitBuku, hargaBuku, stokBuku;

    private Button buttonSimpan;

    private DbSqlHelper dbSqlHelper;
    private DbQueryHelper dbQueryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);

        dbSqlHelper = new DbSqlHelper(context);
        dbQueryHelper = new DbQueryHelper(dbSqlHelper);

        judulBuku = (EditText) findViewById(R.id.judulBuku);
        kategoriBuku = (EditText) findViewById(R.id.kategoriBuku);
        pengarangBuku = (EditText) findViewById(R.id.pengarangBuku);
        penerbitBuku = (EditText) findViewById(R.id.penerbitBuku);
        hargaBuku = (EditText) findViewById(R.id.hargaBuku);
        stokBuku = (EditText) findViewById(R.id.stokBuku);

        buttonSimpan = (Button) findViewById(R.id.buttonSimpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiInput();
            }
        });
    }

    private void validasiInput(){
        if(judulBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Judul Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(kategoriBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Kategori Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(pengarangBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Pengarang Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(penerbitBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Penerbit Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(hargaBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Harga Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else if(stokBuku.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Stok Buku harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else{
            //logic insert buku ke database
            insertIntoDatabase();
        }
    }

    private void  insertIntoDatabase(){
        String judul = judulBuku.getText().toString().trim();
        String kategori = kategoriBuku.getText().toString().trim();
        String pengarang = pengarangBuku.getText().toString().trim();
        String penerbit = penerbitBuku.getText().toString().trim();
        int harga = Integer.parseInt(hargaBuku.getText().toString());
        int stok = Integer.parseInt(stokBuku.getText().toString());

        boolean status = dbQueryHelper.insertBuku(judul, kategori, pengarang,
                penerbit, harga, stok);

        if(status){
            Toast.makeText(context, "Buku berhasil disimpan!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(context, "Buku gagal disimpan!", Toast.LENGTH_SHORT).show();
        }
    }
}
