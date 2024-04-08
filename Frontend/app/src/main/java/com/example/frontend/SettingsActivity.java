package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        ImageButton returnToChatsS = findViewById(R.id.returnToChatsS);
        Button viewMeetings = findViewById(R.id.viewMeetingsButton);
        Button logOut = findViewById(R.id.logOutButton);

        viewMeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewMeetingsIntent = new Intent(SettingsActivity.this, ViewMeetingsActivity.class);
                String sender = getIntent().getStringExtra("SENDER");
                viewMeetingsIntent.putExtra("username", sender);
                startActivity(viewMeetingsIntent);
            }
        });

        returnToChatsS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatsIntent = new Intent(SettingsActivity.this, ChatList.class);
                String sender = getIntent().getStringExtra("SENDER");
                chatsIntent.putExtra("username", sender);
                startActivity(chatsIntent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOutIntent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(logOutIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}