package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.NewsAdapter;
import bootcamp.com.batch170.models.news.Doc;
import bootcamp.com.batch170.models.news.ModelNews;
import bootcamp.com.batch170.retrofit.APIUtilities;
import bootcamp.com.batch170.retrofit.RequestAPIServices;
import bootcamp.com.batch170.utility.LoadingClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends Activity {
    private Context context = this;

    private Button buttonLoadNews;
    private RecyclerView listRecycler;

    private RequestAPIServices apiServices;
    private List<Doc> arrayNews;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        buttonLoadNews = (Button) findViewById(R.id.buttonLoadNews);
        buttonLoadNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panggilAPI5();
            }
        });

        listRecycler = (RecyclerView) findViewById(R.id.listRecycler);
        //layoutmanager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
        );
        listRecycler.setLayoutManager(layoutManager);
        //ekstra fitur
        listRecycler.setDrawingCacheEnabled(true);
        listRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        listRecycler.setItemViewCacheSize(30);
        listRecycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void panggilAPI4(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Ambil news dng Retrofit...");
        loading.show();

        String apiKey = "9b693ffaa5fe451090146e5c90fbed78";
        String q = "indonesia";
        int page = 0;

        apiServices = APIUtilities.getAPIServices();
        apiServices.getNews(apiKey, q, page).enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews> call, Response<ModelNews> response) {
                loading.dismiss();

                if(response.code() == 200 & response.isSuccessful()){
                    //lakukan logic disini
                    Toast.makeText(context, "Request Sukses", Toast.LENGTH_SHORT).show();

                    if(arrayNews == null){
                        arrayNews = response.body().getResponse().getDocs();
                    }

                    setAdapterNews();
                }
                else{
                    Toast.makeText(context, "Gagal ambil News!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelNews> call, Throwable t) {
                loading.dismiss();

                Toast.makeText(context, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void panggilAPI5(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Ambil news dng Retrofit...");
        loading.show();

        String apiKey = "9b693ffaa5fe451090146e5c90fbed78";
        String q = "indonesia";
        int page = 0;

        apiServices = APIUtilities.getAPIServices();
        apiServices.getNews2(APIUtilities.generateNewsMap(apiKey, q, page)).enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews> call, Response<ModelNews> response) {
                loading.dismiss();

                if(response.code() == 200 & response.isSuccessful()){
                    //lakukan logic disini
                    Toast.makeText(context, "Request Sukses", Toast.LENGTH_SHORT).show();

                    if(arrayNews == null){
                        arrayNews = response.body().getResponse().getDocs();
                    }

                    setAdapterNews();
                }
                else{
                    Toast.makeText(context, "Gagal ambil News!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelNews> call, Throwable t) {
                loading.dismiss();

                Toast.makeText(context, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapterNews(){
        if(newsAdapter == null){
            newsAdapter = new NewsAdapter(context, arrayNews);
            listRecycler.setAdapter(newsAdapter);
        }

        newsAdapter.notifyDataSetChanged();
    }
}
