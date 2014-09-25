package com.pun.generator;

import android.app.Activity;
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.ResponseHandlerInterface
import groovy.json.JsonSlurper
import org.apache.http.Header;

public class MainActivity extends Activity
{
    private static Random random = new Random()
    private static final String API = "https://www.kimonolabs.com/api/6onwyw9k?apikey=ABzJzLoLJOSq1zVKAswwsxnUwUY7kDSv";
    private static final AsyncHttpClient client = new AsyncHttpClient();
    private TextView punText;
    private List puns = [];

    interface Callback<T> {
        void onSuccess(T payload);
        void onFailure(String reason);
    }

    private static class ResponseHandler extends AsyncHttpResponseHandler {

        Callback callback

        ResponseHandler(Callback callback) {
            super()
            this.callback = callback
        }

        @Override
        void onSuccess(int i, Header[] headers, byte[] bytes) {
            def response = new JsonSlurper().parse(bytes, "utf-8")
            callback.onSuccess(response.results.collection1)
        }

        @Override
        void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
            callback.onFailure(throwable.getMessage())
        }
    }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        punText = (TextView) findViewById(R.id.pun);
        this.getTitles(new Callback<List>() {

            @Override
            void onSuccess(List payload) {
                puns = payload;
                Toast.makeText(MainActivity.this, "Titles loaded", Toast.LENGTH_SHORT).show()
            }

            @Override
            void onFailure(String reason) {
                Toast.makeText(MainActivity.this, "Could not load title " + reason, Toast.LENGTH_SHORT).show()
            }
        })
    }

    public void newTitle(View v) {
        if(punText && puns.size() > 0) {
            def randomInt = random.nextInt(puns.size())
            punText.setText(puns[randomInt].pun.text)
        }
    }

    public void getTitles(Callback callback) {
        client.get(this, API, new ResponseHandler(callback))
    }
}
