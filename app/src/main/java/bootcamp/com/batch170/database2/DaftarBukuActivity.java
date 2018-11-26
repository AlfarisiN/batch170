package bootcamp.com.batch170.database2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.Buku;
import bootcamp.com.batch170.utility.DbSqlHelper;

public class DaftarBukuActivity extends Activity {
    private Context context = this;

    private Button buttonImport, buttonTampil, buttonInsert;

    private DbSqlHelper dbSqlHelper;

    private int PERMISSION_REQUEST_WRITE_EXTERNAL = 88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_buku);

        dbSqlHelper = new DbSqlHelper(context);

        buttonImport = (Button) findViewById(R.id.buttonImport);
        buttonImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissionWrite()) {
                    importDatabaseFromFile();
                }
            }
        });

        buttonTampil = (Button) findViewById(R.id.buttonTampil);
        buttonTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBukuActivity.class);
                startActivity(intent);
            }
        });

        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InputBukuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void importDatabaseFromFile(){
        try {
            dbSqlHelper.createDatabaseFromImportedFile();
            Toast.makeText(context, "Success create database from imported SQL file",
                    Toast.LENGTH_SHORT).show();

            buttonTampil.setVisibility(View.VISIBLE);
            buttonInsert.setVisibility(View.VISIBLE);
            buttonImport.setVisibility(View.GONE);
        } catch (IOException e) {
            Toast.makeText(context, "importDatabaseFromFile : "+e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermissionWrite(){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion >= Build.VERSION_CODES.M){
            //lakukan runtime permission
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //tampilkan dialog utk meminta permission
                requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE },
                        PERMISSION_REQUEST_WRITE_EXTERNAL);
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

        if(requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL){
            //hasil request dari write external
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                importDatabaseFromFile();
            }
            else{
                Toast.makeText(context, "Anda harus memberikan ijin akses untuk bisa import database!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
