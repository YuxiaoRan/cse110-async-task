package edu.ucsd.cse110.asynctaskapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private boolean isCancelled = false;
    private Button btnCount;
    private Button btnCancel;
    private TextView textResult;
    private boolean isCancelled = false;

    private class CountToTenAsyncTask extends AsyncTask<String, String, String> {

        private String resp;
        //ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            for(int i = 0; i < Integer.parseInt(params[0]) &&
                    isCancelled == false; i++) {
                try {
                    resp = "Slept for 1 second";
                    publishProgress(Integer.toString(i));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    resp = e.getMessage();
                }
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            if(!isCancelled) {
                textResult.setText("10");
            } else {
                textResult.setText("Task Cancelled");
            }
        }

        @Override
        protected void onProgressUpdate(String... text) {
            textResult.setText(text[0]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCount = findViewById(R.id.buttonCount);
        btnCancel = findViewById(R.id.buttonCancel);
        textResult = findViewById(R.id.textResult);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled = false;

                CountToTenAsyncTask runner = new CountToTenAsyncTask();
                runner.execute("10");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled = true;
            }
        });

    }
}
