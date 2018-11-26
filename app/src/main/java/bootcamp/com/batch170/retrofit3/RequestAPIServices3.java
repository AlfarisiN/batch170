package bootcamp.com.batch170.retrofit3;

import bootcamp.com.batch170.models.LoginModel;
import bootcamp.com.batch170.models.ResponseCreateSouvenir;
import bootcamp.com.batch170.models.employee.DataEmployee;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Eric on 25/10/2018.
 */

public interface RequestAPIServices3 {
    //login
    @POST("api/login")
    Call<LoginModel> loginUser(@Header("Content-Type") String contentType,
                               @Body RequestBody data);

    //search employee name
    @GET("api/employee/name/{keyword}")
    Call<DataEmployee> searchEmployeeName(@Header("Content-Type") String contentType,
                                          @Header("Authorization") String tokenAuthorization,
                                          @Path("keyword") String keyword);

    //create souvenir
    @POST("api/souvenir/create")
    Call<ResponseCreateSouvenir> createSouvenir(@Header("Content-Type") String contentType,
                                                @Header("Authorization") String tokenAuthorization,
                                                @Body RequestBody data);
}
