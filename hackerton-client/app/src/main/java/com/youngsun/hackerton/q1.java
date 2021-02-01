package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class q1 extends AppCompatActivity {
RadioGroup sleep;
RadioButton sleepButton;
Button sleepinsert;
int sleepscore1=10;
int sleepscore;
int wakeupscore=5;
int psleepscore=3;
int time=0;
int sleeptime=0;
EditText timee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
     sleep = findViewById(R.id.sleepRadio);
     timee = findViewById(R.id.time);
sleepinsert = findViewById(R.id.sleepinsert);
sleepinsert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sleepButton = findViewById(sleep.getCheckedRadioButtonId());
        switch (sleepButton.getText().toString()){
            case "오후 3시 ~ 5시": sleepscore1-=3; sleeptime=16; break;
            case "오전 12시 ~ 1시": sleepscore1-=2; sleeptime=1;break;
            case "오전 1시 ~ 2시": sleepscore1-=4;sleeptime=2;break;
            case "오전 2시 ~ 3시": sleepscore1-=6;sleeptime=3;break;
            case "오전 3시 ~ 4시": sleepscore1-=8;sleeptime=4;break;
            case "오전 4시 ~ 오후 3시":sleepscore1-=10;sleeptime=15;break;
            case "그외": sleeptime=7;break;
        }
        RadioButton sun = findViewById(R.id.sun);
        RadioButton night = findViewById(R.id.night);
       if(sun.isChecked()){
           time = Integer.parseInt(timee.getText().toString());
       }
       else if(night.isChecked()){
           time = 12+Integer.parseInt(timee.getText().toString());
       }
      if(sleeptime-time>=10){
          wakeupscore-=2;
      }else if(sleeptime-time<=5&&sleeptime-time>=4){
          wakeupscore-=3;
      }else if(sleeptime-time<4&&sleeptime-time>=3){
          wakeupscore-=4;
      }else if(sleeptime-time<3){
          wakeupscore-=5;
      }
      RadioButton psleepy = findViewById(R.id.psleepy);
      RadioButton psleepn = findViewById(R.id.psleepn);
      if(psleepy.isChecked()){
          psleepscore-=3;
      }
      sleepscore = sleepscore1+wakeupscore+psleepscore;
      Intent intent = new Intent(getApplicationContext(),q2.class);
      intent.putExtra("sleepscore",sleepscore);
      startActivity(intent);
    }
});
    }
}