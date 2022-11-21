package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.VatNuoi;

import java.util.ArrayList;
import java.util.List;

public class NCCDao {
    private DatabaseHelper dbHelper;
    private final SQLiteDatabase db;
    public static final String TABLE_NAME = "NCC";
    public static final String SQL_NCC = "CREATE TABLE NCC (maNCC text primary key , tenNCC text, diaChi text, sdt text)";
    private static final String TAG = "NCCDAO";

    public NCCDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertNCC(NCC ncc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNCC",ncc.getMaNCC());
        contentValues.put("tenNCC", ncc.getTenNCC());
        contentValues.put("diaChi",ncc.getDiaChi());
        contentValues.put("sdt",ncc.getSdt());
        if (checkPrimaryKey(ncc.getMaNCC())){
            int result = db.update(TABLE_NAME,contentValues,"maNCC=?", new
                    String[]{ncc.getMaNCC()});
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
        String[] columns = {"maNCC"};
        //WHERE clause
        String selection = "maNCC=?";
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
    public List<NCC> getAllNCC(){
        List<NCC> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            NCC s = new NCC();
            s.setMaNCC(c.getString(0));
            s.setTenNCC(c.getString(1));
            s.setDiaChi(c.getString(2));
            s.setSdt(c.getString(3));

            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
    public int deleteNCC(String maNCC){
        int result = db.delete(TABLE_NAME,"maNCC=?",new String[]{maNCC});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateNCC(String maNCC, String tenNCC, String diaChi, String sdt) {
        ContentValues values = new ContentValues();
        values.put("maNCC", maNCC);
        values.put("tenNCC", tenNCC);
        values.put("diaChi", diaChi);
        values.put("sdt", sdt);

        int result = db.update(TABLE_NAME, values, "maNCC=?", new
                String[]{maNCC});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
