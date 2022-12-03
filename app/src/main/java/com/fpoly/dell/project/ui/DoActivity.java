package com.fpoly.dell.project.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fpoly.dell.project1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoActivity extends AppCompatActivity {
    private TextView text_Nhiet, text_Am, text_Gas;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);
        setTitle("Quản lí môi trường không khí");
        text_Nhiet = findViewById(R.id.text_Nhiet);
        text_Am = findViewById(R.id.text_Do);
        text_Gas = findViewById(R.id.text_Gas);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("HUMIDITY").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float floatValue = snapshot.getValue(float.class);
                text_Am.setText(String.valueOf(floatValue));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("PPM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float floatValue = snapshot.getValue(float.class);
                text_Gas.setText(String.valueOf(floatValue));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("TEMPERATURE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float floatValue = snapshot.getValue(float.class);
                text_Nhiet.setText(String.valueOf(floatValue));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
