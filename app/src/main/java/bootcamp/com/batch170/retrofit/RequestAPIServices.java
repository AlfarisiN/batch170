package bootcamp.com.batch170.retrofit;

import java.util.Map;

import bootcamp.com.batch170.models.news.ModelNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Eric on 11/10/2018.
 */

public interface RequestAPIServices {
    //deklarasikan semua method yg ada di API yg akan kita gunakan

    //ambil list news
    @GET("/articlesearch.json")
    Call<ModelNews> getNews(@Query("api-key") String apiKey,
                            @Query("q") String q,
                            @Query("page") int page);


    //ambil list news 2 (map)
    @GET("/articlesearch.json")
    Call<ModelNews> getNews2(@QueryMap Map<String, String> allParams);
}
