package com.fpoly.dell.project.ui;

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

import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.ThucAn;
import com.fpoly.dell.project1.R;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThemThucAnActivity extends AppCompatActivity{

    private Spinner spNCC;
    private EditText edMathucan;
    private EditText edTenthucan;
    private EditText edMaloai;
    private EditText edSoluong;
    private EditText edDongia;
    private ThucAnDao thucAnDao;

    private NCCDao nccDao;
    private String maNhacungcap = "";
    private List<NCC> nccList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuc_an);
        Random a = new Random();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("utf-8"));


        edMathucan = (EditText) findViewById(R.id.ed_Mathucan);
        edMathucan.setText(generatedString);
        edTenthucan = (EditText) findViewById(R.id.ed_Tenthucan);
        edMaloai = (EditText) findViewById(R.id.ed_Maloai);
        edSoluong = (EditText) findViewById(R.id.ed_Soluong);
        edDongia = (EditText) findViewById(R.id.ed_Dongia);
        spNCC = (Spinner) findViewById(R.id.sp_NCC);
        getNCC();


        spNCC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maNhacungcap =
                        nccList.get(spNCC.getSelectedItemPosition()).getTenNCC();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMathucan.setText(b.getString("MATHUCAN"));
            edTenthucan.setText(b.getString("TENTHUCAN"));
            String maNhaCungCap = b.getString("MANHACUNGCAP");
            edMaloai.setText(b.getString("MALOAI"));
            edSoluong.setText(b.getString("SOLUONG"));
            edDongia.setText(b.getString("DONGIA"));
            //spNCC.setSelection(checkPositionNhaCC(Mancc));
        }
    }

    private void getNCC() {
        nccDao = new NCCDao(ThemThucAnActivity.this);
        nccList = nccDao.getAllNCC();
        ArrayAdapter<NCC> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nccList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNCC.setAdapter(dataAdapter);
    }

    public void add(View view) {
        thucAnDao = new ThucAnDao(ThemThucAnActivity.this);
        ThucAn thucAn = new
                ThucAn(edMathucan.getText().toString(), edTenthucan.getText().toString(),
                edMaloai.getText().toString(), edSoluong.getText().toString(),edDongia.getText().toString(), maNhacungcap);
        try {
            if (validateForm()>0) {
                if (thucAnDao.insertThucAn(thucAn) > 0) {
                    Toast.makeText(getApplicationContext(), "Th??m th??nh c??ng",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemThucAnActivity.this, ThucAnActivity.class);
                    startActivity(a);

                } else {
                    Toast.makeText(getApplicationContext(), "Th??m th???t b???i",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    private int validateForm() {
        int check = 1;
        if (edTenthucan.getText().length() == 0
                || edMaloai.getText().length() == 0 || edSoluong.getText().length() == 0
                || edDongia.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "B???n ph???i nh???p ????? th??ng tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }

    public void Cancel(View view) {
        finish();
    }

    public int checkPositionNhaCC(String strCC) {
        for (int i = 0; i < nccList.size(); i++) {
            if (strCC.equals(nccList.get(i).getMaNCC())) {
                return i;
            }
        }
        return 0;
    }
}
