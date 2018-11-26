package bootcamp.com.batch170.belajar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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
import bootcamp.com.batch170.adapters.CustomListMultilevelAdapter;
import bootcamp.com.batch170.models.ModelUser;
import bootcamp.com.batch170.utility.LoadingClass;

public class VolleyMultilevelJSONActivity extends Activity {
    private Context context = this;

    private Button buttonAPI3;
    private ListView listAdvanceMultilevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_multilevel_json);

        buttonAPI3 = (Button) findViewById(R.id.buttonAPI3);
        buttonAPI3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panggilAPI3();
            }
        });

        listAdvanceMultilevel = (ListView) findViewById(R.id.listAdvanceMultilevel);
    }

    private void panggilAPI3(){
        final ProgressDialog loading = LoadingClass.loadingAnimation(context);
        loading.show();

        String urlAPI = "https://jsonplaceholder.typicode.com/users";

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
                                //parsing
                                List<ModelUser> models = new ArrayList<>();
                                for (int x = 0; x < size; x++) {
                                    ModelUser user = new ModelUser();

                                    //object lv1
                                    JSONObject objLv1 = response.getJSONObject(x);
                                    user.setName(objLv1.getString("name"));

                                    //object lv2 = address
                                    JSONObject objAddress = objLv1.getJSONObject("address");
                                    user.setStreet(objAddress.getString("street"));
                                    user.setSuite(objAddress.getString("suite"));
                                    user.setCity(objAddress.getString("city"));

                                    //object lv3 = geo
                                    JSONObject objGeo = objAddress.getJSONObject("geo");
                                    user.setLat(objGeo.getString("lat"));
                                    user.setLng(objGeo.getString("lng"));

                                    models.add(user);
                                }

                                setListUser(models);
                            } else {
                                Toast.makeText(context, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException x){
                            System.out.println("JSONException : "+x.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context, "Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(context).add(requestAPI);
    }

    private void setListUser(List<ModelUser> models){
        CustomListMultilevelAdapter adapter = new CustomListMultilevelAdapter(
                context, models
        );

        listAdvanceMultilevel.setAdapter(adapter);
    }
}
