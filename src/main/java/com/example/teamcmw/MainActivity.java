package com.example.teamcmw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGame = findViewById(R.id.gameStart);
        Button btnStat = findViewById(R.id.statistics);
        Button btnGame2 = findViewById(R.id.startgameTwo);
        Button btnStat2 = findViewById(R.id.statisticTwo);
        Button btnGame3 = findViewById(R.id.startgameThree);
        Button btnStat3 = findViewById(R.id.statisticThree);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), statisticActivity.class);
                startActivity(intent);
            }
        });

        btnGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity3.class);
                startActivity(intent);
            }
        });

        btnStat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), staticActivity3.class);
                startActivity(intent);
            }
        });

        btnGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity2.class);
                startActivity(intent);
            }
        });

        btnStat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), staticActivity2.class);
                startActivity(intent);
            }
        });

    }


}