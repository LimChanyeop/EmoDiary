package com.example.emodiary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class    RecordList extends AppCompatActivity { //감정 객체 리스트

    DiaryHelper helper;
    SQLiteDatabase db;

    protected void onCreate(Bundle RecordInstance){
        super.onCreate(RecordInstance);
        setContentView(R.layout.record_list);

        ArrayList<String> list = new ArrayList<>();
        //String [] list = new String[3];
        helper = new DiaryHelper(this);
        db = helper.getReadableDatabase();
        TextView tv = (TextView)findViewById(R.id.recordtitle);

        Cursor cursor;

        cursor = db.rawQuery("SELECT emotion, date, description FROM diarydata" ,null);
        while (cursor.moveToNext()){
            //String emotion = cursor.getString(0)+ cursor.getString(1) ;
            //tv.setText(emotion);
            list.add(cursor.getString(0));
            //list[1]=(cursor.getString(1));
            //list[2]=(cursor.getString(2));
        }

        RecyclerView recyclerView = findViewById(R.id.record_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
    }
    
}

