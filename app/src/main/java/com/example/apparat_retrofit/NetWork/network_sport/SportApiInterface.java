package com.example.apparat_retrofit.NetWork.network_sport;

import com.example.apparat_retrofit.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SportApiInterface {
    @GET("/etc/api/categoryVideos/cat/11/perpage/10")
    Single<UserListResponse> getSports();

}
