package com.example.chickencross;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileCreator extends AppCompatActivity {
    private Button submit;
    private TextInputEditText edit;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creator);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = (TextInputEditText) findViewById(R.id.editText);
                text = (TextView) findViewById(R.id.textView4);
                text.setText("Welcome " + edit.getText().toString()+"!");
                Intent intent = new Intent(ProfileCreator.this, ProfileSelect.class);
                intent.putExtra("name", edit.getText().toString());
                startActivity(intent);
            }
        });
    }
}
