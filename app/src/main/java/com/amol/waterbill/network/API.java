package com.amol.waterbill.network;




import com.amol.waterbill.model.GenericResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by amolmhatre on 7/9/20
 */
public interface API {

    @FormUrlEncoded
    @POST("water_user_register.php")
    Call<GenericResponse> water_user_register(
            @Field("connection") String connection,
            @Field("name") String name,
            @Field("mobile_no") String mobile_no,
            @Field("email") String email,
            @Field("address") String address);
//            @Field("status") String status);
}
