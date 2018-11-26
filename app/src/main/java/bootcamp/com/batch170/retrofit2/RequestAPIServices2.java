package bootcamp.com.batch170.retrofit2;

import bootcamp.com.batch170.models.CreateUserModel;
import bootcamp.com.batch170.models.RegisterUserModel;
import bootcamp.com.batch170.models.listuser.ListUserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Eric on 12/10/2018.
 */

public interface RequestAPIServices2 {

    //method utk create user
    @FormUrlEncoded
    @POST("users")
    Call<CreateUserModel> createNewUser(@Header("Token") String token,
                                        @Header("Client-ID") String clientId,
                                        @Field("name") String name,
                                        @Field("job") String job);

    //method utk register user
    @FormUrlEncoded
    @POST("register")
    Call<RegisterUserModel> registerNewUser(@Field("email") String email,
                                            @Field("password") String password);


    //method utk get user list
    @GET("users")
    Call<ListUserModel> getListUser(@Query("page") int page);
}
