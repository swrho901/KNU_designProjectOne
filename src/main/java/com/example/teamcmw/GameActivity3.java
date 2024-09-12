package com.example.teamcmw;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity3 extends AppCompatActivity {



    TextView tv;
    EditText et;
    public int iteration = 0; // 10
    public int ans = -1;
    public int num, maxNum, minNum, userNum;
    public int correct = 0, wrong = 0;
    public double p;
    public String str = null;
    public double time = 0.0;
    final SoundPool winSound1 = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    public int metaldrop;
    public int woodrattle;

    public double beforeTime = System.currentTimeMillis();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        metaldrop=winSound1.load(this,R.raw.metaldrop, 1);
        woodrattle=winSound1.load(this,R.raw.woodrattle, 1);

        tv = findViewById(R.id.gameText);
        et = findViewById(R.id.editTextId);

        // 6 - 8 까지 0.4, 0.4, 0.2 의 확률로 뽑힘
        num = -1; maxNum = -987654321; minNum = 987654321;


        playGame();
        userNum = -1;

        if(iteration == 2){

            tv.setText("asdfasdf");
        }


    }
    public void pickNumAndAns(){
        p = Math.random();
        if(p <= 0.4)
            num = 4;
        else if(p <= 0.8)
            num = 5;
        else
            num = 6;
        if(num == 4){
            maxNum = 9999;
            minNum = 1000;
        }
        else if(num == 5){
            maxNum = 99999;
            minNum = 10000;
        }
        else if(num == 6){
            maxNum = 999999;
            minNum = 100000;
        }
        ans = (int) (Math.random() * (maxNum-minNum+1) + minNum);
    }
    public void playGame(){
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        et.setText("");
        pickNumAndAns();
        String tvText = "";
        for(int i=0;i<num;i++){
            tvText = tvText + "_ ";
        }
        tv.setText(tvText);
        str = tvText;
        new Timer().schedule(new TimerTask() {
            public void run() {
               // tv.setText(String.valueOf(ans));
                String strstr = String.valueOf(ans);
                int len = strstr.length();
                String strShow = "";
                for(int i=0;i<len;i++){
                    strShow = strShow + strstr.charAt(i);
                    strShow = strShow + " ";
                }
                strShow = strShow.trim();
                tv.setText(strShow);
            }
        }, 1000); // 1초후 실행

        new Timer().schedule(new TimerTask() {
            public void run() {
                tv.setText(str);
            }
        }, 1500); // 1초후 실행


        et = findViewById(R.id.editTextId);

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        userNum = Integer.parseInt(et.getText().toString());
                        if (userNum == ans) {
                            winSound1.play(metaldrop, 1, 1, 0, 0, (float) 1.2);
                            tv.setText("correct");
                            tv.setTextColor(Color.parseColor("#4FEE74"));
                            iteration++;
                            correct++;
                            new Timer().schedule(new TimerTask() {
                                public void run() {

                                    if(iteration == 10){
                                        double afterTime = System.currentTimeMillis();
                                        time = (afterTime - beforeTime)/1000;
                                        Intent intent = new Intent(getApplicationContext(), ResultActivity3.class);
                                        intent.putExtra("correct", correct);
                                        intent.putExtra("wrong", wrong);
                                        intent.putExtra("time", time);
                                        startActivity(intent);
                                    }
                                    playGame();
                                }
                            }, 1500); // 1초후 실행
                        }
                        else{
                            winSound1.play(woodrattle, 1, 1, 0, 0, (float) 1.2);
                            tv.setText("wrong");
                            tv.setTextColor(Color.parseColor("#F03549"));
                            iteration++;
                            wrong++;
                            new Timer().schedule(new TimerTask() {
                                public void run() {

                                    if(iteration == 10){
                                        double afterTime = System.currentTimeMillis();
                                        time = (afterTime - beforeTime)/1000;
                                        Intent intent = new Intent(getApplicationContext(), ResultActivity3.class);
                                        intent.putExtra("correct", correct);
                                        intent.putExtra("wrong", wrong);
                                        intent.putExtra("time", time);
                                        startActivity(intent);
                                    }
                                    playGame();
                                }
                            }, 1500); // 1초후 실행


                        }
                        return true;
                    }
                return false;
            }
        });

    }


}