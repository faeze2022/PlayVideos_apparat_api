package com.example.apparat_retrofit.Adapter;

import static com.example.apparat_retrofit.Activity.MusicActivity.Extra_frame;
import static com.example.apparat_retrofit.Activity.MusicActivity.Extra_title;
import static com.example.apparat_retrofit.Activity.MusicActivity.Extra_visit;

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

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {
    UserListResponse userListResponseData;
    AdapterArt.MyOnItemClick myOnItemClick;
    Context context;
    public interface MyOnItemClick{
        void OnItem(int position);
    }

    public AdapterMusic(UserListResponse userListResponseData, AdapterArt.MyOnItemClick myOnItemClick, Context context) {
        this.userListResponseData = userListResponseData;
        this.myOnItemClick = myOnItemClick;
        this.context = context;
    }

    public AdapterMusic(UserListResponse userListResponseData, Context context) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_music,parent,false);
        return new AdapterMusic.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserListResponse.Categoryvideos musicmodel=userListResponseData.getCategoryvideos().get(position);
        holder.titleTextmusic.setText(musicmodel.getTitle());
        holder.sdateTextmusic.setText(musicmodel.getSdate());
        holder.visitcntTextmusic.setText("تعداد بازدید: "+ String.valueOf(musicmodel.getVisit_cnt()));
        holder.durationTextmusic.setText("مدت زمان: ثانیه"+String.valueOf(musicmodel.getDuration()));
        Glide.with(holder.smallposterImagemusic.getContext()).load(musicmodel.getSmall_poster()).into(holder.smallposterImagemusic);
        holder.musicCardview.setTag(position);
        holder.musicCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Intent musicintent=new Intent(context, PlayVideoCat.class);
                musicintent.putExtra(Extra_frame,musicmodel.getFrame());
                musicintent.putExtra(Extra_title,musicmodel.getTitle());
                musicintent.putExtra(Extra_visit,musicmodel.getVisit_cnt());
                context.startActivity(musicintent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return userListResponseData.getCategoryvideos().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView musicCardview;
        public ImageView smallposterImagemusic;
        public TextView titleTextmusic;
        public TextView sdateTextmusic;
        public TextView visitcntTextmusic;
        public TextView durationTextmusic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            musicCardview=itemView.findViewById(R.id.cardview_music);
            smallposterImagemusic=itemView.findViewById(R.id.img_smallposter_music);
            titleTextmusic=itemView.findViewById(R.id.tv_title_music);
            sdateTextmusic=itemView.findViewById(R.id.tv_sdate_music);
            visitcntTextmusic=itemView.findViewById(R.id.tv_visitcnt_music);
            durationTextmusic=itemView.findViewById(R.id.tv_duration_music);
        }
    }
}
