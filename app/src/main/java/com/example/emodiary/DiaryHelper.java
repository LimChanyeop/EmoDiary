package com.example.emodiary;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryHelper extends SQLiteOpenHelper {
private static final String DATABASE_NAME = "diarydata.db";
private static final int DATABASE_VERSION = 2;

public DiaryHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

@Override
public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE diarydata ( _id INTEGER PRIMARY KEY"+" AUTOINCREMENT, emotion TEXT, description TEXT , time TEXT, date TEXT);");
        }

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS diarydata");
        onCreate(db);
        }
}

