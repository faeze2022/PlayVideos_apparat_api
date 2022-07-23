package com.example.apparat_retrofit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.apparat_retrofit.Adapter.AdapterMusic;

import com.example.apparat_retrofit.NetWork.network_music.Apimusic;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserListResponse;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterMusic.MyOnItemClick {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    AppCompatImageView imageView;
    Toolbar toolbar;
    CollapsingToolbarLayout collToolbar;
    UserListResponse userListResponseData;
    private Apimusic apimusic;
    private static final String TAG = "GameActivity";
    private Disposable disposable;
    public static String Extra_title = "title";
    public static String Extra_visit = "visit_cnt";
    public static String Extra_frame = "frame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        toolbar = findViewById(R.id.toolbar_music);
        collToolbar = findViewById(R.id.collapsing_toolbar_music);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerview_music);
        swipeRefreshLayout = findViewById(R.id.refresh_music);
        swipeRefreshLayout.setOnRefreshListener(MusicActivity.this);

        apimusic = new Apimusic(this, TAG);
        apimusic.getMusic().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserListResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull UserListResponse userListResponse) {
                        userListResponseData = userListResponse;
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MusicActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        AdapterMusic adaptermusic = new AdapterMusic(userListResponseData, MusicActivity.this);
                        recyclerView.setAdapter(adaptermusic);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(MusicActivity.this, "خطای نامشخص", Toast.LENGTH_SHORT).show();

                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

            }
        }, 2000);

    }

    @Override
    public void OnItem(int position) {

    }
}