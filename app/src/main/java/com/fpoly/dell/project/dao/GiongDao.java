package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.Giong;

import java.util.ArrayList;
import java.util.List;

public class GiongDao {

    private final SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "Giong";
    public static final String SQL_GIONG = "CREATE TABLE Giong (maGiong text primary key , tenGiong text, xuatXu text)";
    private static final String TAG = "GiongDAO";

    public GiongDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertGiong(Giong giong){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maGiong", giong.getMaGiong());
        contentValues.put("tenGiong", giong.getTenGiong());
        contentValues.put("xuatXu",giong.getXuatXu());
        if (checkPrimaryKey(giong.getMaGiong())){
            int result = db.update(TABLE_NAME,contentValues,"maGiong=?", new
                    String[]{giong.getMaGiong()});
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
        String[] columns = {"MaGiong"};
        //WHERE clause
        String selection = "MaGiong=?";
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
    public List<Giong> getAllGiong(){
        List<Giong> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Giong s = new Giong();
            s.setMaGiong(c.getString(0));
            s.setTenGiong(c.getString(1));
            s.setXuatXu(c.getString(2));
            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
    public int deleteGiong(String maGiong){
        int result = db.delete(TABLE_NAME,"maGiong=?",new String[]{maGiong});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateGiong(String maGiong, String tenGiong, String xuatXu) {
        ContentValues values = new ContentValues();
        values.put("maGiong", maGiong);
        values.put("tenGiong", tenGiong);
        values.put("xuatXu", xuatXu);

        int result = db.update(TABLE_NAME, values, "maGiong=?", new
                String[]{maGiong});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
