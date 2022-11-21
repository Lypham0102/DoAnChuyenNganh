package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.adapter.GiongAdapter;
import com.fpoly.dell.project.adapter.VatNuoiAdapter;
import com.fpoly.dell.project.dao.GiongDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.Giong;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GiongAcitivity extends AppCompatActivity {

    private static List<Giong> dsgiong = new ArrayList<>();
    private ListView lvgiong;
    private GiongAdapter adapter = null;
    private GiongDao giongDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giong);
        setTitle("Quản lý giống");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvgiong = findViewById(R.id.lv_giong);
        giongDao = new GiongDao(GiongAcitivity.this);


        dsgiong = giongDao.getAllGiong();

        adapter = new GiongAdapter(dsgiong, this);
        lvgiong.setAdapter(adapter);

        lvgiong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GiongAcitivity.this, ThemGiongActivity.class);
                Bundle b = new Bundle();

                b.putString("MAGIONG", dsgiong.get(position).getMaGiong());
                b.putString("TENGIONG", dsgiong.get(position).getTenGiong());
                b.putString("XUATXU", dsgiong.get(position).getXuatXu());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvgiong.setTextFilterEnabled(true);
        EditText edSeach = findViewById(R.id.edSearch);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - " +
                        "Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(GiongAcitivity.this, ThemGiongActivity.class);
                startActivity(a);
            }
        });
    }
    private void initView() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
