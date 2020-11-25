package com.example.emodiary;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "diarydata"; //
        private static final int DATABASE_VERSION = 2; // DB 버전 = 2

        public DiaryHelper(Context context){
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        } //helper 생성자

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE diarydata (id INTEGER PRIMARY KEY"+" AUTOINCREMENT, emotion TEXT, description TEXT , time TEXT, date TEXT);");
        } //DB 생성 (ID, EMOTION, DESCRIPTION, TIME, DATE)

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS diarydata");
                onCreate(db);
        } // 업그레이드시 기존 db 제거 후 실시
}

