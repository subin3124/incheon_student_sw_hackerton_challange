package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button gotoq;
Button config;
Button ff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoq = findViewById(R.id.gotoq);
        config = findViewById(R.id.config);
        ff = findViewById(R.id.ff);
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),fighting.class));
            }
        });
        gotoq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),q1.class);
                startActivity(intent);
            }
        });
        DBhander dBhander = new DBhander(getApplicationContext(),"db1");
        Cursor cursor = dBhander.select();
        Boolean isfirst = dBhander.isfirst(cursor);
        if(isfirst){
            startActivity(new Intent(getApplicationContext(), Config.class));
        }
    }
}