package com.example.apparat_retrofit.NetWork.network_music;

import com.example.apparat_retrofit.UserListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface MusicInterface {
    @GET("/etc/api/categoryVideos/cat/7/perpage/10")
    Single<UserListResponse> getMusic();
}
