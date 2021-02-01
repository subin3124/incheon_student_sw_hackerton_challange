package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class q2 extends AppCompatActivity {
int howmaskscore = 20;
int outtimescore = 20;
int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText howmask = findViewById(R.id.howmask);
                EditText outtime = findViewById(R.id.outtime);
                int howmaskn = Integer.valueOf(howmask.getText().toString());
                int outtimen = Integer.valueOf(outtime.getText().toString());
                if(howmaskn>=1&&howmaskn<=2){
                    howmaskscore-=4;
                }else if(howmaskn>=3&&howmaskn<=4){
                    howmaskscore-=8;
                }else if(howmaskn>=5&&howmaskn<=6){
                    howmaskscore-=12;
                }else if(howmaskn>=7&&howmaskn<=8){
                    howmaskscore -= 16;
                }else if(howmaskn>=9){
                    howmaskn-=20;
                }
                if(outtimen>=4&&outtimen<=5){
                    outtimescore-=6;
                }else if(outtimen>5&&outtimen<=6){
                    outtimescore-=10;
                }else if(outtimen>6&&outtimen<=7){
                    outtimescore-=16;
                }else if(outtimen>7){
                    outtimescore-=20;
                }
                score = outtimescore+howmaskscore;
                Intent intenta = getIntent();

                Intent intent = new Intent(getApplicationContext(),fin.class);
                intent.putExtra("sleepscore",intenta.getIntExtra("sleepscore",1));
                intent.putExtra("covidscore",score);
                startActivity(intent);
            }
        });
    }
}