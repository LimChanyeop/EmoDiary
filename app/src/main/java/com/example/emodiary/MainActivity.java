package com.example.emodiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner emotionlist = (Spinner)findViewById(R.id.list); // 스피너 리스트
        Button bt_select = (Button) findViewById(R.id.select);
        TextView tv_selected = (TextView)findViewById(R.id.tv_selected); // 최종 선택 감정
        TextView tv_mid_selected = (TextView)findViewById(R.id.tv_mid_selected); // 선택 감정 분류
        Button bt_mid_select = (Button) findViewById(R.id.select_mid);
        ImageButton ib_negative = (ImageButton)findViewById(R.id.ib_negative);
        ImageButton ib_positive = (ImageButton)findViewById(R.id.ib_positive);
        Button bt_go_record = findViewById(R.id.bt_gorecord); // 기록보기 버튼

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.emotion_list,R.layout.support_simple_spinner_dropdown_item); //string 파일에서 list 받아옴
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        emotionlist.setAdapter(adapter); // 어댑터 할당
        emotionlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               tv_selected.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DescribeEmotion.class);
                intent.putExtra("SELECTED", tv_selected.getText().toString()); // intent 에 "SELECTED"로 선택한 감정 실어보냄
                startActivity(intent);
            }
        });
        ib_positive.setOnClickListener(new View.OnClickListener(){ // 버튼 클릭 -> 텍스트뷰 변경
            @Override
            public void onClick(View v) {
                tv_mid_selected.setText("긍정적 감정");
            }
        });
        ib_negative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tv_mid_selected.setText("부정적 감정");
            }
        });
        bt_mid_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectEmoPath.class);
                if (tv_mid_selected.getText().equals("긍정적 감정"))
                    intent.putExtra("TYPE", "pos");
                else
                    intent.putExtra("TYPE", "neg");
                startActivity(intent);
            }
        });
        bt_go_record.setOnClickListener(new View.OnClickListener() { // 기록 보기 버튼 리스너 할당
            @Override
            public void onClick(View v) {
                Intent intent_recorded = new Intent(MainActivity.this, RecordList.class);
                startActivity(intent_recorded);
            }
        });
    }
}