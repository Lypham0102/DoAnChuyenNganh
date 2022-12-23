package com.fpoly.dell.project.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.dao.BayDanDao;
import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.GiongDao;
import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.NoteDao;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project.model.Giong;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.Note;
import com.fpoly.dell.project1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ThemNoteActivity extends AppCompatActivity {

    private EditText edtManote;
    private EditText edtTennote;
    private EditText edtNoidung;
    private Button btnHuy;
    private Button btnAdd;
    private NoteDao noteDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_note);
        setTitle("Thêm Note");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        edtManote = (EditText) findViewById(R.id.edtManote);
        edtTennote = (EditText) findViewById(R.id.edtTennote);
        edtNoidung = (EditText) findViewById(R.id.edtNoiDung);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
                edtManote.setText(b.getString("MANOTE"));
                edtTennote.setText(b.getString("TENNOTE"));
                edtNoidung.setText(b.getString("NOIDUNG"));
            }
        }


        public void add(View view) {
            noteDao = new NoteDao(ThemNoteActivity.this);
            Note note = new
                    Note(edtManote.getText().toString(), edtTennote.getText().toString(),
                    edtNoidung.getText().toString());
            try {
                if (validateForm() > 0) {
                    if (noteDao.add(note) > 0) {
                        Toast.makeText(getApplicationContext(), "Thêm note thành công",
                                Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(ThemNoteActivity.this, NoteActivity.class);
                        startActivity(a);

                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm note thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        }

        private int validateForm() {
            int check = 1;
            if (edtTennote.getText().length() == 0
                    || edtNoidung.getText().length() == 0 ) {
                Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                check = -1;
            }


            return check;
        }

        public void Cancel(View view) {
            finish();
        }
}