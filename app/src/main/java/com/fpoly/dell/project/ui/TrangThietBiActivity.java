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

import com.fpoly.dell.project.adapter.TrangThietBiAdapter;
import com.fpoly.dell.project.adapter.VatNuoiAdapter;
import com.fpoly.dell.project.dao.TrangThietBiDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.TrangThietBi;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TrangThietBiActivity extends AppCompatActivity {
    private static List<TrangThietBi> dstrangthietbi = new ArrayList<>();
    private ListView lvtrangthietbi;
    private TrangThietBiAdapter adapter = null;
    private TrangThietBiDao trangThietBiDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_thiet_bi);
        setTitle("Quản lý trang thiết bị");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvtrangthietbi = findViewById(R.id.lv_trangthietbi);
        trangThietBiDao = new TrangThietBiDao(TrangThietBiActivity.this);


        dstrangthietbi = trangThietBiDao.getAllTrangThietBi();

        adapter = new TrangThietBiAdapter(dstrangthietbi, this);
        lvtrangthietbi.setAdapter(adapter);

        lvtrangthietbi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrangThietBiActivity.this, ThemTrangThietBiActivity.class);
                Bundle b = new Bundle();

                b.putString("MATRANGTHIETBI", dstrangthietbi.get(position).getMaTrangThietBi());
                b.putString("TENTRANGTHIETBI", dstrangthietbi.get(position).getTenTrangThietBi());
                b.putString("GIATTT", dstrangthietbi.get(position).getGiaTTT());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvtrangthietbi.setTextFilterEnabled(true);
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
                Intent a = new Intent(TrangThietBiActivity.this, ThemTrangThietBiActivity.class);
                startActivity(a);
            }
        });
    }

    private void initView() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
