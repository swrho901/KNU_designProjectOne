package com.example.teamcmw;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class staticActivity3 extends AppCompatActivity {
    TextView tv_meantime;
    TextView tv_meancorrect;
    TextView tv_bestcorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic2);


        SharedPreferences preferences = getSharedPreferences( "scoreInfo" , MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        double mtime=0.0;//mean time
        double bcorrect=0.0;//best correct
        double mcorrect=0.0;

        bcorrect= (double)preferences.getFloat("best_correct_easy", 0.0f);
        mtime= (double)preferences.getFloat("meantime_easy", 0.0f);
        mcorrect= (double)preferences.getFloat("meancorrect_easy", 0.0f);


        mcorrect = (double) Math.round(mcorrect * 100) / 100;
        mtime = (double) Math.round(mtime * 100) / 100;

        tv_meantime  = findViewById(R.id.tvmeanTime_easy);
        tv_meancorrect  = findViewById(R.id.tvmeanCorrectNum_easy);
        tv_bestcorrect  = findViewById(R.id.tvmaxCorrectNum);

        if(mtime!=(double)0.0f){
            tv_meantime.setText("easy mode 평균시간: " + mtime + " 초 걸렸습니다");
            tv_meancorrect.setText("easy mode 평균 맞춘 횟수: " + mcorrect + " 개 맞았습니다");
            tv_bestcorrect.setText("easy mode 최대 맞춘 횟수: " + (int)bcorrect + " 개 맞았습니다");
        }else{
            tv_meantime.setText("easy mode 평균시간: "  + "아직 없습니다.");
            tv_meancorrect.setText("easy mode 평균 맞춘 횟수: "  + "아직 없습니다.");
            tv_bestcorrect.setText("easy mode 최대 맞춘 횟수: " + "아직 없습니다.");

        }


    }
}
