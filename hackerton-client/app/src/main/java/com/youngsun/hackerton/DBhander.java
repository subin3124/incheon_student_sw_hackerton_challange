package com.youngsun.hackerton;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhander {
    SQLiteOpenHelper mhelp = null;
    SQLiteDatabase mdb = null;
    public DBhander(Context context, String name){
        mhelp = new DBh(context,name,null,1);

    }
    public static DBhander dbhopen(Context context, String name){
        return new DBhander(context, name);
    }
    public void Update(String id, String pw){
        mdb = mhelp.getWritableDatabase();
        mdb.execSQL("UPDATE user SET id='"+id+"', pw='"+pw+"',isfirst=0");
    }
    public Cursor select(){
        mdb = mhelp.getReadableDatabase();
        Cursor cursor = mdb.query("user",null,null,null,null,null,null);
        return cursor;
    }
    public boolean isfirst(Cursor c){
        int isfirst = 0;
        while(c.moveToNext()){
            isfirst = c.getInt(c.getColumnIndex("isfirst"));
        }
        if(isfirst == 1){
            return true;
        }else{
            return false;
        }
    }
    public String getid(){
        Cursor cursor = select();
        String id = null;
        while(cursor.moveToNext()){
            id = cursor.getString(cursor.getColumnIndex("id"));
        }
        return id;
    }
}
