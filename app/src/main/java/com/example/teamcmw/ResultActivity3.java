package com.example.teamcmw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class ResultActivity3 extends AppCompatActivity {

    public double time;
    public int correctN = 0;
    public int wrongN = 0;
    TextView tv;
    TextView tvCorrect;
    TextView tvWrong;

    int round = 0;

    // DBHelper
    DBHelper dbHelper = new DBHelper(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result3);

        SharedPreferences preferences = getSharedPreferences( "scoreInfo" , MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        double mtime=0.0;//mean time
        double bcorrect=0.0;
        double mcorrect=0.0;//
        double trycnt=0.0;//mean time

        bcorrect= (double)preferences.getFloat("best_correct_easy", 0.0f);
        mtime= (double)preferences.getFloat("meantime_easy", 0.0f);
        mcorrect= (double)preferences.getFloat("meancorrect_easy", 0.0f);
        trycnt= (double)preferences.getFloat("try_easy", 0.0f);


        tv = findViewById(R.id.tvTime2);
        tvCorrect = findViewById(R.id.tvCorrectNum2);
        tvWrong = findViewById(R.id.tvWrongNum2);

        Intent intent = getIntent();

        time = intent.getDoubleExtra("time", 0.0);
        correctN = intent.getIntExtra("correct", 0);
        wrongN = intent.getIntExtra("wrong", 0);

        tv.setText("시간 : " + Double.toString(time));
        tvCorrect.setText("맞힌 갯수 : " + Integer.toString(correctN));
        tvWrong.setText("틀린 갯수 : " + Integer.toString(wrongN));



        if(bcorrect<correctN)bcorrect=(double)correctN;

        editor.putFloat("best_correct_easy", (float)bcorrect);
        mtime=(mtime*trycnt+time)/(trycnt+1.0);
        mcorrect=(mcorrect*trycnt+correctN)/(trycnt+1.0);
        trycnt+=1.0;
        editor.putFloat("meantime_easy", (float)mtime);
        editor.putFloat("meancorrect_easy", (float)mcorrect);
        editor.putFloat("try_easy", (float)trycnt);


        editor.commit();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();

        round = dbHelper.getGame2Round();

        String result = "보통";
        String detailResult = "총 게임 시간이 1분 30초 미만이며 틀린 횟수가 10회 미만으로 주의력에 큰 문제가 없어 보입니다.";

        if (time >= 90) {
            if (wrongN <= 3) {
                result = "주의";
                detailResult = "총 게임 시간이 " + String.valueOf(time) + "초로 1분 30초 이상이며," +
                        " 틀린 횟수가 " + String.valueOf(wrongN) + "회로 3회 미만이므로" +
                        " 주의력 결핍이 의심됩니다.";
            }
            else {
                result = "매우 주의";
                detailResult = "총 게임 시간이 " + String.valueOf(time) + "초로 1분 30초 이상이며," +
                        " 틀린 횟수가 " + String.valueOf(wrongN) + "회로 3회 이상으로" +
                        " 주의력 결핍이 매우 의심됩니다.";
            }

        }
        else if (time >= 120) {
            result = "매우 주의";
            detailResult = "총 게임 시간이 " + String.valueOf(time) + "초로 2분 이상으로" +
                    " 주의력 결핍이 매우 의심됩니다.";
        }
        else if (wrongN >= 4) {
            result = "주의";
            detailResult = "총 게임 시간이 " + String.valueOf(time) + "초로 1분 30초 미만이나," +
                    " 틀린 횟수가 " + String.valueOf(wrongN) + "회로 4회 이상이므로" +
                    " 주의력 결핍이 의심됩니다.";
        }
        else if (wrongN >= 6) {
            result = "매우 주의";
            detailResult = "총 게임 시간이 " + String.valueOf(time) + "초로 1분 30초 미만이나," +
                    " 틀린 횟수가 " + String.valueOf(wrongN) + "회로 6회 이상이므로" +
                    " 주의력 결핍이 매우 의심됩니다.";
        }

        dbHelper.insertGame2(dateFormat.format(currentDate), round, time, wrongN, result, detailResult);




        Button moveButton=findViewById(R.id.button1232);
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
