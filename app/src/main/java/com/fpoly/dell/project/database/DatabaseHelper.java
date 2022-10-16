package com.fpoly.dell.project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpoly.dell.project.dao.BayChuongDao;
import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.ChungLoaiDao;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.dao.VatNuoiDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbBookManager";
    private static final int VESION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }
    //truy van khong tra ket qua
//    public void QueryData (String sql){
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql);
//    }
//
//    //truy van tra ket qua
//    public Cursor Getdata(String sql){
//        SQLiteDatabase database = getReadableDatabase();
//        return database.rawQuery(sql, null);
//
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ChungLoaiDao.SQL_CHUNGLOAI);
        db.execSQL(VatNuoiDao.SQL_VATNUOI);
        db.execSQL(ChiPhiDao.SQL_CHIPHI);
        db.execSQL(ThucAnDao.SQL_THUCAN);
        db.execSQL(BayChuongDao.SQL_BAYCHUONG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+ChungLoaiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+VatNuoiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ChiPhiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ThucAnDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BayChuongDao.TABLE_NAME);

    }

}
