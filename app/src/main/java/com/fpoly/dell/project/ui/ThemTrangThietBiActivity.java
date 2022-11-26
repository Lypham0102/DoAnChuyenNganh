package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.TrangThietBiDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.TrangThietBi;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class ThemTrangThietBiActivity extends AppCompatActivity {
    private Spinner spNcc;
    private EditText edMatrangthietbi;
    private EditText edTentrangthietbi;
    private EditText edGiaTTT;


    private TrangThietBiDao trangThietBiDao;
    private NCCDao nccDao;
    private String maNhacungcap = "";
    private List<NCC> nccList = new ArrayList<>();
    //private EditText edMavatnuoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_trang_thiet_bi);

        setTitle("Thêm Trang Thiết Bị");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMatrangthietbi = (EditText) findViewById(R.id.ed_matrangthietbi);
        edTentrangthietbi = (EditText) findViewById(R.id.ed_tentrangthietbi);
        edGiaTTT = (EditText) findViewById(R.id.ed_giattt);
        spNcc = (Spinner) findViewById(R.id.sp_Ncc);
        getTTT();
        spNcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maNhacungcap =
                        nccList.get(spNcc.getSelectedItemPosition()).getTenNCC();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMatrangthietbi.setText(b.getString("MATRANGTHIETBI"));
            String maNhaCungCap = b.getString("MANHACUNGCAP");
            edTentrangthietbi.setText(b.getString("TENTRANGTHIETBI"));
            edGiaTTT.setText(b.getString("GIATTT"));
//            edSuckhoe.setText(b.getString("SUCKHOE"));
//
            spNcc.setSelection(checkPositionTheLoai(maNhaCungCap));
        }


    }

    private void getTTT() {
        nccDao = new NCCDao(ThemTrangThietBiActivity.this);
        nccList = nccDao.getAllNCC();
        ArrayAdapter<NCC> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nccList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNcc.setAdapter(dataAdapter);
    }

    public void add(View view) {
        trangThietBiDao = new TrangThietBiDao(ThemTrangThietBiActivity.this);
        TrangThietBi trangThietBi = new
                TrangThietBi(edMatrangthietbi.getText().toString(), edTentrangthietbi.getText().toString(),
                edGiaTTT.getText().toString(),maNhacungcap);
        try {
            if (validateForm() > 0) {
                if (trangThietBiDao.insertTrangThietBi(trangThietBi) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thiết bị thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemTrangThietBiActivity.this, TrangThietBiActivity.class);
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
        if (edMatrangthietbi.getText().length() == 0
                || edTentrangthietbi.getText().length() == 0 || edGiaTTT.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }

    public void Cancel(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < nccList.size(); i++) {
            if (strTheLoai.equals(nccList.get(i).getMaNCC())) {
                return i;
            }
        }
        return 0;
    }
}
