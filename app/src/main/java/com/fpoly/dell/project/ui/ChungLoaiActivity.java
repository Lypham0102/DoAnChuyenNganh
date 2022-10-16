package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.fpoly.dell.project.adapter.ChungLoaiAdapter;
import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class ChungLoaiActivity extends AppCompatActivity {
    private static List<ChungLoai> dsChungLoai = new ArrayList<>();
    private ListView lvChungLoai;
    private ChungLoaiAdapter adapter = null;
    private ChungLoaiDao chungLoaiDao;
    private FloatingActionButton fab;

//    @Override
//    protected void onResume() {
//        super.onResume();
//        dsChungLoai.clear();
//        dsChungLoai = chungLoaiDao.getAllChungLoai();
//        adapter.changeDataset(chungLoaiDao.getAllChungLoai());
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chung_loai);
        setTitle("Quản lý chủng loại");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvChungLoai = findViewById(R.id.lv_chungloai);
        fab=findViewById(R.id.fabAdd);
        chungLoaiDao = new ChungLoaiDao(ChungLoaiActivity.this);
        dsChungLoai = chungLoaiDao.getAllChungLoai();

        adapter = new ChungLoaiAdapter(dsChungLoai, this);

        lvChungLoai.setAdapter(adapter);
        lvChungLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChungLoaiActivity.this, ChiTietChungLoaiActivity.class);
                Bundle b = new Bundle();

                b.putString("MACHUNGLOAI", dsChungLoai.get(position).getMachungloai());
                b.putString("TENCHUNGLOAI", dsChungLoai.get(position).getTenvatnuoi());
                b.putString("VITRICHUONG", dsChungLoai.get(position).getVitrichuong());

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        lvChungLoai.setTextFilterEnabled(true);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ChungLoaiActivity.this, ThemChungLoaiActivity.class);
                startActivity(a);
            }
        });



    }
}
