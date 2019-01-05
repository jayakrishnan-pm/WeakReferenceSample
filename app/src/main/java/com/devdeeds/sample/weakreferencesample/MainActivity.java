package com.devdeeds.sample.weakreferencesample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static final int SLEEP_TIME = 10000;  //10 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.text_content);
        new LongAsyncTask(this).execute();


        goToNextPageAndFinish();

    }



    private void goToNextPageAndFinish() {

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
        finish();
    }


    private void updateUI(String text) {

        if (!isFinishing() && !isDestroyed()) {
            textView.setText(text);
        }
    }


    private static class LongAsyncTask extends AsyncTask<String, String, String> {
        private WeakReference<MainActivity> mainActivity;

        LongAsyncTask(MainActivity mainActivity) {
            this.mainActivity = new WeakReference<>(mainActivity);
        }

        @Override
        protected String doInBackground(String[] params) {
            return doLongTask();
        }

        private String doLongTask() {

            try {
                Thread.sleep(MainActivity.SLEEP_TIME);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Thank you for waiting " + (MainActivity.SLEEP_TIME / 1000) + " seconds";

        }

        @Override
        protected void onPostExecute(String object) {

            if (mainActivity.get() != null) {
                mainActivity.get().updateUI(object);
            }
        }
    }


}
