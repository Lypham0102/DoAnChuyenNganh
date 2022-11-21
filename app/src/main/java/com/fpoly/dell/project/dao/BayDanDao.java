package com.fpoly.dell.project.dao;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class BayDanDao {
    private final SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "BayDan";
    public static final String SQL_BAYDAN = "CREATE TABLE BayDan (maBayDan text primary key, maThucAn text, soBayDan text, soLuongVat text);";
    private static final String TAG = "BayDanDAO";
    public BayDanDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertBayDan(BayDan bayDan){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maBayDan",bayDan.getMaBayDan());
        contentValues.put("maThucAn",bayDan.getMaThucAn());
        contentValues.put("soBayDan", bayDan.getSoBayDan());
        contentValues.put("soLuongVat",bayDan.getSoLuongVat());
        if (checkPrimaryKey(bayDan.getMaBayDan())){
            int result = db.update(TABLE_NAME,contentValues,"maBayDan=?", new
                    String[]{bayDan.getMaBayDan()});
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

    public List<BayDan> getAllBayDan(){
        List<BayDan> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            BayDan s = new BayDan();
            s.setMaBayDan(c.getString(0));
            s.setMaThucAn(c.getString(1));
            s.setSoBayDan(c.getString(2));
            s.setSoLuongVat(c.getString(3));


            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    private boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"maBayDan"};

        //WHERE clause
        String selection = "maBayDa =?";
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


    public int deleteBayDan(String maBayDan){
        int result = db.delete(TABLE_NAME,"maBayDan=?",new String[]{maBayDan});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateBayDan(String maBayDan, String soBayDan, String soLuongVat) {
        ContentValues values = new ContentValues();
        values.put("soBayDan", soBayDan);
        values.put("soLuongVat", soLuongVat);

        int result = db.update(TABLE_NAME, values, "maBayDan=?", new
                String[]{maBayDan});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}