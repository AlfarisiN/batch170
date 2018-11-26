package bootcamp.com.batch170.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.Instant;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.database1.DaftarMahasiswaActivity;
import bootcamp.com.batch170.database1.DetailMahasiswaActivity;
import bootcamp.com.batch170.database1.UpdateDataMahasiswaActivity;
import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.DatabaseHelper;
import bootcamp.com.batch170.viewholder.ViewHolderListMahasiswa;

/**
 * Created by Eric on 16/10/2018.
 */

public class ListMahasiswaAdapter extends RecyclerView.Adapter<ViewHolderListMahasiswa> {
    private Context context;
    private List<String> namaMahasiswa;
    private List<Integer> idMahasiswa;

    private DatabaseHelper databaseHelper;

    public ListMahasiswaAdapter(Context context
            , List<String> namaMahasiswa
            , List<Integer> idMahasiswa) {
        this.context = context;
        this.namaMahasiswa = namaMahasiswa;
        this.idMahasiswa = idMahasiswa;

        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public ViewHolderListMahasiswa onCreateViewHolder(ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_list_mahasiswa,
                parent,
                false
        );

        return new ViewHolderListMahasiswa(customView);
    }

    @Override
    public void onBindViewHolder(ViewHolderListMahasiswa holder, final int position) {
        if(namaMahasiswa.size() > 0) {
            String nama = namaMahasiswa.get(position);

            holder.setNamaMahasiswa(context, nama, position);

            holder.listMahasiswa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //aksi jika list di klik
                    final CharSequence[] listPilihan = {
                            "Detail Mahasiswa",
                            "Update Data Mahasiswa",
                            "Delete Data Mahasiswa"
                    };

                    System.out.println("ID selected : " + idMahasiswa.get(position));

                    AlertDialog.Builder optionChooser = new AlertDialog.Builder(context);
                    optionChooser.setTitle("Pilih aksi");
                    optionChooser.setItems(listPilihan, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    //detail
                                    Intent intent = new Intent(context, DetailMahasiswaActivity.class);
                                    intent.putExtra(Constanta.KEY_ID_TABEL_BIODATA, idMahasiswa.get(position));
                                    context.startActivity(intent);

                                    break;
                                case 1:
                                    //edit
                                    Intent intentEdit = new Intent(context, UpdateDataMahasiswaActivity.class);
                                    intentEdit.putExtra(Constanta.KEY_ID_TABEL_BIODATA, idMahasiswa.get(position));
                                    context.startActivity(intentEdit);

                                    break;
                                case 2:
                                    //delete
                                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                                    db.execSQL("DELETE FROM biodata WHERE id='" + idMahasiswa.get(position) + "'");

                                    //refresh list daftar mahasiswa
                                    DaftarMahasiswaActivity.daftarMahasiswaActivity.refreshList();
                                    break;
                            }
                        }
                    });
                    optionChooser.create().show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(namaMahasiswa == null) {
            return 0;
        }
        else{
            return namaMahasiswa.size();
        }
    }
}
