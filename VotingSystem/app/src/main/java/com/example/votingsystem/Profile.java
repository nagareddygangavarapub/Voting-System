package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputEditText pname,pphone,pmail,padar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pname=(TextInputEditText)findViewById(R.id.txt_pname);
        pphone=(TextInputEditText) findViewById(R.id.txt_pphone);
        pmail=(TextInputEditText) findViewById(R.id.txt_pmail);
        padar=(TextInputEditText) findViewById(R.id.txt_padar);
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        String name=dataSnapshot.child("Name").getValue().toString();
                        pname.setText(name);
                    }
                });
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        String phone=dataSnapshot.child("Phone").getValue().toString();
                        pphone.setText(phone);
                    }
                });
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        String e_mail=dataSnapshot.child("Email").getValue().toString();
                        pmail.setText(e_mail);
                    }
                });
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        String aadar=dataSnapshot.child("Adhar").getValue().toString();
                        padar.setText(aadar);
                    }
                });
    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_profile);

    }*/
}