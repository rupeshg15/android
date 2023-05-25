package com.example.practical_8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "students", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS records (id INTEGER PRIMARY KEY AUTOINCREMENT, prn INTEGER NOT NULL, name TEXT NOT NULL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(int prn, String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("prn", prn);
        contentValues.put("name", name);
        return db.insert("records", null, contentValues);
    }

    public long delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("records", "id=?", new String[]{id+""});
    }

    public Cursor read() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM records";
        return db.rawQuery(query, null);
    }
}
