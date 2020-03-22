package com.example.friendchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationActivity extends AppCompatActivity {

    TextView a;
    Button btnshow;
    DatabaseReference reff;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        a=(TextView)findViewById(R.id.entry_update);
        btnshow=(Button)findViewById(R.id.show_btn);

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                reff= FirebaseDatabase.getInstance().getReference().child("RESULT").child("notify");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {

                        String entryUpdate=dataSnapshot.child("Entry update").getValue().toString();
                        a.setText(entryUpdate);

                    }

                    @Override
                    public void onCancelled ( @NonNull DatabaseError databaseError ) {

                    }
                });

            }
        });
    }
}
