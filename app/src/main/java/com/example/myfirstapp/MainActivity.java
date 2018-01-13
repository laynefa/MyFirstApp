package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_PHONEMESSAGE = "com.example.myfirstapp.PHONEMESSAGE";
    public int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state=1;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        //toast.show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Context context = getApplicationContext();
        CharSequence text = "You left :(";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        if (state == 1)
            toast.show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Context context = getApplicationContext();
        CharSequence text = "Welcome back!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        if (state == 1)
            toast.show();
        state = 1;
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View buttonView)
    {
        state = 0;
        Intent intent  = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.Message);
        EditText editTextPhoneNumber = (EditText) findViewById(R.id.Phone);
        String message = editText.getText().toString();
        String phoneNumberMessage = editTextPhoneNumber.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_PHONEMESSAGE, phoneNumberMessage);
        startActivity(intent);
    }
}
