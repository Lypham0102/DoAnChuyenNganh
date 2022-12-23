package com.fpoly.dell.project.ui;

import static android.R.layout.simple_spinner_item;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpoly.dell.project.dao.BayDanDao;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project.model.ThucAn;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class XemBayDanActivity extends AppCompatActivity {
    private Spinner spThucan;
    //private EditText edSoluongvatnuoi;
    private EditText edSobaydan;
    private EditText edSoluongvat;


    private BayDanDao bayDanDao;
    private ThucAnDao thucAnDao;
    private String maThucAn = "";
    private List<ThucAn> thucAnList = new ArrayList<>();
    private EditText edMabaydan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_bay_dan);

        setTitle("Chi tiết bầy đàn");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMabaydan = (EditText) findViewById(R.id.ed_mabaydan);
        spThucan = (Spinner) findViewById(R.id.sp_Thucan);
        getThucAn();
        edSobaydan = (EditText) findViewById(R.id.ed_sobaydan);
        edSoluongvat = (EditText) findViewById(R.id.ed_soluongvat);


        spThucan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maThucAn =
                        thucAnList.get(spThucan.getSelectedItemPosition()).getTenthucan();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMabaydan.setText(b.getString("MABAYDAN"));
            String maThucAn = b.getString("MATHUCAN");
            edSobaydan.setText(b.getString("SOBAYDAN"));
            edSoluongvat.setText(b.getString("SOLUONGVAT"));
            //aedSuckhoe.setText(b.getString("SUCKHOE"));

            spThucan.setSelection(checkPositionThucAn(maThucAn));
        }


    }

    private void getThucAn() {
        thucAnDao = new ThucAnDao(XemBayDanActivity.this);
        thucAnList = thucAnDao.getAllThucAn();
        ArrayAdapter<ThucAn> dataAdapter = new ArrayAdapter<>(this,
                simple_spinner_item, thucAnList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spThucan.setAdapter(dataAdapter);
    }

    public void add(View view) {
        bayDanDao = new BayDanDao(XemBayDanActivity.this);
        BayDan bayDan = new
                BayDan(edMabaydan.getText().toString(), maThucAn, edSobaydan.getText().toString(),
                edSoluongvat.getText().toString());
        try {
            if (validateForm() > 0) {
                if (bayDanDao.insertBayDan(bayDan) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(XemBayDanActivity.this, BayDanActivity.class);
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
        if (edMabaydan.getText().length() == 0 || edSobaydan.getText().length() == 0
                || edSoluongvat.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void Cancel(View view) {
        finish();
    }

    public void tiem(View view){}
    public void heal(View view){}

    public int checkPositionThucAn(String strThucAn) {
        for (int i = 0; i < thucAnList.size(); i++) {
            if (strThucAn.equals(thucAnList.get(i).getMathucan())) {
                return i;
            }
        }
        return 0;
    }
}
