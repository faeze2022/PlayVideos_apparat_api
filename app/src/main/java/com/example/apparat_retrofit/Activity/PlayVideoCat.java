package com.example.apparat_retrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_frame;
import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_title;
import static com.example.apparat_retrofit.Fragment.HomeFragment.Extra_visit;

import com.example.apparat_retrofit.R;

public class PlayVideoCat extends AppCompatActivity {
    WebView webView;
    TextView titletv,visit,download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_cat);

        Intent i=getIntent();
        String frame=i.getStringExtra(Extra_frame);
        String title=i.getStringExtra(Extra_title);
        int visit_cnt=i.getIntExtra(Extra_visit,0);
        webView =findViewById(R.id.webview);
        titletv=findViewById(R.id.tv_titl_video);
        visit=findViewById(R.id.tv_visitcnt_video);


        titletv.setText(title);
        visit.setText("بازدید: "+Integer.toString(visit_cnt));
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(frame);

    }
}
