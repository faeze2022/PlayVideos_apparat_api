package com.example.apparat_retrofit.NetWork.network_home;

import com.example.apparat_retrofit.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface ApiInterface {
@GET("/etc/api/categoryVideos/cat/15/perpage/10")
Single<UserListResponse> getUsersList();
}






