package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class text_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message);
    }
    public void backActivity(View view)
    {
        startActivity(new Intent(text_message.this, MainActivity.class));
    }
    public void openInputMessage(View view)
    {
        startActivity(new Intent(text_message.this, input_message.class));
    }

}