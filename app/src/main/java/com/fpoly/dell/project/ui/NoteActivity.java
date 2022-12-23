package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project.adapter.ChiPhiAdapter;
import com.fpoly.dell.project.adapter.NoteAdapter;
import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.NoteDao;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project.model.Note;
import com.fpoly.dell.project1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private List<Note> dsNote = new ArrayList<>();
    private NoteAdapter adapter = null;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle("Ghi chú");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView lvNote = findViewById(R.id.lv_note);

        FloatingActionButton fab = findViewById(R.id.fabAdd);


        NoteDao noteDao = new NoteDao(NoteActivity.this);
        try {
            dsNote = noteDao.getAllNote();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new NoteAdapter(this, dsNote);
        lvNote.setAdapter(adapter);

        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoteActivity.this, ChiTietNoteActivity.class);
                Bundle b = new Bundle();

                b.putString("MANOTE", dsNote.get(position).getManote());
                b.putString("TENNOTE", dsNote.get(position).getTennote());
                b.putString("NOIDUNG", dsNote.get(position).getNoidung());


                intent.putExtras(b);
                startActivity(intent);
            }
        });


        // TextFilter
        lvNote.setTextFilterEnabled(true);
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
                Intent a = new Intent(NoteActivity.this, ThemNoteActivity.class);
                startActivity(a);
            }
        });
    }

}
