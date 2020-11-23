package com.example.emodiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectEmoPath_Specific extends AppCompatActivity{
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pathspecific);

            String specific;
            Button bt_content1 = findViewById(R.id.content01);
            Button bt_content2 = findViewById(R.id.content02);
            Button bt_content3 = findViewById(R.id.content03);
            Button bt_content4 = findViewById(R.id.content04);
            Button bt_content5 = findViewById(R.id.content05);

            Intent intent_type = getIntent();
            specific = intent_type.getExtras().getString("SPECIFIC");
            String [] specific_array;
            specific_array = null; // 배열 초기화

            if(specific.equals("기쁨"))
                specific_array = getResources().getStringArray(R.array.delightful);
            else if(specific.equals("행복"))
                specific_array = getResources().getStringArray(R.array.happy);
            else if(specific.equals("시랑"))
                specific_array = getResources().getStringArray(R.array.lovely);
            else if(specific.equals("평화"))
                specific_array = getResources().getStringArray(R.array.peaceful);
            else if(specific.equals("기운"))
                specific_array = getResources().getStringArray(R.array.energetic);
            else if(specific.equals("두려움"))
                specific_array = getResources().getStringArray(R.array.fear);
            else if(specific.equals("걱정"))
                specific_array = getResources().getStringArray(R.array.worried);
            else if(specific.equals("슬픔"))
                specific_array = getResources().getStringArray(R.array.sad);
            else if(specific.equals("고독"))
                specific_array = getResources().getStringArray(R.array.lonely);
            else if(specific.equals("거슬림"))
                specific_array = getResources().getStringArray(R.array.unpleasant);

            bt_content1.setText(specific_array[0]);
            bt_content2.setText(specific_array[1]);
            bt_content3.setText(specific_array[2]);
            bt_content4.setText(specific_array[3]);
            bt_content5.setText(specific_array[4]);

            View.OnClickListener listener = new View.OnClickListener(){
                public void onClick(View v){
                    Button bt = (Button) v;
                    Intent intent_specific = new Intent(com.example.emodiary.SelectEmoPath_Specific.this, DescribeEmotion.class);
                    intent_specific.putExtra("SELECTED", bt.getText().toString());
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
