package com.example.teamcmw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView timeResult;
    double time = 0.0;
    double btime=987654321.0;//best time
    double wtime=0.0;//worst time
    double mtime=0.0;//mean time
    double trycnt=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        timeResult  = findViewById(R.id.text1);

        SharedPreferences preferences = getSharedPreferences( "scoreInfo" , MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        btime= (double)preferences.getFloat("best", 987654321.0f);
        wtime= (double)preferences.getFloat("worst", 0.0f);
        mtime= (double)preferences.getFloat("mean", 0.0f);
        trycnt= (double)preferences.getFloat("try", 0.0f);

        Intent it = getIntent();

        time = it.getDoubleExtra("time", 0.0);

        time = (double) Math.round(time * 100) / 100;

        if(time<btime){
            btime=time;
        }
        if(time>wtime){
            wtime=time;
        }
        editor.putFloat("best", (float)btime);
        editor.putFloat("worst", (float)wtime);
        mtime=(mtime*trycnt+time)/(trycnt+1.0);
        trycnt+=1.0;
        editor.putFloat("mean", (float)mtime);
        editor.putFloat("try", (float)trycnt);


        editor.commit();

        timeResult.setText(Double.toString(time) + " 초 걸렸습니다");

        Button moveButton=findViewById(R.id.button);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
