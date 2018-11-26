package bootcamp.com.batch170.retrofit3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import bootcamp.com.batch170.retrofit2.RetrofitClient2;
import okhttp3.MediaType;

/**
 * Created by Eric on 25/10/2018.
 */

public class APIUtilities3 {
    private static String BASE_URL_API_3 = "http://139.162.5.173:8080/marcomm-ws/";

    public static RequestAPIServices3 getAPIServices3(){
        return RetrofitClient2.getClient(BASE_URL_API_3)
                .create(RequestAPIServices3.class);
    }

    public static MediaType mediaType() {
        return okhttp3.MediaType.parse("application/json; charset=utf-8");
    }

    //generate login body request
    public static String generateLoginMap(String username, String password){
        Map<String, String> map = new HashMap<>();
        if(username != null) map.put("username", username);
        if(password != null) map.put("password", password);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().create();
        String json = gson.toJson(map);

        return json;
    }

    //generate souvenir body request
    public static String generateSouvenirMap(String name, String unit, String notes){
        Map<String, Object> map = new HashMap<>();
        if(name != null) map.put("name", name);
        if(notes != null) map.put("notes", notes);

        if(unit != null){
            Map<String, String> unitObj = new HashMap<>();
            unitObj.put("id", unit);

            map.put("unit", unitObj);
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().create();
        String json = gson.toJson(map);

        return json;
    }
}
