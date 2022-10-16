package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.database.DatabaseHelper;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class VatNuoiDao {
    private final SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "VatNuoi";
    public static final String SQL_VATNUOI = "CREATE TABLE VatNuoi (maVatNuoi text primary key, maChungLoai text, soLuong text," +
            "loaiThucAn text, sucKhoe text);";
    private static final String TAG = "VatNuoiDAO";

    public VatNuoiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertVatNuoi(VatNuoi vatNuoi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maVatNuoi",vatNuoi.getMavatnuoi());
        contentValues.put("maChungLoai", vatNuoi.getMachungloai());
        contentValues.put("soLuong",vatNuoi.getSoluong());
        contentValues.put("loaiThucAn",vatNuoi.getLoaithucan());
        contentValues.put("sucKhoe",vatNuoi.getSuckhoe());
        if (checkPrimaryKey(vatNuoi.getMavatnuoi())){
            int result = db.update(TABLE_NAME,contentValues,"maVatNuoi=?", new
                    String[]{vatNuoi.getMavatnuoi()});
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
    public List<VatNuoi> getAllVatNuoi(){
        List<VatNuoi> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            VatNuoi s = new VatNuoi();
            s.setMavatnuoi(c.getString(0));
            s.setMachungloai(c.getString(1));
            s.setSoluong(c.getString(2));
            s.setLoaithucan(c.getString(3));
            s.setSuckhoe(c.getString(4));

            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    private boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"maVatNuoi"};
        //WHERE clause
        String selection = "maVatNuoi=?";
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
    public int deleteVatNuoi(String maVatNuoi){
        int result = db.delete(TABLE_NAME,"maVatNuoi=?",new String[]{maVatNuoi});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateVatNuoi(String maVatNuoi, String soluong, String thucan, String suckhoe) {
        ContentValues values = new ContentValues();
        values.put("soLuong", soluong);
        values.put("loaiThucAn", thucan);
        values.put("sucKhoe", suckhoe);

        int result = db.update(TABLE_NAME, values, "maVatNuoi=?", new
                String[]{maVatNuoi});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
