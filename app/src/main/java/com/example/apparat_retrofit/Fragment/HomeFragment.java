package com.example.apparat_retrofit.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apparat_retrofit.Adapter.UsersAdapter;
import com.example.apparat_retrofit.NetWork.network_home.Api;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserListResponse;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, UsersAdapter.MyOnItemClickListener {

    private static final String TAG = "HomeFragment";
    public static String Extra_title = "title";
    public static String Extra_visit = "visit_cnt";
    public static String Extra_frame = "frame";
    RecyclerView recyclerView;
    UserListResponse userListResponseData;
    SwipeRefreshLayout refreshLayout;
    private Disposable disposable;
    private Api api;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);//Add this sentence to the menu

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toolbar mToolbarContact;
        setHasOptionsMenu(true);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mToolbarContact = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recycle_home_frag);
        refreshLayout = view.findViewById(R.id.refresh_home);
        api = new Api(this, TAG);

        mToolbarContact.setTitle("آپارات");
        mToolbarContact.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));
        mToolbarContact.inflateMenu(R.menu.main_menu);


        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarContact);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getUserListData();
        return view;

    }

    private void getUserListData() {


        /* Api is a class in which we define a method getClient() that returns the API Interface class object
        getUsersList() is a method in API Interface class, in this method we define our API sub url*/

        api.getUsersList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;

                    }

                    @Override
                    public void onSuccess(UserListResponse userListResponses) {
                        // setDataInRecyclerView();
                        userListResponseData = userListResponses;
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                        recyclerView.setHasFixedSize(true);
                        UsersAdapter usersAdapter = new UsersAdapter(userListResponseData, (UsersAdapter.MyOnItemClickListener) HomeFragment.this, getActivity());
                        usersAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(usersAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "خطای نامشخص", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // this sentence is useless. You don't need to add it
        menu.clear();

    }


   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.toolbar_menu:
                Toast.makeText(getContext(), "You clicked menu", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

            break;
        }
        return true;

    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    @Override
    public void OnItemClick(int position) {

    }
}
