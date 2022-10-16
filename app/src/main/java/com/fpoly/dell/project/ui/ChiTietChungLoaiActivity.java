package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project1.R;

public class ChiTietChungLoaiActivity extends AppCompatActivity {

    private EditText edtMaChungLoai;
    private EditText edtTenChungLoai;
    private EditText edtViTriChuong;
    private Button btnHuy;

    private String machungloai;
    private String tenchungloai;
    private String vitrichuong;
    private ChungLoaiDao chungLoaiDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_chung_loai);

        initView();
        setTitle("Chi Tiết Chủng Loại");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        chungLoaiDao = new ChungLoaiDao(ChiTietChungLoaiActivity.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
       machungloai = b.getString("MACHUNGLOAI");
        tenchungloai = b.getString("TENCHUNGLOAI");
        vitrichuong = b.getString("VITRICHUONG");


        edtMaChungLoai.setText(machungloai);
        edtTenChungLoai.setText(tenchungloai);
        edtViTriChuong.setText(vitrichuong);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        edtMaChungLoai = (EditText) findViewById(R.id.edtMaChungLoai);
        edtTenChungLoai = (EditText) findViewById(R.id.edtTenChungLoai);
        edtViTriChuong = (EditText) findViewById(R.id.edtViTriChuong);
        btnHuy = (Button) findViewById(R.id.btnHuy);

    }

    public void UpdateChungLoai(View view) {

        if (validateForm()>0) {
            if (chungLoaiDao.updateChungLoai(edtMaChungLoai.getText().toString(),
                    edtTenChungLoai.getText().toString(), edtViTriChuong.getText().toString()) > 0) {
                Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();

                Intent a = new Intent(ChiTietChungLoaiActivity.this, ChungLoaiActivity.class);
                startActivity(a);
            }
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
}
