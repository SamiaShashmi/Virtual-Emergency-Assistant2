package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.currentlocation.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.NavigableMap;

import static android.app.ProgressDialog.show;

public class emergencyButton extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FusedLocationProviderClient fusedLocationProviderClient;
    Toolbar toolbar;
    TextView phone_Call;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_button);
        drawLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navV);
        //toolbar = findViewById(R.id.hamburger);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawLayout, R.string.open, R.string.close);
        drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setCheckedItem(R.id.navV);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView navView = (NavigationView) findViewById(R.id.navV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/



        Button emergencyButton = (Button) findViewById(R.id.button);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(emergencyButton.this,
                        "Alert Sent", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(emergencyButton.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
                ActivityCompat.requestPermissions(emergencyButton.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
                getLocation();
            }
        });
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(emergencyButton.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        String[] phoneNumber = {"99999", "9999"};
                        for(int i = 0; i < phoneNumber.length; i++)
                        {
                            String dangerMessage = "**User is in DANGER**\n";
                            String link = "\nLink = https://www.google.com/maps/search/?api=1&query="
                                    +addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
                            String message = dangerMessage + "Address = " + addresses.get(0).getAddressLine(0) +
                                    "\nLocality = " + addresses.get(0).getLocality() +
                                    "\nCountry = " + addresses.get(0).getCountryName() +
                                    "\nLatitude = " + addresses.get(0).getLatitude() +
                                    "\nLongitude = " + addresses.get(0).getLongitude() + link;
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber[i], null, message, null, null);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }
    //@Override
   /* public boolean onOptionItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void onBackPressed(){
        if(drawLayout.isDrawerOpen(GravityCompat.START)){
            drawLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.phoneCall:
                Intent intent = new Intent(emergencyButton.this, phoneCallActivity.class);
                startActivity(intent);
                break;
            case R.id.txtMessage:
                Intent intent1 = new Intent(emergencyButton.this,text_message.class);
                startActivity(intent1);
                break;
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

    public void phoneCall(View view) {
        String phoneNumber = "8801881485160";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
   /* public void logout(View view){
        //FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }*/
   /* @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;
        action= event.getAction();
        keycode = event.getKeyCode();
        switch (keycode)
        {
            case KeyEvent.KEYCODE_VOLUME_UP:
            {
                if(KeyEvent.ACTION_UP == action)
                {
                    count++;

                    if(count == 3)
                    {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
                        getLocation();
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        String phoneNumber = "99999";
                        String dangerMessage = "**User is in DANGER**\n";
                        String link = "\nLink = https://www.google.com/maps/search/?api=1&query="
                                +addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
                        String message = dangerMessage + "Address = " + addresses.get(0).getAddressLine(0) +
                                "\nLocality = " + addresses.get(0).getLocality() +
                                "\nCountry = " + addresses.get(0).getCountryName() +
                                "\nLatitude = " + addresses.get(0).getLatitude() +
                                "\nLongitude = " + addresses.get(0).getLongitude() + link;
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    }

                }
            }

        }
        return super.dispatchKeyEvent(event);
    }*/
}