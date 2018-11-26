package bootcamp.com.batch170.database2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.ListBukuAdapter;
import bootcamp.com.batch170.models.Buku;
import bootcamp.com.batch170.utility.DbQueryHelper;
import bootcamp.com.batch170.utility.DbSqlHelper;

public class ListBukuActivity extends Activity {
    private Context context = this;

    private TextView titleList;
    private RecyclerView listBukuRecycler;
    private ListBukuAdapter bukuAdapter;

    private DbSqlHelper dbSqlHelper;
    private DbQueryHelper queryHelper;

    private EditText inputKeyword;
    private Button buttonCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buku);

        titleList = (TextView) findViewById(R.id.titleList);
        listBukuRecycler = (RecyclerView) findViewById(R.id.listBukuRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        listBukuRecycler.setLayoutManager(layoutManager);

        inputKeyword = (EditText) findViewById(R.id.inputKeyword);
        buttonCari = (Button) findViewById(R.id.buttonCari);
        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiKeyword();
            }
        });

        dbSqlHelper = new DbSqlHelper(context);
        queryHelper = new DbQueryHelper(dbSqlHelper);

        tampilkanListBuku();
    }

    private void tampilkanListBuku(){
        List<Buku> bukuList = queryHelper.getAllBukuListModel();

        bukuAdapter = new ListBukuAdapter(context, bukuList);
        listBukuRecycler.setAdapter(bukuAdapter);
        bukuAdapter.notifyDataSetChanged();

        titleList.setText("Jumlah Buku : "+bukuList.size());
    }

    private void validasiKeyword(){
        String keyword = inputKeyword.getText().toString().trim();
        if(keyword.length() == 0){
            Toast.makeText(context, "Keyword harus diisi!", Toast.LENGTH_SHORT).show();
        }
        else{
            //logic cari
            List<Buku> bukuList = queryHelper.cariBuku(keyword);

            bukuAdapter = new ListBukuAdapter(context, bukuList);
            listBukuRecycler.setAdapter(bukuAdapter);
            bukuAdapter.notifyDataSetChanged();

            titleList.setText("Jumlah Hasil Pencarian Buku : "+bukuList.size());
        }
    }
}
