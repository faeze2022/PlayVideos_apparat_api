package com.example.apparat_retrofit.NetWork.network_art;

import com.example.apparat_retrofit.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ArtInterface {
    @GET("/etc/api/categoryVideos/cat/17/perpage/10")
    Single<UserListResponse> getArt();
}
