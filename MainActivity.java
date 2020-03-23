package com.example.smsrecieved;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String address = extras.getString("MessageNumber");
            String message = extras.getString("Message");
            TextView addressField = (TextView) findViewById(R.id.address);
            TextView messageField = (TextView) findViewById(R.id.message);
            addressField.setText("Message From : " +address);
            messageField.setText("Messsage : "+message);
        }

    }

}


