package com.example.chickencross;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProfileSelect extends AppCompatActivity {
    private Button profile1;
    private Button profile2;
    private Button profile3;
    private Button profile4;
    private static int buttonNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_select);
        profile1 = (Button) findViewById(R.id.button);
        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber = 1;
                    openCreator();
            }
        });
        profile2 = (Button) findViewById(R.id.button2);
        profile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber = 2;
                openCreator();
            }
        });

        profile3 = (Button) findViewById(R.id.button3);
        profile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber = 3;
                openCreator();
            }
        });

        profile4 = (Button) findViewById(R.id.button4);
        profile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNumber = 4;
                openCreator();
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(buttonNumber == 1){
            profile1.setText(name);
        } else if (buttonNumber == 2){
            profile2.setText(name);
        } else if (buttonNumber == 3){
            profile3.setText(name);
        } else if (buttonNumber == 4){
            profile4.setText(name);
        }

    }

    public void openCreator(){
        Intent profile = new Intent(this, ProfileCreator.class);
        startActivity(profile);
    }
}
