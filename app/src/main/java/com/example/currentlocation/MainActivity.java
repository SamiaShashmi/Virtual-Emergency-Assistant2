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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {

    private EditText signInEmailEditText, signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;
    private ProgressBar progressBar;
    private EditText passwordEditText;
    private EditText userEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mAuth = FirebaseAuth.getInstance();
        signInEmailEditText = findViewById(R.id.signInUNameEditTextID);
        signInPasswordEditText=findViewById(R.id.signInPasswordEditTextID);
        signUpTextView=findViewById(R.id.SignUpTextViewID);
        signInButton=findViewById(R.id.SignInButtonId);

        progressBar=findViewById(R.id.progressBarid);
    }


    public void userLogin(View view) {

       /* passwordEditText = findViewById(R.id.signInPasswordEditTextID);
        userEditText = findViewById(R.id.signInUNameEditTextID);
        String userName = userEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(userName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    userEditText.setError(null);
                    String passFromDB = snapshot.child(userName).child("password2").getValue(String.class);
                    if(passFromDB.equals(password)){
                        passwordEditText.setError(null);
                        String nameFromDB = snapshot.child(userName).child("fullname").getValue(String.class);
                        String dobFromDB = snapshot.child(userName).child("dateofbirth").getValue(String.class);
                        String emailFromDB = snapshot.child(userName).child("email2").getValue(String.class);
                        String mobFromDB = snapshot.child(userName).child("mobilenumber").getValue(String.class);
                        String typeFromDB = snapshot.child(userName).child("usertype").getValue(String.class);

                           if(typeFromDB.equals("Volunteer"))
                            {
                                Intent intent = new Intent(getApplicationContext(),volunteer_menu.class);
                                intent.putExtra("fullname",nameFromDB);
                                intent.putExtra("dateofbirth",dobFromDB);
                                intent.putExtra("email2",emailFromDB);
                                intent.putExtra("mobilenumber",mobFromDB);
                                intent.putExtra("usertype",typeFromDB);

                                startActivity(intent);
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(),emergencyButton.class);
                                intent.putExtra("fullname",nameFromDB);
                                intent.putExtra("dateofbirth",dobFromDB);
                                intent.putExtra("email2",emailFromDB);
                                intent.putExtra("mobilenumber",mobFromDB);
                                intent.putExtra("usertype",typeFromDB);

                                startActivity(intent);
                            }
                   }
                    else {
                        passwordEditText.setError("Wrong Password");
                        passwordEditText.requestFocus();
                    }
                }
                else {
                    userEditText.setError("No Such User");
                    userEditText.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

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

    public void openSignInActivity(View view) {
        startActivity(new Intent(MainActivity.this, signUpActivity.class));
        Toast.makeText(MainActivity.this,
                "Alert Sent", Toast.LENGTH_SHORT).show();
    }

    public void signUp(View view) {
    }

}