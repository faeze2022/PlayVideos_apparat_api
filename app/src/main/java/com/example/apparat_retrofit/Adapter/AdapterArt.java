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

import static com.example.apparat_retrofit.Activity.ArtActivity.Extra_frame;
import static com.example.apparat_retrofit.Activity.ArtActivity.Extra_title;
import static com.example.apparat_retrofit.Activity.ArtActivity.Extra_visit;

public class AdapterArt extends RecyclerView.Adapter<AdapterArt.ViewHolder> {
    UserListResponse userListResponseData;
    MyOnItemClick myOnItemClick;
    Context context;
    public interface MyOnItemClick{
        void OnItem(int position);
    }

    public AdapterArt(UserListResponse userListResponseData, MyOnItemClick myOnItemClick, Context context) {
        this.userListResponseData = userListResponseData;
        this.myOnItemClick = myOnItemClick;
        this.context = context;
    }

    public AdapterArt(UserListResponse userListResponseData, Context context) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_art,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserListResponse.Categoryvideos artmodel=userListResponseData.getCategoryvideos().get(position);
        holder.titleTextart.setText(artmodel.getTitle());
        holder.sdateTextart.setText(artmodel.getSdate());
        holder.visitcntTextart.setText("تعداد بازدید: "+ String.valueOf(artmodel.getVisit_cnt()));
        holder.durationTextart.setText("مدت زمان: ثانیه"+String.valueOf(artmodel.getDuration()));
        Glide.with(holder.smallposterImageart.getContext()).load(artmodel.getSmall_poster()).into(holder.smallposterImageart);
        holder.artCardview.setTag(position);
        holder.artCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
           Intent artintent=new Intent(context, PlayVideoCat.class);
           artintent.putExtra(Extra_frame,artmodel.getFrame());
           artintent.putExtra(Extra_title,artmodel.getTitle());
           artintent.putExtra(Extra_visit,artmodel.getVisit_cnt());
           context.startActivity(artintent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userListResponseData.getCategoryvideos().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView artCardview;
        public ImageView smallposterImageart;
        public TextView titleTextart;
        public TextView sdateTextart;
        public TextView visitcntTextart;
        public TextView durationTextart;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artCardview=itemView.findViewById(R.id.cardview_art);
            smallposterImageart=itemView.findViewById(R.id.img_smallposter_art);
            titleTextart=itemView.findViewById(R.id.tv_title_art);
            sdateTextart=itemView.findViewById(R.id.tv_sdate_art);
            visitcntTextart=itemView.findViewById(R.id.tv_visitcnt_art);
            durationTextart=itemView.findViewById(R.id.tv_duration_art);

        }
    }
}
