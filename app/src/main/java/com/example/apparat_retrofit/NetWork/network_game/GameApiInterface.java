package com.example.apparat_retrofit.NetWork.network_game;

import com.example.apparat_retrofit.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface GameApiInterface {
    @GET("/etc/api/categoryVideos/cat/22/perpage/10")
    Single<UserListResponse> getUsersList();
    ////List<List<Data>>




}
