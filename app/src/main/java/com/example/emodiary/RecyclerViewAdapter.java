package com.example.emodiary;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<MyData> mData;

    public class ViewHolder extends RecyclerView.ViewHolder{
        /* 뷰 선언 */
        TextView record_emotion;
        TextView record_center;
        TextView record_date;
        CardView cardView;
        //int position;
        String time;
        ViewHolder(View itemView) { //뷰 홀더 객체에 들어갈 뷰들 == 리사리클러 뷰의 구성 요소들
            super(itemView);
            cardView = itemView.findViewById(R.id.card); //카드 뷰
            record_emotion = itemView.findViewById(R.id.tv_record_emotion); //카드뷰 구성요소
            record_center = itemView.findViewById(R.id.tv_record_center);
            record_date = itemView.findViewById(R.id.tv_record_date);
        }

    }
    RecyclerViewAdapter(ArrayList<MyData> list) // 생성자, Mydate(밑에 선언되어 있음) 구조의 배열리스트를 인자로 받아옴
    {
        mData = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) // 뷰홀더 객체 생성시 자동 실행되는 콜백 메소드
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //레이아웃 inflater 활용하여 뷰 show

        View view = inflater.inflate(R.layout.cardview, parent,false); //inflate
        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view); //뷰 홀더 객체 생성하여 리턴

       return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //카드 뷰가 생성 될 때마다 생성자에서 받아온 배열리스트를 참조하여 카드 뷰를 구성함
        holder.record_emotion.setText(mData.get(position).emotion); //데이터 참조하는 과정
        holder.record_date.setText(mData.get(position).date);
        holder.record_center.setText("\"" + mData.get(position).center + "\"");
        holder.time = mData.get(position).time;

        holder.cardView.setOnClickListener(new OnClickListener() { // 카드뷰 클릭 -> EmotionContent 로 이동

            @Override
            public void onClick(View v) { // 카드뷰 클릭 -> 인텐트에 정보 EmotionContent에 실어서 전달
                Intent intent = new Intent(v.getContext(), EmotionContent.class); //인텐트에 카드뷰에 있는 정보 담아서 전달
                intent.putExtra("POS",position);
                intent.putExtra("EMO",holder.record_emotion.getText());
                intent.putExtra("DATE",holder.record_date.getText());
                intent.putExtra("DES",holder.record_center.getText());
                intent.putExtra("TIME",holder.time); // 시간 정보 = KEY 로써 DELETE 시에 활용할 것임
                v.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
class MyData{ // 카드뷰에 옮길 수 있도록 정형화된 클래스 선언
    public String emotion;
    public String date;
    public String time;
    public String center;
    public MyData(String emotion, String date, String time, String description){ // 생성자 -> 할당
        this.emotion = emotion;
        this.date = date;
        this.time = time;
        this.center= description;
    }
}
