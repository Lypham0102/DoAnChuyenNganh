package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.BayChuong;
import com.fpoly.dell.project.model.ChungLoai;

import java.util.ArrayList;
import java.util.List;

public class BayChuongDao {
    private final SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "BayChuong";
    public static final String SQL_BAYCHUONG = "CREATE TABLE ChungLoai(mabaychuong " +
            "text primary key, sochuong text);";
    private static final String TAG = "BayChuongDao";


    public BayChuongDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertBayChuong(BayChuong bayChuong){
        ContentValues contentValues = new ContentValues();
        contentValues.put("mabaychuong",bayChuong.getMabaychuong());
        contentValues.put("sochuong",bayChuong.getSochuong());
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
    public List<BayChuong> getAllBayChuong() {
        List<BayChuong> dsBayChuong = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            BayChuong ee = new BayChuong();
            ee.setMabaychuong(c.getString(0));
            ee.setSochuong(c.getString(1));


            dsBayChuong.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsBayChuong;
    }

    //delete

    public  int deleteBayChuongByID(String mabaychuong){
        int result = db.delete(TABLE_NAME, "machungloai=?", new
                String[]{mabaychuong});
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
