package bootcamp.com.batch170.retrofit2;

/**
 * Created by Eric on 12/10/2018.
 */

public class APIUtilities2 {
    private static String BASE_URL_API_2 = "https://reqres.in/api/";

    public static RequestAPIServices2 getAPIServices2(){
        return RetrofitClient2.getClient(BASE_URL_API_2)
                .create(RequestAPIServices2.class);
    }
}
