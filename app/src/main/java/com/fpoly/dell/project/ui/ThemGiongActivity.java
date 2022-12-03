package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.dao.GiongDao;
import com.fpoly.dell.project.model.Giong;
import com.fpoly.dell.project1.R;

public class ThemGiongActivity extends AppCompatActivity {
    //private Spinner spVatnuoi;
    private EditText edMagiong;
    private EditText edTengiong;
    private EditText edXuatxu;


    private GiongDao giongDao;
//    private ChungLoaiDao chungLoaiDao;
//    private String maChungLoai = "";
//    private List<ChungLoai> chungLoaiList = new ArrayList<>();
//    private EditText edMavatnuoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giong);

        setTitle("Thêm giống vật nuôi");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMagiong = (EditText) findViewById(R.id.ed_magiong);
        //spVatnuoi = (Spinner) findViewById(R.id.sp_Vatnuoi);
        //getVatNuoi();
        edTengiong = (EditText) findViewById(R.id.ed_tengiong);
        edXuatxu = (EditText) findViewById(R.id.ed_xuatxu);


        //spVatnuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                maChungLoai =
//                        chungLoaiList.get(spVatnuoi.getSelectedItemPosition()).getTenvatnuoi();
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMagiong.setText(b.getString("MAGIONG"));
            //String maChungLoai = b.getString("MACHUNGLOAI");
            edTengiong.setText(b.getString("TENGIONG"));
            edXuatxu.setText(b.getString("XUATXU"));
//            edSuckhoe.setText(b.getString("SUCKHOE"));
//
//            spVatnuoi.setSelection(checkPositionTheLoai(maChungLoai));
        }


    }

//    private void getVatNuoi() {
//        chungLoaiDao = new ChungLoaiDao(ThemVatNuoiActivity.this);
//        chungLoaiList = chungLoaiDao.getAllChungLoai();
//        ArrayAdapter<ChungLoai> dataAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, chungLoaiList);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spVatnuoi.setAdapter(dataAdapter);
//    }

    public void add(View view) {
        giongDao = new GiongDao(ThemGiongActivity.this);
        Giong giong = new
                Giong(edMagiong.getText().toString(), edTengiong.getText().toString(),
                edXuatxu.getText().toString());
        try {
            if (validateForm() > 0) {
                if (giongDao.insertGiong(giong) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm giống thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(ThemGiongActivity.this, GiongAcitivity.class);
                    startActivity(a);

                } else {
                    Toast.makeText(getApplicationContext(), "Thêm giống thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    private int validateForm() {
        int check = 1;
        if (edTengiong.getText().length() == 0
                || edXuatxu.getText().length() == 0 ) {
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
