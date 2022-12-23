package com.fpoly.dell.project.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChiTietChiPhiActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private String machiphi;
    private String tenthucan;
    private String ngaynhap;
    private String soluong;
    private String giatien;
    private ChiPhiDao chiPhiDao;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dđ-MM-yyyy");
    private TextView tvMachiphi;
    private TextView tvTenthucan;
    private TextView tvNgaymua;

    private TextView tvGiatien;
    private TextView tvSoluong;

    NumberFormat numberFormat = new DecimalFormat("#,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_chi_phi);

        setTitle("Chi Tiết Chi Phí");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();

        chiPhiDao = new ChiPhiDao(ChiTietChiPhiActivity.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        machiphi = b.getString("MACHIPHI");
        tenthucan = b.getString("TENTHUCAN");
        ngaynhap = b.getString("NGAYNHAP");
        soluong = b.getString("SOLUONG");
        giatien = b.getString("GIATIEN");


        tvMachiphi.setText("Mã chi phí: "+machiphi);
        tvTenthucan.setText("Đồ nhập: "+tenthucan);
        tvNgaymua.setText("Ngày mua: "+ngaynhap);
        tvSoluong.setText("Số lượng: "+soluong);
        tvGiatien.setText("Giá Tiền: "+numberFormat.format(Long.parseLong(giatien))+" vnđ");
    }


    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    private void setDate(final Calendar calendar) {
        tvNgaymua.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);

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


    private void initView() {

        tvMachiphi = (TextView) findViewById(R.id.tv_machiphi);
        tvTenthucan = (TextView) findViewById(R.id.tv_tenthucan);
        tvNgaymua = (TextView) findViewById(R.id.tv_ngaymua);

        tvGiatien = (TextView) findViewById(R.id.tv_giatien);
        tvSoluong = (TextView) findViewById(R.id.tv_Soluong);
    }


}
