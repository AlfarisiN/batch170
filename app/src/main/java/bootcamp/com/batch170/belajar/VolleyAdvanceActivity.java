package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.CustomListAdvanceAdapter;
import bootcamp.com.batch170.models.ModelPhotos;
import bootcamp.com.batch170.utility.LoadingClass;

public class VolleyAdvanceActivity extends Activity {
    private Context context = this;

    private Button buttonAPI2;
    private ListView listAdvance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_advance);

        buttonAPI2 = (Button) findViewById(R.id.buttonAPI2);
        buttonAPI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAPI2();
            }
        });

        listAdvance = (ListView) findViewById(R.id.listAdvance);
    }

    private void loadAPI2(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Silahkan tunggu...");
        loading.show();

        String urlAPI = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest requestAPI = new JsonArrayRequest(
                Request.Method.GET,
                urlAPI,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loading.dismiss();

                        try {
                            int size = response.length();
                            if (size > 0) {
                                //ada data
                                List<ModelPhotos> models = new ArrayList<>();
                                for (int n = 0; n < size; n++) {
                                    JSONObject object = response.getJSONObject(n);

                                    ModelPhotos photo = new ModelPhotos();
                                    photo.setAlbumId(object.getInt("albumId"));
                                    photo.setId(object.getInt("id"));
                                    photo.setTitle(object.getString("title"));
                                    photo.setUrl(object.getString("url"));
                                    photo.setThumbnail(object.getString("thumbnailUrl"));

                                    models.add(photo);
                                }

                                //isi ke list view
                                setListPhotos(models);
                            } else {
                                Toast.makeText(context, "Tidak ada data", Toast.LENGTH_SHORT).show();
                            }
                        }catch(JSONException x){
                            System.out.println("JSON Parsing Exception : "+x.getMessage());
                            Log.d("json", x.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        //logic jika error
                        Toast.makeText(context, "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(context).add(requestAPI);
    }

    private void setListPhotos(List<ModelPhotos> models){
        CustomListAdvanceAdapter advanceAdapter =
                new CustomListAdvanceAdapter(context, models);

        listAdvance.setAdapter(advanceAdapter);
    }
}
