package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fin extends AppCompatActivity {
TextView sleepscore;
TextView sleepmsg;
TextView covidscore;
TextView covidmsg;
TextView f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);
        sleepscore = findViewById(R.id.sleepscore);
        sleepmsg = findViewById(R.id.sleepmsg);
        covidscore = findViewById(R.id.covidscore);
        covidmsg = findViewById(R.id.covidmsg);
        f= findViewById(R.id.fighting1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Reqeust();
                    f();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void Reqeust() throws IOException, ParseException {
        URL url = new URL("http://172.31.98.172:8080/GetResult");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Content-type","application/json");
        httpURLConnection.setRequestProperty("Accept","application/json");
        Intent intent = getIntent();
        DataOutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());
        JSONObject obj = new org.json.simple.JSONObject();
        obj.put("sleepscore",intent.getIntExtra("sleepscore",1));
        obj.put("covidscore",intent.getIntExtra("covidscore",1));
        DBhander dBhander = new DBhander(getApplicationContext(),"db1");
        obj.put("id",dBhander.getid());
        os.write(obj.toString().getBytes("UTF-8"));
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        JSONParser parser = new JSONParser();
        JSONObject obj2 = (JSONObject) parser.parse(br);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sleepscore.setText(obj2.get("sleepscore").toString()+"/25");
                sleepmsg.setText(obj2.get("sleepmsg").toString());
                covidmsg.setText(obj2.get("maskmsg").toString());
                covidscore.setText(obj2.get("mask" +
                        "score").toString()+"/40");
            }
        });
    }
    private void f() throws IOException, ParseException {
        URL url = new URL("http://172.31.98.172:8080/listf");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-type","application/json");
        httpURLConnection.setRequestProperty("Accept","application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        JSONParser parser = new JSONParser();
        JSONArray ary = (JSONArray) parser.parse(br);
        for(int i=0 ;i<ary.size();i++){
            JSONObject obj = (JSONObject) ary.get(i);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    f.append(obj.get("msg").toString()+"\n");
                }
            });
    }
    }

}