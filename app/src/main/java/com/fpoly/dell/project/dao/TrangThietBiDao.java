package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.TrangThietBi;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class TrangThietBiDao {
    private final SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TrangThietBi";
    public static final String SQL_TRANGTHIETBI = "CREATE TABLE TrangThietBi (maTrangThietBi text primary key , tenTrangThietBi text, giaTTT text)";
    private static final String TAG = "TrangThietBiDAO";

    public TrangThietBiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertTrangThietBi(TrangThietBi trangThietBi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTrangThietBi",trangThietBi.getMaTrangThietBi());
        contentValues.put("tenTrangThietBi", trangThietBi.getTenTrangThietBi());
        contentValues.put("giaTTT",trangThietBi.getGiaTTT());
        if (checkPrimaryKey(trangThietBi.getMaTrangThietBi())){
            int result = db.update(TABLE_NAME,contentValues,"maTrangThietBi=?", new
                    String[]{trangThietBi.getMaTrangThietBi()});
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
    private boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"maTrangThietBi"};
        //WHERE clause
        String selection = "maTrangThietBi=?";
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

    public List<TrangThietBi> getAllTrangThietBi(){
        List<TrangThietBi> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            TrangThietBi s = new TrangThietBi();
            s.setMaTrangThietBi(c.getString(0));
            s.setTenTrangThietBi(c.getString(1));
            s.setGiaTTT(c.getString(2));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    public int deleteTrangThietBi(String maTrangThietBi){
        int result = db.delete(TABLE_NAME,"maTrangThietBi=?",new String[]{maTrangThietBi});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateTrangThietBi(String maTrangThietBi, String tenTrangThietBi, String giaTTT) {
        ContentValues values = new ContentValues();
        values.put("tenTrangthietBi", tenTrangThietBi);
        values.put("giaTTT", giaTTT);

        int result = db.update(TABLE_NAME, values, "maTrangThietBi=?", new
                String[]{maTrangThietBi});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
