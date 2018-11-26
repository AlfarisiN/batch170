package bootcamp.com.batch170;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import bootcamp.com.batch170.adapters.CustomListAdapter;
import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.SessionManager;

public class MainMenuActivity extends Activity {
    private Context context = this;

    private TextView txtUsername, txtPassword;
    private ListView listMenu;

    private int counter_exit = 1;

    //data dummy list
    private int[] LIST_ICON = {
            R.mipmap.eye_circle,
            R.mipmap.icon_company_office,
            R.mipmap.icon_education,
            R.mipmap.icon_facebook,
            R.mipmap.icon_cards,
            R.mipmap.icon_cards,
            R.mipmap.icon_cards,
            R.mipmap.icon_education,
            R.mipmap.icon_education,
            R.mipmap.icon_education,
            R.mipmap.icon_facebook,
            R.mipmap.eye_circle,
            R.mipmap.icon_facebook,
    };
    private String[] LIST_TITLE = {
            "Panggil WS API 1",
            "Panggil WS API 2",
            "Panggil WS API 3",
            "Panggil WS API 4",
            "Panggil WS API 5",
            "Camera",
            "Library Image Picker",
            "Image Slider",
            "SQLite CRUD 1",
            "SQLite CRUD 2",
            "Tabbed Menu",
            "Navigation Drawer Menu",
            "Sample API Marcomm"
    };
    private String[] LIST_DESCRIPTION = {
            "Belajar memanggil API dengan Volley",
            "Belajar memanggil API dengan Volley & Picasso",
            "Belajar memanggil API dengan Volley JSON multilevel",
            "Belajar memanggil API dengan Retrofit",
            "Belajar memanggil API dengan Retrofit GET, POST, HEADER etc",
            "Akses Camera + Runtime Permission",
            "Belajar menggunakan library Image Picker Esafirm",
            "Belajar membuat image slider",
            "Belajar CRUD database SQLite",
            "Belajar CRUD database SQLite Advance",
            "Belajar membuat tabbed menu ala WhatsApp",
            "Belajar membuat navigation drawer menu ala Gmail",
            "Sample API Marcomm"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //menangkap extras yg dikirim intent sebelumnya
//        Intent intent = getIntent();
//        String username = intent.getExtras().getString(Constanta.KEY_USERNAME);
//        String password = intent.getExtras().getString(Constanta.KEY_PASSWORD);

        txtUsername = (TextView) findViewById(R.id.username);
        txtPassword = (TextView) findViewById(R.id.password);

//        txtUsername.setText(username);
//        txtPassword.setText(", "+password);

        //cara shared preferences
        String fullname = SessionManager.getFullName(context);
        txtUsername.setText(fullname);

        String age = Integer.toString(SessionManager.getAge(context));
        txtPassword.setText(age);

        //deklarasi listview
        listMenu = (ListView) findViewById(R.id.listMenu);
        //adapter utk list
        CustomListAdapter adapterList = new CustomListAdapter(context,
                LIST_ICON,
                LIST_TITLE,
                LIST_DESCRIPTION);
        listMenu.setAdapter(adapterList);
    }

    @Override
    protected void onResume() {
        counter_exit = 1;
        super.onResume();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        //cara 1 -> tanya user
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setMessage("Are you sure want to exit?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //jika yes
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //jika no
//                        dialog.cancel();
//                    }
//                })
//                .setCancelable(false);
//
//        AlertDialog showAlert = alert.create();
//        showAlert.show();

        //cara 2 : counter
        if(counter_exit < 2){
            Toast.makeText(context, "Tekan back 1x lagi untuk keluar", Toast.LENGTH_SHORT).show();
            counter_exit += 1;
        }
        else if(counter_exit == 2){
            finish();
        }
    }
}
