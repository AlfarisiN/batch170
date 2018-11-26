package bootcamp.com.batch170.retrofit;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Eric on 11/10/2018.
 */

public class APIUtilities {
    public static String BASE_URL = "https://api.nytimes.com/svc/search/v2";

    public static String BASE_IMAGE_URL_NYTIMES = "https://static01.nyt.com/";

    public static RequestAPIServices getAPIServices(){
        return RetrofitClient.getClient(BASE_URL).create(RequestAPIServices.class);
    }

    //generate get news Map params
    public static Map<String, String> generateNewsMap(String apiKey,
                                                      String q,
                                                      int page){
        Map<String, String> map = new HashMap<>();
        if(apiKey != null) map.put("api-key", apiKey);
        if(q != null) map.put("q", q);
        if(page != -1) map.put("page", ""+page);

        return map;
    }
}
