package bootcamp.com.batch170;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bootcamp.com.batch170.utility.Constanta;

public class LoginActivity extends Activity {
    private Context context = this;

    private EditText inputUsername, inputPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.userName);
        inputPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });
    }

    private void submitLogin(){
        //ambil username
        String username = inputUsername.getText().toString().trim();

        //ambil password
        String password = inputPassword.getText().toString().trim();

        if(username.equalsIgnoreCase("")){
            Toast.makeText(context,
                    getResources().getString(R.string.username_empty),
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.length() == 0){
            Toast.makeText(context,
                    getResources().getString(R.string.password_empty),
                    Toast.LENGTH_SHORT).show();
        }
        else{
            //semua sudah diisi
            Intent intent = new Intent(context, MainMenuActivity.class);
            intent.putExtra(Constanta.KEY_USERNAME, username);
            intent.putExtra(Constanta.KEY_PASSWORD, password);
            startActivity(intent);
        }
    }
}
