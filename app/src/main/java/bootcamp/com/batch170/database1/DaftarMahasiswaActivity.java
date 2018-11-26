package bootcamp.com.batch170.database1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.ListMahasiswaAdapter;
import bootcamp.com.batch170.utility.DatabaseHelper;

public class DaftarMahasiswaActivity extends Activity {
    private Context context = this;

    private RecyclerView listMahasiswa;
    private ListMahasiswaAdapter adapterMahasiswa;
    private List<String> listNamaMahasiswa = new ArrayList<>();
    private List<Integer> idMahasiswa = new ArrayList<>();

    private Button buttonInput;

    private DatabaseHelper databaseHelper;
    private Cursor cursor;

    public static DaftarMahasiswaActivity daftarMahasiswaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiswa);

        daftarMahasiswaActivity = this;

        listMahasiswa = (RecyclerView) findViewById(R.id.listMahasiswa);
        buttonInput = (Button) findViewById(R.id.buttonInput);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InputDataMahasiswaActivity.class);
                startActivity(intent);
            }
        });

        listMahasiswa = (RecyclerView) findViewById(R.id.listMahasiswa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        listMahasiswa.setLayoutManager(layoutManager);

        databaseHelper = new DatabaseHelper(context);
        readDataMahasiswa();
    }

    private void readDataMahasiswa(){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

//        String queryList = "SELECT * FROM biodata";
//        cursor = db.rawQuery(queryList, null);

        //cara 2 dengan bantuan projection query
        String[] projection = {
           "id", "nim", "nama_lengkap", "gender", "tanggal_lahir", "alamat", "path_foto"
        };
        cursor = db.query("biodata", projection, null, null, null, null, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int c = 0; c < cursor.getCount(); c++) {
                cursor.moveToPosition(c);

                String nama = cursor.getString(2).toString();
                String alamat = cursor.getString(5).toString();

                listNamaMahasiswa.add(nama);

                int id = cursor.getInt(0);
                idMahasiswa.add(id);
            }
        }
        else{
            System.out.println("Query return empty result >>>");
        }

        tampilkanListMahasiswa();
    }

    private void tampilkanListMahasiswa(){
        adapterMahasiswa = new ListMahasiswaAdapter(context, listNamaMahasiswa, idMahasiswa);
        listMahasiswa.setAdapter(adapterMahasiswa);

        adapterMahasiswa.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    public void refreshList(){
        listNamaMahasiswa = new ArrayList<>();
        idMahasiswa = new ArrayList<>();

        if(listNamaMahasiswa.size() > 0) {
            adapterMahasiswa.notifyDataSetChanged();
        }

        readDataMahasiswa();
    }
}
