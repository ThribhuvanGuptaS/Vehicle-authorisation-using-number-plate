package com.example.friendchat;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mRegBtn;
    private Button mLoginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginbtn = (Button)findViewById(R.id.start_login_btn);
        mRegBtn = (Button)findViewById(R.id.start_reg_btn);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reg_Intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(reg_Intent);
            }
        });

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login_intent);

            }
        });

    }
}
