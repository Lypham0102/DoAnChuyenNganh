package com.fpoly.dell.project.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.NoteDao;
import com.fpoly.dell.project1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChiTietNoteActivity extends AppCompatActivity {
    private String manote;
    private String tennote;
    private String noidung;
    private NoteDao noteDao;

    //private final SimpleDateFormat sdf = new SimpleDateFormat("dđ-MM-yyyy");
    private TextView tvManote;
    private TextView tvTennote;
    private TextView tvNoidung;


    NumberFormat numberFormat = new DecimalFormat("#,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_note);

        setTitle("Chi Tiết Ghi Chú");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();

        noteDao = new NoteDao(ChiTietNoteActivity.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        manote = b.getString("MANOTE");
        tennote = b.getString("TENNOTE");
        noidung = b.getString("NOIDUNG");

        tvManote.setText("Mã note: "+manote);
        tvTennote.setText("Tiêu đề: "+tennote);
        tvNoidung.setText("Nội dung: "+noidung);

    }

    private void initView() {

        tvManote = (TextView) findViewById(R.id.tv_manote);
        tvTennote = (TextView) findViewById(R.id.tv_tennote);
        tvNoidung = (TextView) findViewById(R.id.tv_noidung);
    }


}
