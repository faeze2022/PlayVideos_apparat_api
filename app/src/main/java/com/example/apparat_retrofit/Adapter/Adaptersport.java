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

import static com.example.apparat_retrofit.Activity.SportActivity.Extra_frame;
import static com.example.apparat_retrofit.Activity.SportActivity.Extra_title;
import static com.example.apparat_retrofit.Activity.SportActivity.Extra_visit;

public class Adaptersport extends RecyclerView.Adapter<Adaptersport.ViewHolder> {
    UserListResponse userListResponseData;
    Context context;
    MyOnItemClick mlistener;
    public interface MyOnItemClick{
        void OnItemClick(int position);
    }

    public Adaptersport(UserListResponse userListResponseData, Context context, MyOnItemClick mlistener) {
        this.userListResponseData = userListResponseData;
        this.context = context;
        this.mlistener = mlistener;
    }

    public Adaptersport(UserListResponse userListResponseData, Context context) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @NonNull
    @Override
    public Adaptersport.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.sport_item,parent,false);
        return new Adaptersport.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptersport.ViewHolder holder, int position) {
        UserListResponse.Categoryvideos sportmodel=userListResponseData.getCategoryvideos().get(position);
        holder.titleTextsport.setText(sportmodel.getTitle());
        holder.sdateTextsport.setText(sportmodel.getSdate());
        holder.visitcntTextsport.setText("تعداد بازدید: "+ String.valueOf(sportmodel.getVisit_cnt()));
        holder.durationTextsport.setText("مدت زمان: ثانیه" +String.valueOf(sportmodel.getDuration()));
        Glide.with(holder.smallposterImagesport.getContext()).load(sportmodel.getSmall_poster()).into(holder.smallposterImagesport);
        holder.sportCardview.setTag(position);
        holder.sportCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= (int) v.getTag();
                Intent sp=new Intent(context, PlayVideoCat.class);
                sp.putExtra(Extra_title,sportmodel.getTitle());
                sp.putExtra(Extra_frame,sportmodel.getFrame());
                sp.putExtra(Extra_visit,sportmodel.getVisit_cnt());
                context.startActivity(sp);

            }
        });



    }

    @Override
    public int getItemCount() {
        return userListResponseData.getCategoryvideos().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView sportCardview;
        public ImageView smallposterImagesport;
        public TextView titleTextsport;
        public TextView sdateTextsport;
        public TextView visitcntTextsport;
        public TextView durationTextsport;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sportCardview=itemView.findViewById(R.id.cardview_sport);
            smallposterImagesport=itemView.findViewById(R.id.img_smallposter_sport);
            titleTextsport=itemView.findViewById(R.id.tv_title_sport);
            sdateTextsport=itemView.findViewById(R.id.tv_sdate_sport);
            visitcntTextsport=itemView.findViewById(R.id.tv_visitcnt_sport);
            durationTextsport=itemView.findViewById(R.id.tv_duration_sport);

        }
    }
}
