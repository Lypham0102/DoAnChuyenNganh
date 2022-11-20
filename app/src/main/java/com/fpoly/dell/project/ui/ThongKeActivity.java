package com.fpoly.dell.project.ui;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ThongKeActivity extends AppCompatActivity {

    private TextView tvThongKeNgay;
    private TextView tvThongKeThang;
    private TextView tvThongKeNam;
    private ChiPhiDao chiPhiDao;
    NumberFormat numberFormat = new DecimalFormat("#,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        initView();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("TỔNG CHI TIÊU");

        chiPhiDao = new ChiPhiDao(this);
        tvThongKeNgay.setText("Hôm nay: "+numberFormat.format(chiPhiDao.getDoanhThuTheoNgay())+" vnđ");
        tvThongKeThang.setText("Tháng Này: "+numberFormat.format(chiPhiDao.getDoanhThuTheoThang())+" vnđ");
        tvThongKeNam.setText("Năm Này: "+numberFormat.format(chiPhiDao.getDoanhThuTheoNam())+" vnđ");


    }

    private void initView() {
        tvThongKeNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThongKeThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvThongKeNam = (TextView) findViewById(R.id.tvThongKeNam);
    }
}
