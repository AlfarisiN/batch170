package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.EmployeeListAdapter;
import bootcamp.com.batch170.models.LoginModel;
import bootcamp.com.batch170.models.ResponseCreateSouvenir;
import bootcamp.com.batch170.models.employee.DataEmployee;
import bootcamp.com.batch170.models.employee.DataList;
import bootcamp.com.batch170.models.employee.Employee;
import bootcamp.com.batch170.retrofit3.APIUtilities3;
import bootcamp.com.batch170.retrofit3.RequestAPIServices3;
import bootcamp.com.batch170.utility.LoadingClass;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarcommActivity extends Activity {
    private Context context = this;

    private EditText username, password, keyword, souvenirName, souvenirNotes, souvenirId;
    private Button buttonLogin, buttonSearchEmployee, buttonCreateSouvenir;
    private TextView token;

    private RecyclerView recyclerEmployee;
    private EmployeeListAdapter employeeListAdapter;
    private List<DataList> dataLists = new ArrayList<>();

    private RequestAPIServices3 apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcomm);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        keyword  = (EditText) findViewById(R.id.keyword);
        souvenirName = (EditText) findViewById(R.id.souvenirName);
        souvenirNotes = (EditText) findViewById(R.id.souvenirNotes);
        souvenirId = (EditText) findViewById(R.id.souvenirId);

        recyclerEmployee = (RecyclerView) findViewById(R.id.recyclerEmployee);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerEmployee.setLayoutManager(layoutManager);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Username kosong!", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Password kosong!", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginUser(username.getText().toString().trim(), password.getText().toString().trim());
                }
            }
        });

        buttonSearchEmployee = (Button) findViewById(R.id.buttonSearchEmployee);
        buttonSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Keyword Kosong!", Toast.LENGTH_SHORT).show();
                }
                else{
                    searchEmployee(keyword.getText().toString().trim());
                }
            }
        });

        buttonCreateSouvenir = (Button) findViewById(R.id.buttonCreateSouvenir);
        buttonCreateSouvenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(souvenirName.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Souvenir Name Kosong!", Toast.LENGTH_SHORT).show();
                }
                else if(souvenirNotes.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Souvenir Notes Kosong!", Toast.LENGTH_SHORT).show();
                }
                else if(souvenirId.getText().toString().trim().length() == 0){
                    Toast.makeText(context, "Souvenir Unit ID Kosong!", Toast.LENGTH_SHORT).show();
                }
                else{
                    createSouvenir(souvenirName.getText().toString().trim(), souvenirId.getText().toString().trim(), souvenirNotes.getText().toString().trim());
                }
            }
        });

        token = (TextView) findViewById(R.id.token);

    }

    private void loginUser(String username, String password){
        String json = APIUtilities3.generateLoginMap(username, password);
        RequestBody bodyRequest = RequestBody.create(APIUtilities3.mediaType(), json);

        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "Call API Login User...");
        loading.show();

        String contentType = "application/json";

        apiService = APIUtilities3.getAPIServices3();
        apiService.loginUser(contentType, bodyRequest).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                loading.dismiss();

                if(response.code() == 200){
                    if(response.body().getGeneratedToken() != null){
                        token.setText(response.body().getGeneratedToken());
                    }
                }
                else{
                    Toast.makeText(context, "Login User Gagal : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Login User onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchEmployee(String keyword){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "Call API Search Employee...");
        loading.show();

        String contentType = "application/json";
        String tokenAuthorization = token.getText().toString().trim();

        apiService = APIUtilities3.getAPIServices3();
        apiService.searchEmployeeName(contentType, tokenAuthorization, keyword).enqueue(new Callback<DataEmployee>() {
            @Override
            public void onResponse(Call<DataEmployee> call, Response<DataEmployee> response) {
                loading.dismiss();

                if(response.code() == 200){
                    if(response.body().getDataList().size() > 0){
                        recyclerEmployee.setVisibility(View.VISIBLE);

                        showDataList(response.body().getDataList());
                    }
                    else{
                        Toast.makeText(context, "Hasil pencarian kosong : "+response.code(), Toast.LENGTH_SHORT).show();
                        recyclerEmployee.setVisibility(View.GONE);
                    }
                }
                else{
                    Toast.makeText(context, "Search Employee Gagal : "+response.code(), Toast.LENGTH_SHORT).show();
                    recyclerEmployee.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DataEmployee> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Search Employee onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                recyclerEmployee.setVisibility(View.GONE);
            }
        });
    }

    private void showDataList(List<DataList> dataLists){
        employeeListAdapter = new EmployeeListAdapter(context, dataLists);
        recyclerEmployee.setAdapter(employeeListAdapter);
        employeeListAdapter.notifyDataSetChanged();
    }

    private void createSouvenir(String souvenir_name, String unit_id, String notes){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "Call API Create Souvenir...");
        loading.show();

        String contentType = "application/json";
        String tokenAuthorization = token.getText().toString().trim();
        String json = APIUtilities3.generateSouvenirMap(souvenir_name, unit_id, notes);
        RequestBody bodyRequest = RequestBody.create(APIUtilities3.mediaType(), json);

        apiService = APIUtilities3.getAPIServices3();
        apiService.createSouvenir(contentType, tokenAuthorization, bodyRequest).enqueue(new Callback<ResponseCreateSouvenir>() {
            @Override
            public void onResponse(Call<ResponseCreateSouvenir> call, Response<ResponseCreateSouvenir> response) {
                loading.dismiss();

                if(response.code() == 201){
                    Toast.makeText(context, "Result : "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Create Souvenir Gagal : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateSouvenir> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Create Souvenir onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
