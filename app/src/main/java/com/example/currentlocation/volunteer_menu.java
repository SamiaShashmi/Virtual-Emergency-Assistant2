package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class volunteer_menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextInputLayout fullName, phoneNumber, email, dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_menu);
        drawLayout = (DrawerLayout) findViewById(R.id.volunteer_drawer);
        navigationView = findViewById(R.id.volunteer_navV);
        //toolbar = findViewById(R.id.hamburger);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawLayout, R.string.open, R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fullName = findViewById(R.id.fullNamep);
        phoneNumber = findViewById(R.id.phoneNop);
        email = findViewById(R.id.emailp);
        dob = findViewById(R.id.dobp);
        showVolunteerProfile();

    }
    public void showVolunteerProfile()
    {
        Intent intent = getIntent();
        String namestr = intent.getStringExtra("fullname");
        String phoneNostr = intent.getStringExtra("mobilenumber");
        String emailstr = intent.getStringExtra("email2");
        String dobstr = intent.getStringExtra("dateofbirth");

        fullName.getEditText().setText(namestr);
        phoneNumber.getEditText().setText(phoneNostr);
        email.getEditText().setText(emailstr);
        dob.getEditText().setText(dobstr);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.volunteer_dataView:
                Intent intent = new Intent(volunteer_menu.this, signUpActivity.class);
                startActivity(intent);
                break;
            /*case R.id.volunteer_profile:
                Intent intent1 = new Intent(volunteer_menu.this,volunteerProfile.class);


                String namestr = intent1.getStringExtra("fullname");
                String phoneNostr = intent1.getStringExtra("mobilenumber");
                String emailstr = intent1.getStringExtra("email2");
                String dobstr = intent1.getStringExtra("dateofbirth");

                fullName.getEditText().setText(namestr);
                phoneNumber.getEditText().setText(phoneNostr);
                email.getEditText().setText(emailstr);
                dob.getEditText().setText(dobstr);

                startActivity(intent1);
                break;*/
        }
        //drawLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void menuClick(View view)
    {
        openDrawer(drawLayout);
    }

    private static void openDrawer(DrawerLayout drawLayout) {
        drawLayout.openDrawer(GravityCompat.START);
    }
}