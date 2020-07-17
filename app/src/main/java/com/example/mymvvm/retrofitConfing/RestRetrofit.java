package com.example.mymvvm.retrofitConfing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class RestRetrofit {

    private static final String TAG = RestRetrofit.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private static RestRetrofit instance;
    private static ApiCall apiService;
    @SuppressLint("StaticFieldLeak")
    private static Context mcontext;

    public static String BaseUrl = "https://student.valuxapps.com/api/";


    private RestRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);




        //  httpClient.addInterceptor(interceptor).build();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SharedPreferences prefs =mcontext.getSharedPreferences("data", MODE_PRIVATE);
                String lang = prefs.getString("lang", "en");
                String token=prefs.getString("token","0");
                Request request = chain.request();
                Request newRequest;

                    newRequest = request.newBuilder()
                            .header("lang", lang)
                            .header("Content-Type", "application/json")
                            .header("Authorization",token)
                            .method(request.method(), request.body())
                            .build();
                    return chain.proceed(newRequest);


            }
        });

        OkHttpClient httpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiCall.class);
    }

    public static RestRetrofit getInstance(Context context) {
        mcontext = context;
        if (instance == null) {
            instance = new RestRetrofit();
        }
        return instance;
    }

    public static void inititobj() {
       instance=null;

    }



    ApiCall getClientService() {
        return apiService;
    }
}