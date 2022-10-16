package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.fpoly.dell.project1.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout chungloai;
    private LinearLayout vatnuoi;
    private LinearLayout kinhnghiem;
    private LinearLayout chiphi;
    private LinearLayout thongke;
    private LinearLayout thucan;
    private LinearLayout baychuong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Quản Lý Chăn Nuôi");
        initView();

        chungloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, ChungLoaiActivity.class);
                startActivity(b);
            }
        });
        vatnuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, VatNuoiActivity.class);
                startActivity(b);
            }
        });
        kinhnghiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, KinhNghiemActivity.class);
                startActivity(b);
            }
        });
        chiphi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, ChiPhiActivity.class);
                startActivity(b);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, ThongKeActivity.class);
                startActivity(b);
            }
        });
        thucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, ThucAnActivity.class);
                startActivity(b);
            }
        });
        baychuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, BayChuongActivity.class);
                startActivity(b);
            }
        });

    }

    private void initView() {
        chungloai = (LinearLayout) findViewById(R.id.chungloai);
        vatnuoi = (LinearLayout) findViewById(R.id.vatnuoi);
        thucan = (LinearLayout) findViewById(R.id.thucan);
        kinhnghiem = (LinearLayout) findViewById(R.id.kinhnghiem);
        chiphi = (LinearLayout) findViewById(R.id.chiphi);
        thongke = (LinearLayout) findViewById(R.id.thongke);
        baychuong= (LinearLayout) findViewById(R.id.baydan);


    }
}
