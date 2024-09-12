package com.example.teamcmw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity2 extends AppCompatActivity {

    public double time;
    public int correctN = 0;
    public int wrongN = 0;
    TextView tv;
    TextView tvCorrect;
    TextView tvWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        SharedPreferences preferences = getSharedPreferences( "scoreInfo" , MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        double mtime=0.0;//mean time
        double bcorrect=0.0;
        double mcorrect=0.0;//
        double trycnt=0.0;//mean time

        bcorrect= (double)preferences.getFloat("best_correct_hard", 0.0f);
        mtime= (double)preferences.getFloat("meantime_hard", 0.0f);
        mcorrect= (double)preferences.getFloat("meancorrect_hard", 0.0f);
        trycnt= (double)preferences.getFloat("try_hard", 0.0f);

        tv = findViewById(R.id.tvTime);
        tvCorrect = findViewById(R.id.tvCorrectNum);
        tvWrong = findViewById(R.id.tvWrongNum);

        Intent intent = getIntent();

        time = intent.getDoubleExtra("time", 0.0);
        correctN = intent.getIntExtra("correct", 0);
        wrongN = intent.getIntExtra("wrong", 0);

        tv.setText("시간 : " + Double.toString(time));
        tvCorrect.setText("맞힌 갯수 : " + Integer.toString(correctN));
        tvWrong.setText("틀린 갯수 : " + Integer.toString(wrongN));

        if(bcorrect<correctN)bcorrect=(double)correctN;

        editor.putFloat("best_correct_hard", (float)bcorrect);
        mtime=(mtime*trycnt+time)/(trycnt+1.0);
        mcorrect=(mcorrect*trycnt+correctN)/(trycnt+1.0);
        trycnt+=1.0;
        editor.putFloat("meantime_hard", (float)mtime);
        editor.putFloat("meancorrect_hard", (float)mcorrect);
        editor.putFloat("try_hard", (float)trycnt);


        editor.commit();



        Button moveButton=findViewById(R.id.button123);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        int k = 0;
    }
}
