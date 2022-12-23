package com.fpoly.dell.project.dao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpoly.dell.project.dao.BayDanDao;
import com.fpoly.dell.project.dao.ChiPhiDao;
import com.fpoly.dell.project.dao.NCCDao;
import com.fpoly.dell.project.dao.NoteDao;
import com.fpoly.dell.project.dao.ThucAnDao;
import com.fpoly.dell.project.dao.VatNuoiDao;
import com.fpoly.dell.project.dao.GiongDao;
import com.fpoly.dell.project.dao.TrangThietBiDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbBookManager";
    private static final int VESION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoteDao.SQL_NOTE);
        db.execSQL(VatNuoiDao.SQL_VATNUOI);
        db.execSQL(ChiPhiDao.SQL_CHIPHI);
        db.execSQL(ThucAnDao.SQL_THUCAN);
        db.execSQL(BayDanDao.SQL_BAYDAN);
        db.execSQL(GiongDao.SQL_GIONG);
        db.execSQL(TrangThietBiDao.SQL_TRANGTHIETBI);
        db.execSQL(NCCDao.SQL_NCC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+ NoteDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+VatNuoiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ChiPhiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ThucAnDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BayDanDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ GiongDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ TrangThietBiDao.TABLE_NAME);
        db.execSQL("Drop table if exists "+ NCCDao.TABLE_NAME);

    }

}
