package bootcamp.com.batch170.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 16/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "database_mahasiswa.db";
    final static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE `biodata` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`nim` INTEGER," +
                " `nama_lengkap` TEXT, " +
                " `gender` TEXT, " +
                " `tanggal_lahir` TEXT, " +
                " `alamat` TEXT, " +
                " `path_foto` TEXT" +
                ");";

        String query2 = "CREATE TABLE `biodata` (`nim` INTEGER, `nama_lengkap` TEXT, `gender` TEXT, `tanggal_lahir`	TEXT, `alamat` TEXT, `path_foto` TEXT,PRIMARY KEY(`nim`));";

        db.execSQL(query);
        System.out.println("Tabel sudah di create");

        String queryInsertTabel = "INSERT INTO `biodata`(`nim`,`nama_lengkap`,`gender`,`tanggal_lahir`,`alamat`,`path_foto`) VALUES (" +
                "001" +
                "," +
                "'Mahasiswa Abadi'" +
                "," +
                "'L'" +
                "," +
                "'01/01/1901'" +
                "," +
                "'Indonesia'" +
                "," +
                "'-'" +
                ");";
        db.execSQL(queryInsertTabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
