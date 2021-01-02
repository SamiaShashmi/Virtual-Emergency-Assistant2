package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signInEmailEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        signInEmailEditText = findViewById(R.id.signInEmailEditTextID);
        signInPasswordEditText=findViewById(R.id.signInPasswordEditTextID);
        signUpTextView=findViewById(R.id.SignUpTextViewID);
        signInButton=findViewById(R.id.SignInButtonId);

        progressBar=findViewById(R.id.progressBarid);

        signUpTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.SignInButtonId:
                userLogin();
                break;

            case R.id.SignUpTextViewID:
                Intent intent = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
                break;


        }

    }

    private void userLogin() {

        String email= signInEmailEditText.getText().toString().trim();
        String password= signInPasswordEditText.getText().toString().trim();

        //checking validity of email
        if(email.isEmpty())
        {
            signInEmailEditText.setError("Enter an email Address");
            signInEmailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signInEmailEditText.setError("Enter an email Address");
            signInEmailEditText.requestFocus();
            return;

        }
        //checking validity of password
        if(password.isEmpty())
        {
            signInPasswordEditText.setError("Enter an email Address");
            signInPasswordEditText.requestFocus();
            return;
        }
        if(password.length() < 8)
        {
            signInPasswordEditText.setError("Password must contain atleast 8 characters");
            signInPasswordEditText.requestFocus();
            return;
        }
        /* progressBar.setVisibility(View.VISIBLE); */
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Intent intent = new Intent(getApplicationContext(),emergencyButton.class);
                    intent.putExtra("keyemail",email);
                    intent.putExtra("keypassword",password);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    public void phoneCall(View view) {
        String phoneNumber = "8801881485160";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    public void textMessage(View view) {
        String phoneNumber = "9999";
        EditText inmsg= findViewById(R.id.inputMessage);
        String message = inmsg.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public void openInputMessage(View view) {
        startActivity(new Intent(MainActivity.this, input_message.class));
    }
}