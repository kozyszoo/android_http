package com.example.koji.api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void callApi(View view) {
        try {
            TextView txt1 = (TextView) findViewById(R.id.txt1);
            new HttpGetTask(txt1).execute(new URL("http://www.programing-style.com/android/android-api/android-httpurlconnection-get-text/"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

