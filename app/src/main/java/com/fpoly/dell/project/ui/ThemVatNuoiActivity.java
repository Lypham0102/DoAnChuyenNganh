package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class ThemVatNuoiActivity extends AppCompatActivity {

    private Spinner spVatnuoi;
    private EditText edSoluongvatnuoi;
    private EditText edThucan;
    private EditText edSuckhoe;


    private VatNuoiDao vatNuoiDao;
    private ChungLoaiDao chungLoaiDao;
    private String maChungLoai = "";
    private List<ChungLoai> chungLoaiList = new ArrayList<>();
    private EditText edMavatnuoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_vat_nuoi);

        setTitle("Thêm Vật Nuôi");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMavatnuoi = (EditText) findViewById(R.id.ed_mavatnuoi);
        spVatnuoi = (Spinner) findViewById(R.id.sp_Vatnuoi);
        getVatNuoi();
        edSoluongvatnuoi = (EditText) findViewById(R.id.ed_soluongvatnuoi);
        edThucan = (EditText) findViewById(R.id.ed_thucan);
        edSuckhoe = (EditText) findViewById(R.id.ed_suckhoe);

        spVatnuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maChungLoai =
                        chungLoaiList.get(spVatnuoi.getSelectedItemPosition()).getTenvatnuoi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMavatnuoi.setText(b.getString("MAVATNUOI"));
            String maChungLoai = b.getString("MACHUNGLOAI");
            edSoluongvatnuoi.setText(b.getString("SOLUONG"));
            edThucan.setText(b.getString("THUCAN"));
            edSuckhoe.setText(b.getString("SUCKHOE"));

            spVatnuoi.setSelection(checkPositionTheLoai(maChungLoai));
        }


    }

    private void getVatNuoi() {
        chungLoaiDao = new ChungLoaiDao(ThemVatNuoiActivity.this);
        chungLoaiList = chungLoaiDao.getAllChungLoai();
        ArrayAdapter<ChungLoai> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, chungLoaiList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVatnuoi.setAdapter(dataAdapter);
    }

    public void add(View view) {
        vatNuoiDao = new VatNuoiDao(ThemVatNuoiActivity.this);
        VatNuoi vatNuoi = new
                VatNuoi(edMavatnuoi.getText().toString(), maChungLoai, edSoluongvatnuoi.getText().toString(),
                edThucan.getText().toString(), edSuckhoe.getText().toString());
        try {
            if (validateForm() > 0) {
                if (vatNuoiDao.insertVatNuoi(vatNuoi) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemVatNuoiActivity.this, VatNuoiActivity.class);
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
        if (edMavatnuoi.getText().length() == 0 || edSoluongvatnuoi.getText().length() == 0
                || edThucan.getText().length() == 0 || edSuckhoe.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }

    public void Cancel(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < chungLoaiList.size(); i++) {
            if (strTheLoai.equals(chungLoaiList.get(i).getMachungloai())) {
                return i;
            }
        }
        return 0;
    }

}
