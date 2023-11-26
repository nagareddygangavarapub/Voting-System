package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class register extends AppCompatActivity implements View.OnClickListener {
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnregister=(Button) findViewById(R.id.btn_register);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnregister))
        {
            Toast.makeText(getBaseContext(),"Registered",Toast.LENGTH_LONG).show();
            startActivity(new Intent(register.this, MainActivity.class));
        }
    }
}