package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.ChungLoai;
import com.fpoly.dell.project.model.ThucAn;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class ThucAnDao {

    private final SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "ThucAn";
    public static final String SQL_THUCAN = "CREATE TABLE ThucAn(mathucan text primary key, Tenthucan text, Maloai text, Soluong text, Dongia text)";
    private static final String TAG = "ThucAnDao";

    public ThucAnDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertThucAn(ThucAn thucAn){
        ContentValues contentValues = new ContentValues();
        contentValues.put("mathucan",thucAn.getMathucan());
        contentValues.put("Tenthucan", thucAn.getTenthucan());
        contentValues.put("Maloai",thucAn.getMaloai());
        contentValues.put("Soluong",thucAn.getSoluong());
        contentValues.put("Dongia",thucAn.getDongia());
        if (checkPrimaryKey(thucAn.getMathucan())){
            int result = db.update(TABLE_NAME,contentValues,"mathucan=?", new
                    String[]{thucAn.getMathucan()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME, null, contentValues) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<ThucAn> getAllThucAn(){
        List<ThucAn> dsSachh = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            ThucAn s = new ThucAn();
            s.setMathucan(c.getString(0));
            s.setTenthucan(c.getString(1));
            s.setMaloai(c.getString(2));
            s.setSoluong(c.getString(3));
            s.setDongia(c.getString(4));

            dsSachh.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSachh;
    }

    private boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"maThucAn"};
        //WHERE clause
        String selection = "maThucAn=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public int deleteThucAn(String maThucAn){
        int result = db.delete(TABLE_NAME,"maThucAn=?",new String[]{maThucAn});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateThucAn(String maThucAn, String Maloai, String Soluong, String Dongia) {
        ContentValues values = new ContentValues();
        values.put("MaLoai", Maloai);
        values.put("Soluong", Soluong);
        values.put("Dongia", Dongia);

        int result = db.update(TABLE_NAME, values, "maThucAn=?", new
                String[]{maThucAn});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}

