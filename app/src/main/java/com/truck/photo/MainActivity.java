package com.truck.photo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPanel = findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if(vId == R.id.buttonPanel){
            startActivity(new Intent(MainActivity.this,ClearCacheActivity.class));
            finish();
        }
    }
}
