package com.example.apparat_retrofit.NetWork.network_music;

import android.content.Context;

import com.example.apparat_retrofit.UserListResponse;

import java.io.IOException;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apimusic {

    private static Retrofit retrofit = null;
    private MusicInterface apimusic;

    public Apimusic(Context context, String requestTag) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request oldRequest = chain.request();
                        Request.Builder newRequestBuilder = oldRequest.newBuilder();
                        //  newRequestBuilder.addHeader("Authorization","YOUR TOKEN");
                        newRequestBuilder.addHeader("Acccept", "application/json");
                        return chain.proceed(newRequestBuilder.build());

                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.aparat.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        apimusic = retrofit.create(MusicInterface.class);

    }

    public Single<UserListResponse> getMusic() {
        return apimusic.getMusic();
    }
}
