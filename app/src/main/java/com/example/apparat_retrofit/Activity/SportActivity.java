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

import com.example.apparat_retrofit.Adapter.Adaptersport;

import com.example.apparat_retrofit.NetWork.network_sport.ApiSport;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserListResponse;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SportActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Adaptersport.MyOnItemClick {
    public static String Extra_title="title";
    public static String Extra_frame="frame";
    public static String Extra_visit="visit_cnt";
    ApiSport apiSport;
    private static final String TAG = "SportActivity";
    private Disposable disposable;
    CollapsingToolbarLayout collapsing;
    AppCompatImageView imageView;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    UserListResponse userListResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        toolbar=findViewById(R.id.toolbar_sport);
        collapsing=findViewById(R.id.collapsing_toolbar_sport);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView=findViewById(R.id.recyclerview_sport);
        swipeRefreshLayout=findViewById(R.id.refresh_sport);
        swipeRefreshLayout.setOnRefreshListener(SportActivity.this);



        apiSport=new ApiSport(this, TAG);
        apiSport.getSports().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;

                    }

                    @Override
                    public void onSuccess(UserListResponse userListResponse) {
                        userListResponseData=userListResponse;
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SportActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        Adaptersport adaptersport=new Adaptersport(userListResponseData,SportActivity.this);
                        recyclerView.setAdapter(adaptersport);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SportActivity.this, "خطای نامشخص", Toast.LENGTH_SHORT).show();

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

            }
        },2000);

    }

    @Override
    public void OnItemClick(int position) {

    }
}
