package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    Button btnlogout,btnvote,btnparty,btnresult,Profile;
    TextView user,disp;
    FirebaseAuth mAuth;
    String enable;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        /*FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            String u = String.valueOf(dataSnapshot.child("Name").getValue());
                            enable=dataSnapshot.child("vote").getValue().toString();
                            //Toast.makeText(getBaseContext(),String.valueOf(enable.contains("1")),Toast.LENGTH_SHORT).show();
                            if(enable.contains("1")==true)
                            {
                                //Toast.makeText(getBaseContext(),"innnnn",Toast.LENGTH_SHORT).show();
                                btnvote.setEnabled(false);
                            }
                            user.setAllCaps(true);
                            user.setText(u);
                        }
                        else {
                            Toast.makeText(getBaseContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
        mAuth=FirebaseAuth.getInstance();
        btnvote=(Button) findViewById(R.id.btn_vote);
        btnvote.setOnClickListener(this);
        btnparty=(Button) findViewById(R.id.btn_party);
        btnparty.setOnClickListener(this);
        btnresult=(Button) findViewById(R.id.btn_result);
        btnresult.setOnClickListener(this);
        btnlogout=(Button) findViewById(R.id.btn_logout);
        btnlogout.setOnClickListener(this);
        user=(TextView) findViewById(R.id.username);
        Profile=(Button) findViewById(R.id.btn_profile);
        Profile.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_home_page);
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            String u = String.valueOf(dataSnapshot.child("Name").getValue());
                            enable=dataSnapshot.child("vote").getValue().toString();
                            //Toast.makeText(getBaseContext(),String.valueOf(enable.contains("1")),Toast.LENGTH_SHORT).show();
                            if(enable.contains("1")==true)
                            {
                                //Toast.makeText(getBaseContext(),"innnnnawse",Toast.LENGTH_SHORT).show();
                                btnvote.setEnabled(false);
                                btnvote.setTextColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral70));
                            }
                            user.setAllCaps(true);
                            user.setText(u);
                        }
                        else {
                            Toast.makeText(getBaseContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        FirebaseDatabase.getInstance().getReference("Result")
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot=task.getResult();
                        String result=dataSnapshot.getValue().toString();
                        if(result.contains("1"))
                        {
                            btnresult.setEnabled(true);
                            btnresult.setTextColor(getResources().getColor(R.color.white));
                        }
                        else if(result.contains("0"))
                        {
                            btnresult.setEnabled(false);
                            btnresult.setTextColor(getResources().getColor(com.google.android.material.R.color.material_dynamic_neutral70));
                        }
                    }
                });
        mAuth=FirebaseAuth.getInstance();
        btnvote=(Button) findViewById(R.id.btn_vote);
        btnvote.setOnClickListener(this);
        btnparty=(Button) findViewById(R.id.btn_party);
        btnparty.setOnClickListener(this);
        btnresult=(Button) findViewById(R.id.btn_result);
        btnresult.setOnClickListener(this);
        btnlogout=(Button) findViewById(R.id.btn_logout);
        btnlogout.setOnClickListener(this);
        user=(TextView) findViewById(R.id.username);
        Profile=(Button) findViewById(R.id.btn_profile);
        Profile.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HomePage.this, HomePage.class));
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnvote))
        {
            FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("vote").setValue(1);
            startActivity(new Intent(HomePage.this, Vote.class));
            //finish();
            Toast.makeText(getBaseContext(),"Vote",Toast.LENGTH_SHORT).show();
        }
        if(v.equals(btnparty))
        {
            startActivity(new Intent(HomePage.this, Party.class));
            //Toast.makeText(getBaseContext(),"Party",Toast.LENGTH_SHORT).show();
        }
        if(v.equals(btnresult))
        {
            startActivity(new Intent(HomePage.this, Result.class));
            Toast.makeText(getBaseContext(),"Result",Toast.LENGTH_SHORT).show();
        }
        if(v.equals(Profile))
        {
            startActivity(new Intent(HomePage.this,com.example.votingsystem.Profile.class));
        }
        if(v.equals(btnlogout))
        {
            mAuth.signOut();
            startActivity(new Intent(HomePage.this, MainActivity.class));
            Toast.makeText(getBaseContext(),"Logged out",Toast.LENGTH_SHORT).show();
        }
    }

}