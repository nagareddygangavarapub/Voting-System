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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    EditText txtName,txtPhone,txtEmail,txtAdhar,txtPassword;
    Button btnregister;
    String Adarregex="^\\d{12}$";
    String phoneregex="^\\d{10}$";
    String pwdregex="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
    FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        txtName=(EditText)findViewById(R.id.txt_pname);
        txtPhone=(EditText)findViewById(R.id.txt_phone);
        txtEmail=(EditText)findViewById(R.id.txt_mail);
        txtAdhar=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.s_pwd);
        btnregister=(Button) findViewById(R.id.btn_login);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RegisterUser();
        /*if(v.equals(btnregister))
        {
            if(adarvalidate(reguser) && pwdvalidate(regpwd)) {
                Toast.makeText(getBaseContext(), "Registered", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
            else if(!adarvalidate(reguser)) {
                Toast.makeText(getBaseContext(),"Invalid Aadhar Number",Toast.LENGTH_SHORT).show();
            }
            else if(!pwdvalidate(regpwd)) {
                Toast.makeText(getBaseContext(),"Invalid Password",Toast.LENGTH_LONG).show();
            }
        }*/
    }

    private void RegisterUser() {
        String Name=txtName.getText().toString().trim();
        String Phone=txtPhone.getText().toString().trim();
        String Email=txtEmail.getText().toString().trim();
        String Adhar=txtAdhar.getText().toString().trim();
        String Password=txtPassword.getText().toString().trim();
        if(Name.isEmpty())
        {
            txtName.setError("Name is Required");
            txtName.requestFocus();
            return;
        }
        if(Phone.isEmpty())
        {
            txtPhone.setError("Phone Number is Required");
            txtPhone.requestFocus();
            return;
        }
        if(!Phonevalid(Phone))
        {
            txtPhone.setError("Invalid Phone Number");
            txtPhone.requestFocus();
            return;
        }
        if(Email.isEmpty())
        {
            txtEmail.setError("Email is Required");
            txtEmail.requestFocus();
            return;
        }
        if(Adhar.isEmpty())
        {
            txtAdhar.setError("Aadhar Number is Required");
            txtAdhar.requestFocus();
            return;
        }
        if(!adarvalidate(Adhar))
        {
            txtAdhar.setError("Invalid Aadhar Number");
            txtAdhar.requestFocus();
            return;
        }
        if(Password.isEmpty())
        {
            txtPassword.setError("Password is Required");
            txtPassword.requestFocus();
            return;
        }
        else {
            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Users users=new Users(Name,Phone,Email,Adhar,0);
                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getBaseContext(),"Registered Successfully!",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Signup.this, MainActivity.class));
                                        }
                                        else {
                                            //startActivity(new Intent(Signup.this,Signup.class));
                                            Toast.makeText(getBaseContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                    else
                    {
                        //startActivity(new Intent(Signup.this,Signup.class));
                        Toast.makeText(getBaseContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
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

    private boolean adarvalidate(String Adar) {
        Pattern pattern=Pattern.compile(Adarregex);
        Matcher matcher=pattern.matcher(Adar);
        return matcher.matches();
    }
    private boolean Phonevalid(String Phone){
        Pattern pattern=Pattern.compile(phoneregex);
        Matcher matcher=pattern.matcher(Phone);
        return matcher.matches();
    }
}