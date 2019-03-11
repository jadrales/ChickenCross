package com.example.chickencross;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView start;
    private ImageView profile;
    private ImageView character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (ImageView) findViewById(R.id.start);
        profile = (ImageView) findViewById(R.id.profile);
        character = (ImageView) findViewById(R.id.character);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomizer();
            }
        });
    }
    public void openGame(){
        Intent start = new Intent(this, Game.class);
        startActivity(start);
    }
    public void openProfile(){
        Intent profile = new Intent(this, ProfileSelect.class);
        startActivity(profile);
    }
    public void openCustomizer(){
        Intent cc = new Intent(this, CharacterCustomizer.class);
        startActivity(cc);
    }
}
