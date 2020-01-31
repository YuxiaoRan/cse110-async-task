package edu.ucsd.cse110.asynctaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoToCount = (Button) findViewById(R.id.buttonGoToCount);
        buttonGoToCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCount();
            }
        });
    }

    public void goToCount() {
        Intent intent = new Intent(this, CountToTenActivity.class);
        startActivity(intent);
    }
}
