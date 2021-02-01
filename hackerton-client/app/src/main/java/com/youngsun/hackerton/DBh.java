package com.youngsun.hackerton;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBh extends SQLiteOpenHelper {
    public DBh(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE user (id VARCHAR(50) NOT NULL, pw VARCHAR(50) NOT NULL,isfirst INT NOT NULL )";
        db.execSQL(SQL);
        String SQL2 = "INSERT INTO user VALUES ('dummy','dummy',1)";
        db.execSQL(SQL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
