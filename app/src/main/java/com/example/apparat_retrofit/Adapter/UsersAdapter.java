package com.example.apparat_retrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apparat_retrofit.Activity.PlayVideoCat;
import com.example.apparat_retrofit.R;
import com.example.apparat_retrofit.UserListResponse;

import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_frame;
import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_title;
import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_visit;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    UserListResponse userListResponseData;

    private MyOnItemClickListener mlistener;
        Context context;
    public interface MyOnItemClickListener{
        boolean onMenuItemClick(MenuItem menuItem);

        void  OnItemClick(int position);

    }

    public UsersAdapter(UserListResponse userListResponseData, MyOnItemClickListener mlistener, Context context) {
        this.userListResponseData = userListResponseData;
        this.mlistener = mlistener;
        this.context = context;
    }

    public UsersAdapter(Context context, UserListResponse userListResponseData) {
        this.context = context;
        this.userListResponseData = userListResponseData;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent,false);
        UserViewHolder usersViewHolder = new UserViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserListResponse.Categoryvideos homemodel=userListResponseData.getCategoryvideos().get(position);
        holder.titleTexthome.setText(homemodel.getTitle());
        holder.sdateTexthome.setText(homemodel.getSdate());
        holder.visitcntTexthome.setText("بازدید: "+String.valueOf(homemodel.getVisit_cnt()));
        holder.durationTexthome.setText("مدت زمان: ثانیه "+String.valueOf(homemodel.getDuration()));
        Glide.with(holder.smallposterImagehome.getContext()).load(homemodel.getSmall_poster()).into(holder.smallposterImagehome);
        holder.HomeCardview.setTag(position);
        holder.HomeCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Intent intent=new Intent(context, PlayVideoCat.class);
                intent.putExtra(Extra_title,homemodel.getTitle());
                intent.putExtra(Extra_visit,homemodel.getVisit_cnt());
                intent.putExtra(Extra_frame,homemodel.getFrame());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userListResponseData.getCategoryvideos().size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public CardView HomeCardview;
        public ImageView smallposterImagehome;
        public TextView titleTexthome;
        public TextView sdateTexthome;
        public TextView visitcntTexthome;
        public TextView durationTexthome;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            HomeCardview=itemView.findViewById(R.id.card_item_home);
            smallposterImagehome=itemView.findViewById(R.id.img_home_smallposter);
            titleTexthome=itemView.findViewById(R.id.tv_home_title);
            sdateTexthome=itemView.findViewById(R.id.tv_home_sdate);
            visitcntTexthome=itemView.findViewById(R.id.tv_home_visitcnt);
            durationTexthome=itemView.findViewById(R.id.tv_home_duration);
        }
    }
}
