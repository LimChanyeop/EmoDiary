package com.example.emodiary;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DescribeEmotion extends AppCompatActivity {
    DiaryHelper helper;
    SQLiteDatabase db;

    //private static ArrayList<emotion_set> emotion_setArrayList; //RECORD 카드뷰 어댑터에 활용할 ARRAYLIST

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_describe);
        helper = new DiaryHelper(this);
        db = helper.getWritableDatabase();

        //DiaryManager diaryManager = DiaryManager.getInstance(this); // DB 객체 선언

        Intent intent_selected = getIntent();
        String [] emotion_Selected = new String[2]; // 문자열 배열 1.선택된 감정 2.감정에 대한 설명
        String [] extra_info = new String[2]; // 1.시간 정보 2.날짜 정보

        emotion_Selected[0] = intent_selected.getExtras().getString("SELECTED"); // 선택 된 감정 받음
        TextView selected_emotion = (TextView) findViewById(R.id.selected_emotion);
        selected_emotion.setText(emotion_Selected[0]); //TextView로 보여줌

        EditText et_describe = (EditText) findViewById(R.id.describe); // 한 문장 입력 EditText
        TextView tv_time_info = (TextView) findViewById(R.id.tv_time_info);
        TextView tv_date_info = (TextView) findViewById(R.id.tv_date_info);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat time_info = new SimpleDateFormat("HH:mm:ss");
        extra_info[0] = time_info.format(date); // 시간 정보 삽입
        tv_time_info.setText(extra_info[0]);
        SimpleDateFormat date_info = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일");
        extra_info[1] = date_info.format(date); // 날짜 정보 삽입
        tv_date_info.setText(extra_info[1]);

        Button bt_complete = (Button) findViewById(R.id.complete); //입력완료 버튼 -> Record로 이동
        bt_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_recorded = new Intent(DescribeEmotion.this, RecordList.class);
                //emotion_Selected[1] = et_Sentence.getText().toString(); // 사용자가 입력한 문장을 인텐트에 담을 배열에 삽입
                //intent_recorded.putExtra("RECORDED_EMOTION",emotion_Selected); // 레코드에 실어보냄 {감정, 설명}
                //emotion_setArrayList = new ArrayList<emotion_set>();

                emotion_Selected[0] = selected_emotion.getText().toString(); //감정 명 삽입
                emotion_Selected[1] = et_describe.getText().toString(); // 설명 삽입

                db.execSQL("INSERT INTO diarydata VALUES (null, '"+emotion_Selected[0]+"', '"+emotion_Selected[1]+"','"+extra_info[0]+"','"+extra_info[1]+"');");
                Toast.makeText(getApplicationContext(), "일기 등록 완료", Toast.LENGTH_LONG).show();
                et_describe.setText("");
                //ContentValues adddata = new ContentValues();
                //adddata.put("emotion",emotion_Selected[0].toString()); //배열 -> ContentValues
                //adddata.put("description",emotion_Selected[1].toString());
                //adddata.put("time",extra_info[0]);
                //adddata.put("date",extra_info[1]);
                //diaryManager.insert(adddata); // 데이터 열 삽입
                startActivity(intent_recorded);
            }
        });
    }
}
