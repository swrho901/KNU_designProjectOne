package com.example.teamcmw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class statisticActivity extends AppCompatActivity {


    TextView bestScore;
    TextView worstScore;
    TextView meanScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        SharedPreferences preferences = getSharedPreferences( "scoreInfo" , MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        Double btime= (double)preferences.getFloat("best", 987654321.0f);
        Double wtime= (double)preferences.getFloat("worst", 0.0f);
        Double mtime= (double)preferences.getFloat("mean", 0.0f);


        btime = (double) Math.round(btime * 100) / 100;
        wtime = (double) Math.round(wtime * 100) / 100;
        mtime = (double) Math.round(mtime * 100) / 100;

        bestScore  = findViewById(R.id.tv_best);

        if(wtime!=(double)0.0f){
            bestScore.setText("최고기록: " + btime + " 초 걸렸습니다");

            worstScore = findViewById(R.id.tv_worst);

            worstScore.setText("최저기록: " + wtime + " 초 걸렸습니다");

            meanScore = findViewById(R.id.tv_mean);

            meanScore.setText("평균기록: " + mtime + " 초 걸렸습니다");
        }else{
            bestScore.setText("최고기록: " + "아직 없습니다.");
            worstScore = findViewById(R.id.tv_worst);

            worstScore.setText("최저기록: " + "아직 없습니다.");

            meanScore = findViewById(R.id.tv_mean);

            meanScore.setText("평균기록: " + "아직 없습니다.");

        }


    }
}