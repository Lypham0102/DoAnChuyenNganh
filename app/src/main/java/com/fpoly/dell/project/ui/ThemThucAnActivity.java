package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project.model.ThucAn;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class ThemThucAnActivity extends AppCompatActivity{

    private EditText edMathucan;
    private EditText edTenthucan;
    private EditText edMaloai;
    private EditText edSoluong;
    private EditText edDongia;
    private ThucAnDao thucAnDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuc_an);

        setTitle("Thêm thức ăn");

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        edMathucan = (EditText) findViewById(R.id.ed_Mathucan);
        //getThucAn();
        edTenthucan = (EditText) findViewById(R.id.ed_Tenthucan);
        edMaloai = (EditText) findViewById(R.id.ed_Maloai);
        edSoluong = (EditText) findViewById(R.id.ed_Soluong);
        edDongia = (EditText) findViewById(R.id.ed_Dongia);
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMathucan.setText(b.getString("MATHUCAN"));
            edTenthucan.setText(b.getString("TENTHUCAN"));
            edMaloai.setText(b.getString("MALOAI"));
            edSoluong.setText(b.getString("SOLUONG"));
            edDongia.setText(b.getString("DONGIA"));
        }


    }

//    private void getThucAn() {
//    }

    /*private void getVatNuoi() {
        chungLoaiDao = new ChungLoaiDao(ThemVatNuoiActivity.this);
        chungLoaiList = chungLoaiDao.getAllChungLoai();
        ArrayAdapter<ChungLoai> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, chungLoaiList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVatnuoi.setAdapter(dataAdapter);
    }*/

    public void add(View view) {
        thucAnDao = new ThucAnDao(ThemThucAnActivity.this);
        ThucAn thucAn = new
                ThucAn(edMathucan.getText().toString(), edTenthucan.getText().toString(),
                edMaloai.getText().toString(), edSoluong.getText().toString(),edDongia.getText().toString());
        try {
            if (validateForm()>0) {
                if (thucAnDao.insertThucAn(thucAn) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemThucAnActivity.this, ThucAnActivity.class);
                    startActivity(a);

                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    private int validateForm() {
        int check = 1;
        if (edMathucan.getText().length() == 0 || edTenthucan.getText().length() == 0
                || edMaloai.getText().length() == 0 || edSoluong.getText().length() == 0
                || edDongia.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }

    public void Cancel(View view) {
        finish();
    }

    /*public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < chungLoaiList.size(); i++) {
            if (strTheLoai.equals(chungLoaiList.get(i).getMachungloai())) {
                return i;
            }
        }
        return 0;
    }*/
}
