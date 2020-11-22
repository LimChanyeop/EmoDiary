package com.example.emodiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectEmoPath extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectemopath);
        String type;
        String[] type_array;

        Button bt_content1 = findViewById(R.id.content1);
        Button bt_content2 = findViewById(R.id.content2);
        Button bt_content3 = findViewById(R.id.content3);
        Button bt_content4 = findViewById(R.id.content4);
        Button bt_content5 = findViewById(R.id.content5);

        Intent intent_type = getIntent();
        type = intent_type.getExtras().getString("TYPE");
        if (type.equals("pos"))
            type_array = getResources().getStringArray(R.array.positive);
        else
            type_array = getResources().getStringArray(R.array.negative);

        bt_content1.setText(type_array[0]);
        bt_content2.setText(type_array[1]);
        bt_content3.setText(type_array[2]);
        bt_content4.setText(type_array[3]);
        bt_content5.setText(type_array[4]);

        View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){
                Button bt = (Button) v;
                Intent intent_specific = new Intent(SelectEmoPath.this, SelectEmoPath_Specific.class);
                intent_specific.putExtra("SPECIFIC", bt.getText());
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
