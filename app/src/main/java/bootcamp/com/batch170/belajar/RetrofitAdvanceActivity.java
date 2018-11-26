package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.UserListAdapter;
import bootcamp.com.batch170.models.CreateUserModel;
import bootcamp.com.batch170.models.RegisterUserModel;
import bootcamp.com.batch170.models.listuser.Datum;
import bootcamp.com.batch170.models.listuser.ListUserModel;
import bootcamp.com.batch170.retrofit2.APIUtilities2;
import bootcamp.com.batch170.retrofit2.RequestAPIServices2;
import bootcamp.com.batch170.utility.LoadingClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitAdvanceActivity extends Activity {
    private Context context = this;

    private Button buttonCreate, buttonRegister, buttonList;

    private RequestAPIServices2 apiServices2;

    private RecyclerView recylerList;
    private UserListAdapter adapterUser;
    private List<Datum> listUser = new ArrayList<>();
    private boolean isStillLoading = false;
    private int pageCount = 1;
    private int totalPageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_advance);

        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonList = (Button) findViewById(R.id.buttonList);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputCreateUser();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputRegisterUser();
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStillLoading) {
//                    panggilAPIListUser(pageCount);
                }
            }
        });

        recylerList = (RecyclerView) findViewById(R.id.recyclerList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        recylerList.setLayoutManager(layoutManager);

        //auto call
        panggilAPIListUser(pageCount);
    }

    private void showInputCreateUser(){
        final EditText token = new EditText(context);
        token.setHint("Token");

        final EditText clientId = new EditText(context);
        clientId.setHint("Client Id");

        final EditText name = new EditText(context);
        name.setHint("User Name");

        final EditText job = new EditText(context);
        job.setHint("User Job");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(token);
        layout.addView(clientId);
        layout.addView(name);
        layout.addView(job);
        layout.setPadding(10,10,10,10);

        new AlertDialog.Builder(context)
                .setTitle("Input Data")
                .setMessage("Lengkapi isi form")
                .setView(layout)
                .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //aksi jika SEND ditekan
                        String tokenTxt = token.getText().toString().trim();
                        String clientIdTxt = clientId.getText().toString().trim();
                        String nameTxt = name.getText().toString().trim();
                        String jobTxt = job.getText().toString().trim();

                        panggilAPICreateNewUser(tokenTxt, clientIdTxt, nameTxt, jobTxt);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void panggilAPICreateNewUser(String tokenTxt,
                                         String clientIdTxt,
                                         String nameTxt,
                                         String jobTxt){

        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "API Create New User...");
        loading.show();

        //deklarasikan retrofit
        apiServices2 = APIUtilities2.getAPIServices2();
        apiServices2.createNewUser(tokenTxt, clientIdTxt, nameTxt, jobTxt)
                .enqueue(new Callback<CreateUserModel>() {
                    @Override
                    public void onResponse(Call<CreateUserModel> call, Response<CreateUserModel> response) {
                        loading.dismiss();

                        //cek response
                        if(response.code() == 201){
                            //misal tampilkan created date
                            Toast.makeText(context, response.body().getCreatedAt(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(context, "Create User Gagal : "+response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateUserModel> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(context, "Create User onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showInputRegisterUser(){
        final EditText email = new EditText(context);
        email.setHint("Email");

        final EditText password = new EditText(context);
        password.setHint("Password");

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(email);
        layout.addView(password);
        layout.setPadding(20,20, 20, 20);

        new AlertDialog.Builder(context)
                .setTitle("Input Registrasi")
                .setMessage("Lengkapi isi form")
                .setView(layout)
                .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //aksi jika SEND ditekan
                        String emailTxt = email.getText().toString().trim();
                        String passwordTxt = password.getText().toString().trim();

                        panggilAPIRegisterNewUser(emailTxt, passwordTxt);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void panggilAPIRegisterNewUser(String emailTxt,
                                           String passwordTxt){

        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "API Register New User...");
        loading.show();

        apiServices2 = APIUtilities2.getAPIServices2();
        apiServices2.registerNewUser(emailTxt, passwordTxt)
                .enqueue(new Callback<RegisterUserModel>() {
                    @Override
                    public void onResponse(Call<RegisterUserModel> call, Response<RegisterUserModel> response) {
                        loading.dismiss();

                        if(response.code() == 201){
                            String token = response.body().getToken();

                            Toast.makeText(context, "TOKEN : "+token, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(context, "Register Gagal : "+response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterUserModel> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(context, "Register onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void panggilAPIListUser(int page){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context,
                "List user page "+page);
        loading.show();
        isStillLoading = true;

        apiServices2 = APIUtilities2.getAPIServices2();
        apiServices2.getListUser(page).enqueue(new Callback<ListUserModel>() {
            @Override
            public void onResponse(Call<ListUserModel> call, Response<ListUserModel> response) {
                loading.dismiss();
                isStillLoading = false;

                if(response.code() == 200){
                    if(listUser.size() == 0){
                        //jika listUser masih kosong, isi sebagai init awal
                        listUser = response.body().getData();

                        totalPageCount = response.body().getTotalPages();
                    }
                    else{
                        //jika listUser sudah ada isinya, jangan di init ulang, tapi
                        //tambahkan isinya
                        List<Datum> tmp = response.body().getData();
                        for(int n=0; n<tmp.size(); n++){
                            Datum data = tmp.get(n);
                            listUser.add(data);
                        }
                    }


                    if(listUser.size() > 0){
                        tampilkanListUser();
                    }
                    else{
                        //kosong hasilnya
                    }
                }
                else{
                    Toast.makeText(context, "List User Gagal : "+response.code()+" msg : "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListUserModel> call, Throwable t) {
                loading.dismiss();
                isStillLoading = false;
                Toast.makeText(context, "List User onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilkanListUser(){
        if(adapterUser == null){
            adapterUser = new UserListAdapter(context, listUser);
            recylerList.setAdapter(adapterUser);

            loadMorePaging();
        }

        adapterUser.notifyDataSetChanged();
    }

    private void loadMorePaging(){
        final LinearLayoutManager manager = ((LinearLayoutManager)
                recylerList.getLayoutManager());

        recylerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //login deteksi posisi terakhir dari item didalam recylerview
                //handle aksi scrolling vertical
                int lastItemPosition = manager.findLastVisibleItemPosition();
                boolean isScrollVertical = recyclerView.canScrollVertically(1);

                if(lastItemPosition == manager.getItemCount()-1
                        && !isScrollVertical
                        && !isStillLoading
                        && pageCount <= totalPageCount
                        ){
                    pageCount++;
                    panggilAPIListUser(pageCount);
                }
            }
        });
    }
}
