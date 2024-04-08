package com.example.frontend;

import android.content.ClipData;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatList extends AppCompatActivity implements RecyclerChatListInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_list);

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        Button tempButton = findViewById(R.id.tempButton);
        Button scheduleMeetingButton = findViewById(R.id.ScheduleMeetingButton);


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(ChatList.this, SettingsActivity.class);
                String user = getIntent().getStringExtra("username");
                settingsIntent.putExtra("SENDER", user);
                startActivity(settingsIntent);
            }
        });

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messagesIntent = new Intent(ChatList.this, MessagesActivity.class);
                startActivity(messagesIntent);
            }
        });

        scheduleMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleMeetingIntent = new Intent(ChatList.this, ScheduleMeeting.class);
                startActivity(scheduleMeetingIntent);
            }
        });

        List<chatItem> items = new ArrayList<chatItem>();
        items.add(new chatItem(R.drawable.defaultpfp, "Michael1", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael2", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael3", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael4", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael5", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael6", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael7", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael8", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael9", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael10", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael11", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael12", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael13", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael14", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael15", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael16", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael17", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael18", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael19", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael20", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael21", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael22", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael23", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael24", "Currently Online"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael25", "Currently Online"));


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterM(getApplicationContext(), items, this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClick(int position, String sender, String receiver) {
        String user = getIntent().getStringExtra("username");
        Intent messagesIntent = new Intent(ChatList.this, MessagesActivity.class);
        messagesIntent.putExtra("SENDER", user);
        messagesIntent.putExtra("RECEIVER", receiver);
        startActivity(messagesIntent);
    }
}