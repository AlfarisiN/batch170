package bootcamp.com.batch170;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    private Context context = this;

    private TextView textLogin;
    private Button btnDaftar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textLogin = (TextView) findViewById(R.id.textLogin);
        btnDaftar1 = (Button) findViewById(R.id.buttonDaftar1);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnDaftar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
