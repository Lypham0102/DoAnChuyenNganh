package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.fpoly.dell.project.adapter.VatNuoiAdapter;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class VatNuoiActivity extends AppCompatActivity {
    private static List<VatNuoi> dsvatnuoi = new ArrayList<>();
    private ListView lvvatnuoi;
    private VatNuoiAdapter adapter = null;
    private VatNuoiDao vatNuoiDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_nuoi);
        setTitle("Quản Lý Vật Nuôi");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvvatnuoi = findViewById(R.id.lv_vatnuoi);
        vatNuoiDao = new VatNuoiDao(VatNuoiActivity.this);


        dsvatnuoi = vatNuoiDao.getAllVatNuoi();

        adapter = new VatNuoiAdapter(dsvatnuoi, this);
        lvvatnuoi.setAdapter(adapter);

        lvvatnuoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VatNuoiActivity.this, ThemVatNuoiActivity.class);
                Bundle b = new Bundle();

                b.putString("MAVATNUOI", dsvatnuoi.get(position).getMavatnuoi());
                b.putString("MACHUNGLOAI", dsvatnuoi.get(position).getMachungloai());
                b.putString("SOLUONG", dsvatnuoi.get(position).getSoluong());
                b.putString("THUCAN", dsvatnuoi.get(position).getLoaithucan());
                b.putString("SUCKHOE", dsvatnuoi.get(position).getSuckhoe());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvvatnuoi.setTextFilterEnabled(true);
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
                Intent a = new Intent(VatNuoiActivity.this, ThemVatNuoiActivity.class);
                startActivity(a);
            }
        });
    }

    private void initView() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
