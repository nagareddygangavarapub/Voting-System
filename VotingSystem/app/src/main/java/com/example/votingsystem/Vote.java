package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vote extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton bjpbutton,congbutton,jdsbutton;
    Button votesubmit;
    int bjpid,congid,jdsid;
    int bjpcount,congcount,jdscount;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        FirebaseDatabase.getInstance().getReference("BJP");
        FirebaseDatabase.getInstance().getReference("CONG");
        FirebaseDatabase.getInstance().getReference("JDS");
        radioGroup=(RadioGroup) findViewById(R.id.vote_select);
        bjpbutton=(RadioButton)findViewById(R.id.bjp);
        congbutton=(RadioButton)findViewById(R.id.cong);
        jdsbutton=(RadioButton)findViewById(R.id.jds);
        bjpid=bjpbutton.getId();
        congid=congbutton.getId();
        jdsid=jdsbutton.getId();
        votesubmit=(Button) findViewById(R.id.btn_votesubmit);
        votesubmit.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Vote.this, Vote.class));
    }

    @Override
    public void onClick(View v) {
        if(v.equals(votesubmit)) {
            vote();
        }
    }
    private void vote() {
        if(radioGroup.getCheckedRadioButtonId()==bjpid)
        {
            Toast.makeText(getBaseContext(),"Voted Successfully",Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("BJP")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            DataSnapshot dataSnapshot= task.getResult();
                            bjpcount= Integer.parseInt(dataSnapshot.getValue().toString());
                            bjpcount+=1;
                            FirebaseDatabase.getInstance().getReference("BJP").setValue(bjpcount);

                            startActivity(new Intent(Vote.this, HomePage.class));
                        }
                    });
        }
        else if(radioGroup.getCheckedRadioButtonId()==congid)
        {
            Toast.makeText(getBaseContext(),"Voted Successfully",Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("CONG")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            DataSnapshot dataSnapshot= task.getResult();
                            congcount= Integer.parseInt(dataSnapshot.getValue().toString());
                            congcount+=1;
                            FirebaseDatabase.getInstance().getReference("CONG").setValue(congcount);
                            startActivity(new Intent(Vote.this, HomePage.class));
                        }
                    });
        }
        else if(radioGroup.getCheckedRadioButtonId()==jdsid)
        {
            Toast.makeText(getBaseContext(),"Voted Successfully",Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference("JDS")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            DataSnapshot dataSnapshot= task.getResult();
                            jdscount= Integer.parseInt(dataSnapshot.getValue().toString());
                            jdscount+=1;
                            //Toast.makeText(getBaseContext(),"Counted",Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("JDS").setValue(jdscount);
                            startActivity(new Intent(Vote.this, HomePage.class));
                        }
                    });
        }
    }

}