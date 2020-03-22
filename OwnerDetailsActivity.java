package com.example.friendchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerDetailsActivity extends AppCompatActivity {

    EditText name,phone,carnumber;
    Button insert;
    Button entrypage;
    FirebaseDatabase database;
    DatabaseReference ref;
    Users users;
    long maxid=0;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);

        name=(EditText)findViewById(R.id.owner_name);
        phone=(EditText)findViewById(R.id.contact_number);
        carnumber=(EditText)findViewById(R.id.car_number);
        insert=(Button)findViewById(R.id.done);
        entrypage=(Button)findViewById(R.id.entry_page);
        users=new Users();
        database=FirebaseDatabase.getInstance();
       ref=database.getReference().child("Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                users.setName(name.getText().toString().trim());
                users.setContactNumber(phone.getText().toString().trim());
                users.setCarNumber(carnumber.getText().toString().trim());

                ref.child(String.valueOf(maxid+1)).setValue(users);
                Toast.makeText(OwnerDetailsActivity.this,"Registration Completed",Toast.LENGTH_LONG).show();


            }
        });

        entrypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                Intent mainIntent1 = new Intent(OwnerDetailsActivity.this,NotificationActivity.class);
                startActivity(mainIntent1);

            }
        });

    }

/*
    private void getValues()
    {
        users.setName(name.getText().toString().trim());
        users.setContactNumber(phone.getText().toString().trim());
        users.setCarNumber(carnumber.getText().toString().trim());
    }

    public void btnInsert ( View view ) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                getValues();
               ref.child("User01").setValue(users);

                Toast.makeText(OwnerDetailsActivity.this,"Registration Completed",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });
    }

 */
}
