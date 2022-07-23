package com.example.apparat_retrofit.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apparat_retrofit.R;


public class DownloadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DownloadFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Toolbar toolar;

        View view= inflater.inflate(R.layout.fragment_download, container, false);
        setHasOptionsMenu(true);
        toolar=view.findViewById(R.id.toolbar);
        toolar .setTitle("آپارات");
        toolar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.toolbar_menu:
                 Toast.makeText(getContext(), "You clicked menu", Toast.LENGTH_SHORT).show();

                break;
            case R.id.toolbar_search:
                Toast.makeText(getContext(), "You clicked search", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
