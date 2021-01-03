package com.example.currentlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUpActivity extends AppCompatActivity {
    private EditText signUpEmailEditText, signUpPasswordEditText;
    private EditText Fullname;
    private EditText dateOfbirth;
    private EditText MobileNumber;
    private EditText userName;
    private RadioGroup radiogroup1;
    private RadioButton radiobutton1;
    private RadioGroup radiogroup2;
    private RadioButton radiobutton2;
    private ProgressBar progressBar;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressBar = findViewById(R.id.progressBarid);
        signUpEmailEditText= findViewById(R.id.signUpEmailEditTextID);
        signUpPasswordEditText=findViewById(R.id.signUpPasswordEditTextID);
        TextView signInTextView = findViewById(R.id.SignInTextViewID2);

        Fullname=findViewById(R.id.fullNameID);
        dateOfbirth=findViewById(R.id.dateOfBirthID);
        MobileNumber=findViewById(R.id.mobileID);
        userName=findViewById(R.id.userNameID);

        radiogroup1=findViewById(R.id.genderradioid);
        radiogroup2=findViewById(R.id.usertyperadioid);

        Button signUpButton;
        signUpButton = findViewById(R.id.SignUpButtonIID);

    }
    public void signUp(View view)
    {
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("User");

        String fullname=Fullname.getText().toString().trim();
        String dateofbirth=dateOfbirth.getText().toString().trim();
        String mobileNumber=MobileNumber.getText().toString().trim();
        String username=userName.getText().toString().trim();
        String email2= signUpEmailEditText.getText().toString().trim();
        String password2= signUpPasswordEditText.getText().toString().trim();

        String gender;
        String usertype;

        int rdio1=radiogroup1.getCheckedRadioButtonId();
        radiobutton1=findViewById(rdio1);
        gender=radiobutton1.getText().toString().trim();

        int rdio2=radiogroup2.getCheckedRadioButtonId();
        radiobutton2=findViewById(rdio2);
        usertype=radiobutton2.getText().toString().trim();

        User user= new User(fullname,dateofbirth,mobileNumber,gender,usertype,username,email2,password2);
        reference.child(username).setValue(user);
        //Toast.makeText(getApplicationContext(),"User Information has been registered",Toast.LENGTH_LONG).show();

        if(email2.isEmpty())
        {
            signUpEmailEditText.setError("Enter an email Address");
            signUpEmailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email2).matches())
        {
            signUpEmailEditText.setError("Enter an email Address");
            signUpEmailEditText.requestFocus();
            return;

        }
        if(fullname.isEmpty())
        {
            signUpEmailEditText.setError("Field should not be empty");
            signUpEmailEditText.requestFocus();
            return;
        }
        if(dateofbirth.isEmpty())
        {
            dateOfbirth.setError("Field should not be empty");
            dateOfbirth.requestFocus();
            return;
        }
        if(mobileNumber.isEmpty())
        {
            MobileNumber.setError("Field should not be empty");
            MobileNumber.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            userName.setError("Field should not be empty");
            userName.requestFocus();
            return;
        }
        //checking validity of password
        if(password2.isEmpty())
        {
            signUpPasswordEditText.setError("Field should not be empty");
            signUpPasswordEditText.requestFocus();
            return;
        }
        if(password2.length() < 8)
        {
            signUpPasswordEditText.setError("Password must contain atleast 8 characters");
            signUpPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
    }
    public void openSignInActivity(View view){
        startActivity(new Intent(signUpActivity.this,MainActivity.class));
    }

}