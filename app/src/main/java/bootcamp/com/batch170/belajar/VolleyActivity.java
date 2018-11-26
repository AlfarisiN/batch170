package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.utility.LoadingClass;

public class VolleyActivity extends Activity {
    private Context context = this;

    private Button buttonAPI1;
    private TextView hasilAPI;
    private ImageView imageWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        buttonAPI1 = (Button) findViewById(R.id.buttonAPI1);
        buttonAPI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                panggilAPI1();
                panggilAPI2();
//                ambilImageWeb();
                ambilImageViaPicasso();
            }
        });

        hasilAPI = (TextView) findViewById(R.id.hasilAPI);
        imageWeb = (ImageView) findViewById(R.id.imageWeb);
    }

    private void panggilAPI1(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Silahkan tunggu...");
        loading.show();

        String urlAPI = "https://jsonplaceholder.typicode.com/posts";

        StringRequest requestAPI = new StringRequest(
                Request.Method.GET,
                urlAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        hasilAPI.setText(response);
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

    private void panggilAPI2(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(context, "Silahkan tunggu...");
        loading.show();

        String urlAPI = "https://jsonplaceholder.typicode.com/posts";

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
                                //parsing semua id dari jsonarray ditampilkan textview
                                String allID = "";
                                for (int n = 0; n < size; n++) {
                                    JSONObject object = response.getJSONObject(n);
                                    allID += object.getInt("id") + ", ";
                                }

                                hasilAPI.setText(allID);
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

    private void ambilImageWeb(){
        String urlImage = "https://vignette.wikia.nocookie.net/marvelcinematicuniverse/images/5/52/Empire_March_Cover_IW_6_Textless.png/revision/latest?cb=20180325144529";

        ImageRequest imgRequest = new ImageRequest(urlImage,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageWeb.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.FIT_XY,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Gambar Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(context).add(imgRequest);
    }

    private void ambilImageViaPicasso(){
        String urlImage = "https://vignette.wikia.nocookie.net/marvelcinematicuniverse/images/5/52/Empire_March_Cover_IW_6_Textless.png/revision/latest?cb=20180325144529";
        Picasso.get()
                .load(urlImage)
                .placeholder(R.mipmap.eye_circle)
                .error(R.mipmap.icon_clear)
                .into(imageWeb);
    }
}
