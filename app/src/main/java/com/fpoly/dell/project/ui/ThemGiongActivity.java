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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giong);

        setTitle("Thêm giống vật nuôi");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        edMagiong = (EditText) findViewById(R.id.ed_magiong);
        edTengiong = (EditText) findViewById(R.id.ed_tengiong);
        edXuatxu = (EditText) findViewById(R.id.ed_xuatxu);

        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMagiong.setText(b.getString("MAGIONG"));
            edTengiong.setText(b.getString("TENGIONG"));
            edXuatxu.setText(b.getString("XUATXU"));
        }
    }


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


}
