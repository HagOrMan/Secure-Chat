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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatList extends AppCompatActivity implements RecyclerChatListInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_list);

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        Button scheduleMeetingButton = findViewById(R.id.ScheduleMeetingButton);
        String user = getIntent().getStringExtra("username");

        ReadWriteSnippets rwsChats = new ReadWriteSnippets();
        scheduleMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleMeetingIntent = new Intent(ChatList.this, ScheduleMeeting.class);
                String sender = getIntent().getStringExtra("username");
                scheduleMeetingIntent.putExtra("username", sender);
                startActivity(scheduleMeetingIntent);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(ChatList.this, SettingsActivity.class);
                String user = getIntent().getStringExtra("username");
                settingsIntent.putExtra("SENDER", user);
                startActivity(settingsIntent);
            }
        });

        List<chatItem> items = new ArrayList<chatItem>();
        items.add(new chatItem(R.drawable.defaultpfp, "Michael1", "Employee"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael2", "Employee"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael3", "Employee"));
        items.add(new chatItem(R.drawable.defaultpfp, "Michael4", "Employee"));


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