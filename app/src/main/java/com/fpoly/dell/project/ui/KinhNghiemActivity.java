package com.fpoly.dell.project.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.fpoly.dell.project1.R;

public class KinhNghiemActivity extends AppCompatActivity {
        private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinh_nghiem);

        setTitle("Tin tức thị trường");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        webView= findViewById(R.id.wb_kinhkngiem);
        String url = "https://www.anovafarm.vn/tin-tuc/tin-tuc-thi-truong-noi-bat-1";

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
}
