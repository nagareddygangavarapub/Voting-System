package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtemail,txtpwd;
    Button btnLogin,btnregister;
    String Adarregex="^\\d{12}$";
    String pwdregex="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        txtemail=(EditText) findViewById(R.id.txt_email);
        txtpwd=(TextInputEditText) findViewById(R.id.L_pwd);
        btnLogin=(Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnregister=(Button) findViewById(R.id.btn_register);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnregister))
        {
                //Toast.makeText(getBaseContext(), "registered", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, Signup.class));
        }
        if(v.equals(btnLogin))
        {
            Loginuser();
        }
            /*if(adarvalidate(Adar) && pwdvalidate(password)) {
                Toast.makeText(getBaseContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, HomePage.class));
            }
            else if(!adarvalidate(Adar)) {
                Toast.makeText(getBaseContext(),"Invalid Aadhar Number",Toast.LENGTH_SHORT).show();
            }
            else if(!pwdvalidate(password)) {
                Toast.makeText(getBaseContext(),"Invalid Password",Toast.LENGTH_LONG).show();
            }
        }*/

    }

    private void Loginuser() {
        String email = txtemail.getText().toString().trim();
        String password = txtpwd.getText().toString().trim();
        if (email.isEmpty()) {
            txtemail.setError("Email Required");
            txtemail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            txtpwd.setError("Password Required");
            txtpwd.requestFocus();
            return;
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, HomePage.class));
                        //finish();
                    } else {
                        //startActivity(new Intent(MainActivity.this, MainActivity.class));
                            Toast.makeText(getBaseContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

    /*private boolean pwdvalidate(String password) {
        Pattern pattern=Pattern.compile(pwdregex);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }*/

    /*private boolean adarvalidate(String Adar) {
        Pattern pattern=Pattern.compile(Adarregex);
        Matcher matcher=pattern.matcher(Adar);
        return matcher.matches();
    }*/

}