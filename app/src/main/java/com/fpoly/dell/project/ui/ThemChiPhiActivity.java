package com.fpoly.dell.project.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ThemChiPhiActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {


    private EditText edtMachiphi;
    private EditText edtTenthucan;
    private EditText edNgayMua;
    private EditText edtSoLuongChiPhi;
    private EditText edtGiatien;
    private Button btnHuy;
    private Button btnAdd;
    private ChiPhiDao chiPhiDao;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_chi_phi);
        setTitle("Thêm Chi Phí");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chiPhiDao = new ChiPhiDao(ThemChiPhiActivity.this);
                try {
                    if (validation() < 0) {
                        Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        ChiPhi hoaDon = new
                                ChiPhi(edtMachiphi.getText().toString(),edtTenthucan.getText().toString(),
                                sdf.parse(edNgayMua.getText().toString()),edtSoLuongChiPhi.getText().toString(),edtGiatien.getText().toString());
                        if (chiPhiDao.insertChiPhi(hoaDon) > 0) {
                            Toast.makeText(getApplicationContext(), "Thêm thành công",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new
                                    Intent(ThemChiPhiActivity.this, ChiPhiActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {
        edNgayMua.setText(sdf.format(calendar.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }
    }
    private int validation() {
        if
                (edtMachiphi.getText().toString().isEmpty() || edtTenthucan.getText().toString().isEmpty()
                || edNgayMua.getText().toString().isEmpty() || edtSoLuongChiPhi.getText().toString().isEmpty()
                || edtGiatien.getText().toString().isEmpty()
                ) {
            return -1;
        }
        return 1;
    }

    private void initView() {
        edtMachiphi = (EditText) findViewById(R.id.edtMachiphi);
        edtTenthucan = (EditText) findViewById(R.id.edtTenthucan);
        edNgayMua = (EditText) findViewById(R.id.edNgayMua);
        edtSoLuongChiPhi = (EditText) findViewById(R.id.edtSoLuong_ChiPhi);
        edtGiatien = (EditText) findViewById(R.id.edtGiatien);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }
}
