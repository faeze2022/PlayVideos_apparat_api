package com.example.apparat_retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apparat_retrofit.Adapter.AdapterArt;

import com.example.apparat_retrofit.NetWork.network_art.Apiart;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserListResponse;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArtActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterArt.MyOnItemClick {
    private static final String TAG = "ArtActivity";
    Apiart apiart;
    private Disposable disposable;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbar;
    AppCompatImageView imageView;
    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    UserListResponse userListResponseData;
    public static String Extra_frame = "frame";
    public static String Extra_title = "title";
    public static String Extra_visit = "visit_cnt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar_art);
        toolbar = findViewById(R.id.toolbar_art);
        imageView = findViewById(R.id.img_art);
        refreshLayout = findViewById(R.id.refresh_art);
        recyclerView = findViewById(R.id.recyclerview_art);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        apiart = new Apiart(this, TAG);
        apiart.getArt().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(UserListResponse userListResponse) {
                        userListResponseData = userListResponse;
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ArtActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        AdapterArt adapterArt = new AdapterArt(userListResponseData, (AdapterArt.MyOnItemClick) ArtActivity.this, ArtActivity.this);
                        recyclerView.setAdapter(adapterArt);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ArtActivity.this, "خطای نامشخص", Toast.LENGTH_SHORT).show();

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

    }

    @Override
    public void OnItem(int position) {

    }
}
