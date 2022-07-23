package com.example.apparat_retrofit.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apparat_retrofit.Activity.ArtActivity;
import com.example.apparat_retrofit.Activity.GameActivity;
import com.example.apparat_retrofit.Activity.MusicActivity;
import com.example.apparat_retrofit.Activity.SportActivity;
import com.example.apparat_retrofit.R;


public class CatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public CatFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toolbar cattoolar;
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cat, container, false);
        Button searchbtn;
        TextView game,sport,art,music;

        game=view.findViewById(R.id.tv_game);
        sport=view.findViewById(R.id.tv_sports);
        art=view.findViewById(R.id.tv_art);
        music=view.findViewById(R.id.tv_music);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), GameActivity.class);
                startActivity(i);

            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent=new Intent(getActivity(), SportActivity.class);
                startActivity(sintent);
            }
        });
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aintent=new Intent(getActivity(), ArtActivity.class);
                startActivity(aintent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent=new Intent(getActivity(), MusicActivity.class);
                startActivity(mintent);
            }
        });


        setHasOptionsMenu(true);
        cattoolar=view.findViewById(R.id.toolbar);
        cattoolar .setTitle("آپارات");
        cattoolar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));

        ((AppCompatActivity) getActivity()).setSupportActionBar(cattoolar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

    }


}
