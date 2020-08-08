package com.example.abhishek.speakup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_main);
        Button b = (Button)findViewById(R.id.button);

    }
    public void click1(View view)
    {
        Toast.makeText(getApplicationContext(), "Data Recorded",Toast.LENGTH_SHORT).show();
    }
}
