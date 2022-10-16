package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.ChungLoai;

import java.util.ArrayList;
import java.util.List;

public class ChungLoaiDao {
    private final SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "ChungLoai";
    public static final String SQL_CHUNGLOAI = "CREATE TABLE ChungLoai(machungloai " +
            "text primary key, tenchungloai text, vitrichuong text);";
    private static final String TAG = "ChungLoaiDao";


    public ChungLoaiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertChungLoai(ChungLoai chungLoai){
        ContentValues contentValues = new ContentValues();
        contentValues.put("machungloai",chungLoai.getMachungloai());
        contentValues.put("tenchungloai",chungLoai.getTenvatnuoi());
        contentValues.put("vitrichuong",chungLoai.getVitrichuong());
        try {
            if (db.insert(TABLE_NAME,null,contentValues)==-1){
                return -1;
            }
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
        return 1;
    }


    //getall
    public List<ChungLoai> getAllChungLoai() {
        List<ChungLoai> dsChungLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            ChungLoai ee = new ChungLoai();
            ee.setMachungloai(c.getString(0));
            ee.setTenvatnuoi(c.getString(1));
            ee.setVitrichuong(c.getString(2));

            dsChungLoai.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsChungLoai;
    }

    //delete

    public  int deleteChungloaiByID(String matheloai){
        int result = db.delete(TABLE_NAME, "machungloai=?", new
                String[]{matheloai});
        if (result==0)
            return  -1;
        return 1;
    }


    public int updateChungLoai(String machungloai, String tenchungloai, String vitrichuong) {
        ContentValues values = new ContentValues();
        values.put("machungloai", machungloai);
        values.put("tenchungloai", tenchungloai);
        values.put("vitrichuong", vitrichuong);

        int result = db.update(TABLE_NAME, values, "machungloai=?", new
                String[]{machungloai});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

}
