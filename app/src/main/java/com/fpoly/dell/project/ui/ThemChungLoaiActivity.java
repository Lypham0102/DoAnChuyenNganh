package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project1.R;

public class ThemChungLoaiActivity extends AppCompatActivity {

    private EditText edtMaChungLoai;
    private EditText edtTenChungLoai;
    private EditText edtViTriChuong;
    private Button btnHuyAdd;
    private Button btnAdd;
    private ChungLoaiDao chungLoaiDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_chung_loai);
        setTitle("Thêm Chủng Loại");
        initView();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTheloai();
            }
        });
        btnHuyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addTheloai() {
        chungLoaiDao = new ChungLoaiDao(ThemChungLoaiActivity.this);
        ChungLoai theloai = new ChungLoai(edtMaChungLoai.getText().toString(), edtTenChungLoai.getText().toString(),
                edtViTriChuong.getText().toString());
        try {
            if (validateForm() > 0) {
                if (chungLoaiDao.insertChungLoai(theloai) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(ThemChungLoaiActivity.this, ChungLoaiActivity.class);
                    startActivity(b);

                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());

        }
    }
    private int validateForm() {
        int check = 1;
        if (edtMaChungLoai.getText().length() == 0 || edtTenChungLoai.getText().length() == 0
                || edtViTriChuong.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }



        return check;
    }

    private void initView() {
        edtMaChungLoai = (EditText) findViewById(R.id.edtMaChungLoai);
        edtTenChungLoai = (EditText) findViewById(R.id.edtTenChungLoai);
        edtViTriChuong = (EditText) findViewById(R.id.edtViTriChuong);
        btnHuyAdd = (Button) findViewById(R.id.btnHuy);
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }
}
