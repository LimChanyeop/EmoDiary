package com.example.emodiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EmotionContent extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotioncontent);
        /* 뷰 할당 과정 */
        TextView tv_emo = findViewById(R.id.tv_cont_emo);
        TextView tv_des = findViewById(R.id.tv_cont_des);
        TextView tv_date = findViewById(R.id.tv_cont_date);
        Button bt_gomain = findViewById(R.id.bt_go_main);
        Button bt_delete = findViewById(R.id.bt_delete);

        String time_key;

        //position = getIntent().getIntExtra("POS",-1); // 인텐트로 카드뷰 인덱스 받아오기
        tv_emo.setText(getIntent().getExtras().getString("EMO")); // 인텐트 내에 실린 정보 -> 텍스트 뷰로 설정하는 과정
        tv_des.setText(getIntent().getExtras().getString("DES"));
        tv_date.setText(getIntent().getExtras().getString("DATE")); //제거시에 카드 뷰를 특정할 수 있는 KEY로 활용할 것
        time_key=getIntent().getExtras().getString("TIME"); //제거시에 카드 뷰를 특정할 수 있는 KEY로 활용할 것
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryHelper helper;
                SQLiteDatabase db;
                helper = new DiaryHelper(getApplicationContext());
                db = helper.getWritableDatabase();
                //db.delete("diarydata",  "_id" + "=" + position+1, null);
                db.execSQL("DELETE FROM diarydata WHERE time = '" + time_key + "' AND date = '"+ tv_date.getText()+"'");
                Toast.makeText(EmotionContent.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EmotionContent.this, MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        bt_gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmotionContent.this, MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}