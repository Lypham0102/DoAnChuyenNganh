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

import com.fpoly.dell.project.adapter.ThucAnAdapter;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.model.ThucAn;
import com.fpoly.dell.project1.R;

import java.util.ArrayList;
import java.util.List;

public class ThucAnActivity extends AppCompatActivity{
    private static List<ThucAn> dsthucan = new ArrayList<>();
    private ListView lvthucan;
    private ThucAnAdapter adapter = null;
    private ThucAnDao thucAnDao;
    private FloatingActionButton fabAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_an);
        setTitle("Quản Lý thức ăn");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvthucan = findViewById(R.id.lv_thucan);
        thucAnDao = new ThucAnDao(ThucAnActivity.this);


        dsthucan = thucAnDao.getAllThucAn();

        adapter = new ThucAnAdapter(dsthucan, this);
        lvthucan.setAdapter(adapter);

        lvthucan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ThucAnActivity.this, ThemThucAnActivity.class);
                Bundle b = new Bundle();

                b.putString("MATHUCAN", dsthucan.get(position).getMathucan());
                b.putString("TENTHUCAN", dsthucan.get(position).getTenthucan());
                b.putString("MALOAI", dsthucan.get(position).getMaloai());
                b.putString("SOLUONG", dsthucan.get(position).getSoluong());
                b.putString("DONGIA", dsthucan.get(position).getDongia());

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvthucan.setTextFilterEnabled(true);
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
        fabAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ThucAnActivity.this, ThemThucAnActivity.class);
                startActivity(a);
            }
        });
    }

    private void initView() {
        fabAd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
