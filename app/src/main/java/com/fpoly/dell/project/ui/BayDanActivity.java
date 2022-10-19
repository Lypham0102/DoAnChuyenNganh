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

import com.fpoly.dell.project.adapter.BayDanAdapter;
import com.fpoly.dell.project.adapter.VatNuoiAdapter;
import com.fpoly.dell.project.dao.BayDanDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class BayDanActivity extends AppCompatActivity {
    private static List<BayDan> dsbaydan = new ArrayList<>();
    private ListView lvbaydan;
    private BayDanAdapter adapter = null;
    private BayDanDao bayDanDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bay_dan);
        setTitle("Quản lý bầy đàn");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvbaydan = findViewById(R.id.lv_baydan);
        bayDanDao = new BayDanDao(BayDanActivity.this);

        dsbaydan = bayDanDao.getAllBayDan();

        adapter = new BayDanAdapter(dsbaydan, this);
        lvbaydan.setAdapter(adapter);

        lvbaydan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BayDanActivity.this, ThemBayDanActivity.class);
                Bundle b = new Bundle();

                b.putString("MABAYDAN", dsbaydan.get(position).getMaBayDan());
                b.putString("MATHUCAN", dsbaydan.get(position).getMaThucAn());
                b.putString("SOBAYDAN", dsbaydan.get(position).getSoBayDan());
                b.putString("SOLUONGVAT", dsbaydan.get(position).getSoLuongVat());
                //b.putString("SUCKHOE", dsvatnuoi.get(position).getSuckhoe());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvbaydan.setTextFilterEnabled(true);
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
                Intent a = new Intent(BayDanActivity.this, ThemBayDanActivity.class);
                startActivity(a);
            }
        });
    }

    private void initView() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}

