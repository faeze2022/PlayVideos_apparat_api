package com.example.apparat_retrofit.Activity;

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

import com.example.apparat_retrofit.NetWork.network_game.Apigame;

import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.Adapter.UserAdaptergame;
import com.example.apparat_retrofit.UserListResponse;
import com.google.android.material.appbar.CollapsingToolbarLayout;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GameActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, UserAdaptergame.MyOnItemClickListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    AppCompatImageView imageView;
    Toolbar toolbar;
    CollapsingToolbarLayout collToolbar;
    UserListResponse userListResponseData;
    private Apigame apigame;
    private static final String TAG = "GameActivity";
    private Disposable disposable;
    public static String Extra_title="title";
    public static String Extra_visit="visit_cnt";
    public static String Extra_frame="frame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        recyclerView = findViewById(R.id.recyclerview_game);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GameActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        apigame = new Apigame(this, TAG);

        toolbar = findViewById(R.id.toolbar_game);
        collToolbar = findViewById(R.id.collapsing_toolbar_game);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        imageView = findViewById(R.id.img_game);

        // call a method in which we have implement our GET type web API
        getUserListData();

    }

    private void getUserListData() {
        refreshLayout = findViewById(R.id.refresh_game);
        refreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) GameActivity.this);

        apigame.getUsersList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;

                    }

                    @Override
                    public void onSuccess(UserListResponse userListResponses) {
                        // setDataInRecyclerView();
                        // set a LinearLayoutManager with default vertical orientation
                        userListResponseData=userListResponses;

                        // call the constructor of UsersAdapter to send the reference and data to Adapter
                        UserAdaptergame userAdaptergame = new UserAdaptergame(userListResponseData ,(UserAdaptergame.MyOnItemClickListener)GameActivity.this,GameActivity.this);
                        recyclerView.setAdapter(userAdaptergame);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(GameActivity.this, "خطای نامشخص", Toast.LENGTH_SHORT).show();

                    }
                });




    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable!=null){
            disposable.dispose();

        }
    }

    @Override
    public void onRefresh() {
        Toast.makeText(GameActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public void OnItemClick(int position) {

    }
}