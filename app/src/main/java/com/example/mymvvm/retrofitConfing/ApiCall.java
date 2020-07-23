package com.example.mymvvm.retrofitConfing;




import com.example.mymvvm.model.LoginModel;
import com.example.mymvvm.model.LogoutModel;
import com.example.mymvvm.model.ProfileModel;
import com.example.mymvvm.model.RegisterModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterModel> register(@FieldMap Map<String, String> field);

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> login(@FieldMap Map<String, String> field);


    @GET("profile")
    Call<ProfileModel> profile();

    @FormUrlEncoded
    @POST("logout")
    Call<LogoutModel> logout(@FieldMap Map<String, String> field);

    @FormUrlEncoded
    @PUT("update-profile")
    Call<ProfileModel> updateProfile(@FieldMap Map<String, String> field);

//    @GET("profile/{id}")
//    Call<ProfileModel> profile(@Path("id")String s);

//    @GET("profile")
//    Call<ProfileModel> profile(@Query("id")String s);


}

