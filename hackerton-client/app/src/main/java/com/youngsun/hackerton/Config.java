package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.simple.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Config extends AppCompatActivity {
Button button;
EditText id;
EditText pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        button = findViewById(R.id.login);
        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            loginhttp(id.getText().toString(),pw.getText().toString());
                            DBhander dbh = new DBhander(getApplicationContext(),"db1");
                            dbh.Update(id.getText().toString(),pw.getText().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
    private void loginhttp(String id,String pw) throws IOException {
        URL url = new URL("http://172.31.98.172:8080/login");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-type","application/json");
        httpURLConnection.setRequestProperty("Accept","application/json");
        DataOutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());
        JSONObject obj = new JSONObject();
        obj.put("id",id);
        obj.put("pw",pw);
        os.write(obj.toString().getBytes("UTF-8"));
        os.flush();
        Log.i("loginconnR",String.valueOf(httpURLConnection.getResponseCode()));
        finish();
    }
}