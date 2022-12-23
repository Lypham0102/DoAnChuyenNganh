package com.fpoly.dell.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fpoly.dell.project.dao.database.DatabaseHelper;
import com.fpoly.dell.project.model.BayDan;
import com.fpoly.dell.project.model.ChiPhi;
import com.fpoly.dell.project.model.Giong;
import com.fpoly.dell.project.model.NCC;
import com.fpoly.dell.project.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteDao {

    private DatabaseHelper dbHelper;
    private final SQLiteDatabase db;
    public static final String TABLE_NAME = "Note";
    public static final String SQL_NOTE = "CREATE TABLE NOTE (maNOTE text primary key , tenNOTE text, noiDUNG text)";
    private static final String TAG = "NoteDAO";

    public NoteDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int add(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNOTE",note.getManote());
        contentValues.put("tenNOTE", note.getTennote());
        contentValues.put("noiDUNG",note.getNoidung());
        if (checkPrimaryKey(note.getManote())){
            int result = db.update(TABLE_NAME,contentValues,"maNOTE=?", new
                    String[]{note.getManote()});
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
        String[] columns = {"maNOTE"};
        //WHERE clause
        String selection = "maNOTE=?";
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
    public List<Note> getAllNote(){
        List<Note> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Note s = new Note();
            s.setManote(c.getString(0));
            s.setTennote(c.getString(1));
            s.setNoidung(c.getString(2));

            dsSach.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
    public int deleteNote(String maNOTE){
        int result = db.delete(TABLE_NAME,"maNOTE=?",new String[]{maNOTE});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateNote(String maNOTE, String tenNOTE, String noiDUNG) {
        ContentValues values = new ContentValues();
        values.put("maNOTE", maNOTE);
        values.put("tenNOTE", tenNOTE);
        values.put("noiDUNG", noiDUNG);

        int result = db.update(TABLE_NAME, values, "maNOTE=?", new
                String[]{maNOTE});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}