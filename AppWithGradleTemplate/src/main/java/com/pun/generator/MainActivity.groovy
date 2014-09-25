package com.pun.generator;

import android.app.Activity;
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.loopj.android.http.AsyncHttpClient;

public class MainActivity extends Activity
{
    private static final String API = "https://www.kimonolabs.com/api/6onwyw9k?apikey=ABzJzLoLJOSq1zVKAswwsxnUwUY7kDSv";
    private static final AsyncHttpClient client = new AsyncHttpClient();
    private TextView punText;
    private List puns = [];

    interface Callback<T> {
        void onSuccess(T payload);
        void onFailure(String reason);
    }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        punText = (TextView) findViewById(R.id.pun);

    }

    public void newPun(View v) {
        if(punText) {

        }
    }

    public static void getPuns(Callback callback) {

    }
}
