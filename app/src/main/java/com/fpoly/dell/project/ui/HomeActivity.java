package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fpoly.dell.project1.R;

public class HomeActivity extends AppCompatActivity {

    //private LinearLayout chungloai;
    private LinearLayout vatnuoi;
    //private LinearLayout kinhnghiem;
    private LinearLayout chiphi;
    private LinearLayout thongke;
    private LinearLayout thucan;
    private LinearLayout baydan;
    private LinearLayout checkbd;
    private LinearLayout giong;
    private LinearLayout trangthietbi;
    private LinearLayout ncc;
    private LinearLayout nhietdogas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("        QUẢN LÝ TRANG TRẠI");
        initView();

//        chungloai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent b = new Intent(HomeActivity.this, ChungLoaiActivity.class);
//                startActivity(b);
//            }
//        });
//        vatnuoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent b = new Intent(HomeActivity.this, VatNuoiActivity.class);
//                startActivity(b);
//            }
//        });
//        kinhnghiem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent b = new Intent(HomeActivity.this, KinhNghiemActivity.class);
//                startActivity(b);
//            }
//        });
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
        baydan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, BayDanActivity.class);
                startActivity(b);
            }
        });

        giong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, GiongAcitivity.class);
                startActivity(b);
            }
        });

        trangthietbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, TrangThietBiActivity.class);
                startActivity(b);
            }
        });

        ncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, NCCActivity.class);
                startActivity(b);
            }
        });
        nhietdogas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(HomeActivity.this, DoActivity.class);
                startActivity(b);
            }
        });

    }

    private void initView() {
        //chungloai = (LinearLayout) findViewById(R.id.chungloai);
        //vatnuoi = (LinearLayout) findViewById(R.id.vatnuoi);
        thucan = (LinearLayout) findViewById(R.id.thucan);
        //kinhnghiem = (LinearLayout) findViewById(R.id.kinhnghiem);
        chiphi = (LinearLayout) findViewById(R.id.chiphi);
        thongke = (LinearLayout) findViewById(R.id.thongke);
        baydan= (LinearLayout) findViewById(R.id.baydan);

        giong= (LinearLayout) findViewById(R.id.giong);
        trangthietbi = (LinearLayout) findViewById(R.id.trangthietbi);
        ncc = (LinearLayout) findViewById(R.id.ncc);
        nhietdogas = (LinearLayout) findViewById(R.id.nhietdogas);
    }
}
