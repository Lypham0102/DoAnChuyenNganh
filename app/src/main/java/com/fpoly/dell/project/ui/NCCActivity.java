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

import com.fpoly.dell.project.adapter.NCCAdapter;
import com.fpoly.dell.project.adapter.VatNuoiAdapter;
import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.VatNuoi;
import com.fpoly.dell.project1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NCCActivity extends AppCompatActivity {
    private static List<NCC> dsncc = new ArrayList<>();
    private ListView lvncc;
    private NCCAdapter adapter = null;
    private NCCDao nccDao;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncc);
        setTitle("Danh sách đại lí");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        lvncc = findViewById(R.id.lv_ncc);
        nccDao = new NCCDao(NCCActivity.this);

        dsncc = nccDao.getAllNCC();

        adapter = new NCCAdapter(dsncc, this);
        lvncc.setAdapter(adapter);

        lvncc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NCCActivity.this, ThemNCCActivity.class);
                Bundle b = new Bundle();

                b.putString("MANCC", dsncc.get(position).getMaNCC());
                b.putString("TENNCC", dsncc.get(position).getTenNCC());
                b.putString("DIACHI", dsncc.get(position).getDiaChi());
                b.putString("SDT", dsncc.get(position).getSdt());


                intent.putExtras(b);
                startActivity(intent);
            }
        });
        initView();

        lvncc.setTextFilterEnabled(true);
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
                Intent a = new Intent(NCCActivity.this, ThemNCCActivity.class);
                startActivity(a);
            }
        });
    }

    private void initView() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
