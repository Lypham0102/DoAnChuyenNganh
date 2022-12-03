package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project1.R;

public class ThemNCCActivity extends AppCompatActivity {
    //private Spinner spVatnuoi;
    private EditText edMancc;
    private EditText edTenncc;
    private EditText edDiachi;
    private EditText edSdt;


    private NCCDao nccDao;
    //private ChungLoaiDao chungLoaiDao;
    //private String maChungLoai = "";
    //private List<ChungLoai> chungLoaiList = new ArrayList<>();
    //private EditText edMavatnuoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ncc);

        setTitle("Thêm đại lí");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMancc = (EditText) findViewById(R.id.ed_mancc);

        edTenncc = (EditText) findViewById(R.id.ed_tenncc);
        edDiachi = (EditText) findViewById(R.id.ed_diachi);
        edSdt = (EditText) findViewById(R.id.ed_sdt);

//        spVatnuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                maChungLoai =
//                        chungLoaiList.get(spVatnuoi.getSelectedItemPosition()).getTenvatnuoi();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMancc.setText(b.getString("MANCC"));
//            String maChungLoai = b.getString("MACHUNGLOAI");
            edTenncc.setText(b.getString("TENNCC"));
            edDiachi.setText(b.getString("DIACHI"));
            edSdt.setText(b.getString("SDT"));

            //spVatnuoi.setSelection(checkPositionTheLoai(maChungLoai));
        }


    }

//    private void getNCC() {
//        chungLoaiDao = new ChungLoaiDao(ThemVatNuoiActivity.this);
//        chungLoaiList = chungLoaiDao.getAllChungLoai();
//        ArrayAdapter<ChungLoai> dataAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, chungLoaiList);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spVatnuoi.setAdapter(dataAdapter);
//    }

    public void add(View view) {
        nccDao = new NCCDao(ThemNCCActivity.this);
        NCC ncc = new
                NCC(edMancc.getText().toString(),  edTenncc.getText().toString(),
                edDiachi.getText().toString(), edSdt.getText().toString());
        try {
            if (validateForm() > 0) {
                if (nccDao.insertNCC(ncc) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm đại lí thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemNCCActivity.this, NCCActivity.class);
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
        if (edTenncc.getText().length() == 0
                || edDiachi.getText().length() == 0 || edSdt.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }


        return check;
    }

    public void Cancel(View view) {
        finish();
    }

//    public int checkPositionTheLoai(String strTheLoai) {
//        for (int i = 0; i < chungLoaiList.size(); i++) {
//            if (strTheLoai.equals(chungLoaiList.get(i).getMachungloai())) {
//                return i;
//            }
//        }
//        return 0;
//    }
}
