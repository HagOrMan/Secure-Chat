package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ViewMeetingsActivity extends AppCompatActivity implements RecyclerChatListInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_meetings);

        RecyclerView recyclerView = findViewById(R.id.recyclerView3);
        String username = getIntent().getStringExtra("username");
        List<chatItem> itemsMSG = new ArrayList<chatItem>();

        ImageButton returnToSettings = findViewById(R.id.returnToSettings);

        ChildEventListener childEventListenerIncoming = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                ScheduleMeeting.MeetingInfo meetingInfo = dataSnapshot.getValue(ScheduleMeeting.MeetingInfo.class);
                itemsMSG.add(new chatItem(R.drawable.defaultpfp,  "Meeting at " + meetingInfo.getTime() + " " + meetingInfo.getDate(), meetingInfo.getMeetingLink()));
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(itemsMSG.size() - 1);
                Log.d("firebasedb", "THIS IS A TEST");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        returnToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(ViewMeetingsActivity.this, SettingsActivity.class);
                String sender = getIntent().getStringExtra("username");
                settingsIntent.putExtra("SENDER", sender);
                startActivity(settingsIntent);
            }
        });

        ReadWriteSnippets rwsIncomingMTG = new ReadWriteSnippets();
        rwsIncomingMTG.mtgDatabase.child(username).addChildEventListener(childEventListenerIncoming);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterM(getApplicationContext(), itemsMSG, this));
        if (Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() >= 1) {
            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClick(int position, String sender, String receiver) {

    }
}