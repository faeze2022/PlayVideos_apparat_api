package com.example.apparat_retrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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

import static com.example.apparat_retrofit.Activity.GameActivity.Extra_frame;
import static com.example.apparat_retrofit.Activity.GameActivity.Extra_title;
import static com.example.apparat_retrofit.Activity.GameActivity.Extra_visit;

public class UserAdaptergame extends RecyclerView.Adapter<UserAdaptergame.UserViewHolder> {
    UserListResponse userListResponseData;
    MyOnItemClickListener mlistener;
    Context context;
    public interface MyOnItemClickListener{
        void OnItemClick(int position);
    }

    public UserAdaptergame(UserListResponse userListResponseData, MyOnItemClickListener mlistener, Context context) {
        this.userListResponseData = userListResponseData;
        this.mlistener = mlistener;
        this.context = context;
    }

    public UserAdaptergame(Context context, UserListResponse userListResponseData) {
        this.context = context;
        this.userListResponseData = userListResponseData;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View myview=layoutInflater.inflate(R.layout.game_item,parent,false);
        return new UserViewHolder(myview);

    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserListResponse.Categoryvideos gamemodel=userListResponseData.getCategoryvideos().get(position);
        holder.titleTextgame.setText(gamemodel.getTitle());
        holder.sdateTextgame.setText(gamemodel.getSdate());
        holder.visitcntTextgame.setText("بازدید: "+String.valueOf (gamemodel.getVisit_cnt()));
        holder.durationTextgame.setText("مدت زمان: ثانیه "+String.valueOf(gamemodel.getDuration()));
        Glide.with(holder. smallposterImagegame.getContext()).load(gamemodel.getSmall_poster()).into(holder. smallposterImagegame);
        holder.GameCardview.setTag(position);
        holder.GameCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Intent gameintent=new Intent(context, PlayVideoCat.class);
                gameintent.putExtra(Extra_title,gamemodel.getTitle());
                gameintent.putExtra(Extra_frame,gamemodel.getFrame());
                gameintent.putExtra(Extra_visit,gamemodel.getVisit_cnt());
                context.startActivity(gameintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListResponseData.getCategoryvideos().size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public CardView GameCardview;
        public ImageView smallposterImagegame;
        public TextView titleTextgame;
        public TextView sdateTextgame;
        public TextView visitcntTextgame;
        public TextView durationTextgame;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            GameCardview =itemView.findViewById(R.id.cardview_item_game);
            smallposterImagegame=itemView.findViewById(R.id.img_smallposter_game);
            titleTextgame=itemView.findViewById(R.id.tv_title_game);
            sdateTextgame=itemView.findViewById(R.id.tv_sdate_game);
            visitcntTextgame=itemView.findViewById(R.id.tv_visitcnt_game);
            durationTextgame=itemView.findViewById(R.id.tv_duration_game);

        }
    }
}
