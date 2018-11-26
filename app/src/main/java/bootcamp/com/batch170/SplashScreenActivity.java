package bootcamp.com.batch170;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import bootcamp.com.batch170.utility.Constanta;
import bootcamp.com.batch170.utility.SessionManager;

public class SplashScreenActivity extends Activity {
    private Context context = this;
    private TextView textVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textVersion = (TextView) findViewById(R.id.textVersion);
        getAppVersion();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //pindah ke screen berikutnya
                if(SessionManager.isRegister(context)){
                    //bypass ke mainmenu
                    Intent intent = new Intent(context, MainMenuActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, WelcomeActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, Constanta.SPLASH_DELAY_TIME);
    }

    //fungsi cek versi aplikasi
    /*
    fungsi cek versi
    menggunakan BuildConfig dari gradle.app
     */
    private void getAppVersion(){
        String versi = BuildConfig.VERSION_NAME;
        textVersion.setText(versi);

        Toast.makeText(context, versi, Toast.LENGTH_LONG).show();
    }
}
