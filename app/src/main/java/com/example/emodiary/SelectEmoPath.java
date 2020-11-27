package com.example.emodiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectEmoPath extends AppCompatActivity { // 감정을 골라가는 과정의 액티비티
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectemo_path);

        String type; // 긍정 or 부정
        String[] type_array;

        Button bt_content1 = findViewById(R.id.content1);
        Button bt_content2 = findViewById(R.id.content2);
        Button bt_content3 = findViewById(R.id.content3);
        Button bt_content4 = findViewById(R.id.content4);
        Button bt_content5 = findViewById(R.id.content5);

        Intent intent_type = getIntent();
        type = intent_type.getExtras().getString("TYPE");
        if (type.equals("pos")) // 긍정적 감정 선택 -> R.array의 positive 배열 받아옴
            type_array = getResources().getStringArray(R.array.positive);
        else // 부정적 감정 선택
            type_array = getResources().getStringArray(R.array.negative);
        /* 긍정 or 부정적 감정 대분류*/
        bt_content1.setText(type_array[0]);
        bt_content2.setText(type_array[1]);
        bt_content3.setText(type_array[2]);
        bt_content4.setText(type_array[3]);
        bt_content5.setText(type_array[4]);

        View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){
                Button bt = (Button) v;
                Intent intent_specific = new Intent(SelectEmoPath.this, SelectEmoPath_Specific.class);
                intent_specific.putExtra("SPECIFIC", bt.getText()); // 클릭한 버튼의 정보 인텐트로 실어서 다음 세분화 액티비티에 전달
                startActivity(intent_specific);
            }
        };
        bt_content1.setOnClickListener(listener);
        bt_content2.setOnClickListener(listener);
        bt_content3.setOnClickListener(listener);
        bt_content4.setOnClickListener(listener);
        bt_content5.setOnClickListener(listener);
    }
}
