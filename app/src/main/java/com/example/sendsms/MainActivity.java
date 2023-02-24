package com.example.sendsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button sendBtn;
    EditText message, phoneNumber;
    String userMessage;
    String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = findViewById(R.id.sendSmsBtn);
        message = findViewById(R.id.editTextMessage);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);

        sendBtn.setOnClickListener(v -> {
            userMessage = message.getText().toString();
            userPhoneNumber = phoneNumber.getText().toString();
            sendSMS(userMessage, userPhoneNumber);

        });
    }

    public void sendSMS(String userMessage, String userPhoneUmber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(userPhoneUmber, null, userMessage, null, null);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(userPhoneNumber, null, userMessage, null, null);
        }else {

        }
    }
}