package com.youngsun.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.simple.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fighting extends AppCompatActivity {
    Intent i;
    SpeechRecognizer mRecognizer;
    EditText editText;
    Button okay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighting);
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getApplicationContext().getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        Button button = findViewById(R.id.recong);
        editText = findViewById(R.id.fighting);
        okay = findViewById(R.id.okay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecognizer.startListening(i);
            }
        });
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL("http://172.31.98.172:8080/fighting");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection httpURLConnection = null;
                        try {
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setRequestProperty("Content-type","application/json");
                        httpURLConnection.setRequestProperty("Accept","application/json");
                        Intent intent = getIntent();
                        DataOutputStream os = null;
                        try {
                            os = new DataOutputStream(httpURLConnection.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONObject obj = new org.json.simple.JSONObject();
                        obj.put("msg",editText.getText().toString());
                        try {
                            os.write(obj.toString().getBytes("UTF-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            os.flush();
                            httpURLConnection.getResponseCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle results) {
           editText.setText(results.getString(SpeechRecognizer.RESULTS_RECOGNITION));
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }


    };

}