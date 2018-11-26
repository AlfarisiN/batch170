package bootcamp.com.batch170.utility;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.models.Buku;

/**
 * Created by Eric on 17/10/2018.
 */

public class DbQueryHelper {
    private DbSqlHelper sqlHelper;

    private String TABEL_BUKU = "stok_buku";
    private String FIELD_JUDUL = "judul_buku";
    private String FIELD_KATEGORI = "kategori_buku";
    private String FIELD_PENGARANG = "pengarang_buku";
    private String FIELD_PENERBIT = "penerbit_buku";
    private String FIELD_HARGA = "harga";
    private String FIELD_STOK = "stok";

    public DbQueryHelper(DbSqlHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

//    private void openDatabase(){
//        sqlHelper.openDatabase();
//    }
//
//    private void closeDatabase(){
//        sqlHelper.close();
//    }

    //login query definisikan disini

    //method ambil semua buku
    public Cursor getAllBuku(){
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABEL_BUKU, null);

        return cursor;
    }

    public List<Buku> getAllBukuListModel(){
        List<Buku> bukuList = new ArrayList<>();

        Cursor cursor = getAllBuku();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int c=0; c<cursor.getCount(); c++){
                cursor.moveToPosition(c);

                Buku buku = new Buku();
                buku.setId(cursor.getInt(0));
                buku.setJudul_buku(cursor.getString(1).toString());
                buku.setKategori_buku(cursor.getString(2).toString());
                buku.setPengarang_buku(cursor.getString(3).toString());
                buku.setPenerbit_buku(cursor.getString(4).toString());
                buku.setHarga(cursor.getInt(5));
                buku.setStok(cursor.getInt(6));

                bukuList.add(buku);
            }
        }

        return bukuList;
    }

    //method insert buku
    public boolean insertBuku(String judul,
                           String kategori,
                           String pengarang,
                           String penerbit,
                           int harga,
                           int stok){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_JUDUL, judul);
        contentValues.put(FIELD_KATEGORI, kategori);
        contentValues.put(FIELD_PENGARANG, pengarang);
        contentValues.put(FIELD_PENERBIT, penerbit);
        contentValues.put(FIELD_HARGA, harga);
        contentValues.put(FIELD_STOK, stok);

        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        long status = db.insert(TABEL_BUKU, null, contentValues);

        if(status == -1){
            //gagal
            return false;
        }
        else {
            System.out.println("Sukses input data buku!");
            return true;
        }
    }

    public List<Buku> cariBuku(String keyword){
        List<Buku> bukuList = new ArrayList<>();

        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABEL_BUKU+
                        " WHERE "+FIELD_JUDUL+" LIKE '%"+keyword+"%'"+
                        " OR "+FIELD_KATEGORI+" LIKE '%"+keyword+"%'"+
                        " OR "+FIELD_PENERBIT+" LIKE '%"+keyword+"%'"+
                        " OR "+FIELD_PENGARANG+" LIKE '%"+keyword+"%'"
                , null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int c=0; c<cursor.getCount(); c++){
                cursor.moveToPosition(c);

                Buku buku = new Buku();
                buku.setId(cursor.getInt(0));
                buku.setJudul_buku(cursor.getString(1).toString());
                buku.setKategori_buku(cursor.getString(2).toString());
                buku.setPengarang_buku(cursor.getString(3).toString());
                buku.setPenerbit_buku(cursor.getString(4).toString());
                buku.setHarga(cursor.getInt(5));
                buku.setStok(cursor.getInt(6));

                bukuList.add(buku);
            }
        }

        return bukuList;
    }
}
