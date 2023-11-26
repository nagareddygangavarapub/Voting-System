package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class Result extends AppCompatActivity {
    TextView bjpvotecount,congvotecount,jdsvotecount,Leader;
    String bjp_count,cong_count,jds_count;
    //int bjp,cong,jds;String bjps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        bjpvotecount=(TextView) findViewById(R.id.bjp_vote);
        congvotecount=(TextView) findViewById((R.id.cong_vote));
        jdsvotecount=(TextView) findViewById(R.id.jds_vote);
        display();
    }

    private void display() {
        FirebaseDatabase.getInstance().getReference("BJP")
                        .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        bjp_count=dataSnapshot.getValue().toString();
                        bjpvotecount.setText(bjp_count);
                    }
                });
        FirebaseDatabase.getInstance().getReference("CONG")
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        cong_count=dataSnapshot.getValue().toString();
                        //Toast.makeText(getBaseContext(),bjp_count,Toast.LENGTH_SHORT).show();
                        congvotecount.setText(cong_count);
                    }
                });
        FirebaseDatabase.getInstance().getReference("JDS")
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot= task.getResult();
                        jds_count=dataSnapshot.getValue().toString();
                        jdsvotecount.setText(jds_count);
                        //Toast.makeText(getBaseContext(),bjp_count,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}