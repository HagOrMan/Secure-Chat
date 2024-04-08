package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

public class MessagesActivity extends AppCompatActivity implements RecyclerChatListInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_messages);

        List<chatItem> itemsMSG = new ArrayList<chatItem>();
        List<Message> outgoing = new ArrayList<Message>();
        List<Message> incoming = new ArrayList<Message>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);

        String sender = getIntent().getStringExtra("SENDER");
        String receiver = getIntent().getStringExtra("RECEIVER");
        ChildEventListener childEventListenerOutgoing = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Message message = dataSnapshot.getValue(Message.class);
                outgoing.add(message);
                itemsMSG.add(new chatItem(R.drawable.defaultpfp, sender + " " + message.getDateTime(), message.getMessage_txt()));
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(itemsMSG.size() - 1);
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

        ChildEventListener childEventListenerIncoming = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Message message = dataSnapshot.getValue(Message.class);
                incoming.add(message);
                itemsMSG.add(new chatItem(R.drawable.defaultpfp, receiver + " " + message.getDateTime(), message.getMessage_txt()));
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(itemsMSG.size() - 1);
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

        ReadWriteSnippets rwsOutgoing = new ReadWriteSnippets();
        ReadWriteSnippets rwsIncoming = new ReadWriteSnippets();

        rwsOutgoing.mDatabase.child(sender).child(receiver).addChildEventListener(childEventListenerOutgoing);
        rwsIncoming.mDatabase.child(receiver).child(sender).addChildEventListener(childEventListenerIncoming);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ImageButton backToChatList = findViewById(R.id.returnChatList);
        ImageButton sendMessageButton = findViewById(R.id.sendMessageButton);
        backToChatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToChatList = new Intent(MessagesActivity.this, ChatList.class);
                backToChatList.putExtra("username", sender);
                startActivity(backToChatList);
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageEditText = findViewById(R.id.textInputEditText);
                String message = messageEditText.getText().toString();
                rwsOutgoing.writeNewMessageWithListeners(sender, receiver, message);
                //itemsMSG.add(new chatItem(R.drawable.defaultpfp, sender + " " + LocalDateTime.now().format(formatter1), message));
                messageEditText.setText("");

                //recyclerView.getAdapter().notifyItemInserted(itemsMSG.size() - 1);
            }
        });


        /*outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 44).format(formatter1), "Test2"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 46).format(formatter1), "Test3"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 48).format(formatter1), "Test4"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 30).format(formatter1), "Test1"));

        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 45).format(formatter1), "Test2-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 47).format(formatter1), "Test3-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 49).format(formatter1), "Test4-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 31).format(formatter1), "Test1-2"));

        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 44).format(formatter1), "Test2"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 46).format(formatter1), "Test3"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 48).format(formatter1), "Test4"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 30).format(formatter1), "Test1"));

        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 45).format(formatter1), "Test2-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 47).format(formatter1), "Test3-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 49).format(formatter1), "Test4-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 31).format(formatter1), "Test1-2"));

        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 44).format(formatter1), "Test2"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 46).format(formatter1), "Test3"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 48).format(formatter1), "Test4"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 30).format(formatter1), "Test1"));

        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 45).format(formatter1), "Test2-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 47).format(formatter1), "Test3-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 49).format(formatter1), "Test4-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 31).format(formatter1), "Test1-2"));

        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 44).format(formatter1), "Test2"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 46).format(formatter1), "Test3"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 48).format(formatter1), "Test4"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 30).format(formatter1), "Test1"));

        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 45).format(formatter1), "Test2-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 47).format(formatter1), "Test3-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 49).format(formatter1), "Test4-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 31).format(formatter1), "Test1-2"));

        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 44).format(formatter1), "Test2"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 46).format(formatter1), "Test3"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 48).format(formatter1), "Test4"));
        outgoing.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 30).format(formatter1), "Test1"));

        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 45).format(formatter1), "Test2-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 47).format(formatter1), "Test3-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 49).format(formatter1), "Test4-2"));
        incoming.add(new Message(LocalDateTime.of(2024, 4, 7, 12, 30, 31).format(formatter1), "Test1-2"));*/

        //Collections.sort(outgoing, Comparator.comparing(Message::DateTimeDateObj));
        //Collections.sort(incoming, Comparator.comparing(Message::DateTimeDateObj));

        int indexInc = 0;
        int indexOut = 0;

        while (indexInc < incoming.size() || indexOut < outgoing.size()) {
            int res = 0;
            if (indexInc >= incoming.size()) {
                res = 1;
            } else if (indexOut >= outgoing.size()) {
                res = -1;
            } else {
                res = incoming.get(indexInc).getDateTime().compareTo(outgoing.get(indexOut).getDateTime());
            }
            if (res < 0) {
                itemsMSG.add(new chatItem(R.drawable.defaultpfp, receiver + " " + incoming.get(indexInc).getDateTime(), incoming.get(indexInc).getMessage_txt()));
                indexInc++;
            } else {
                itemsMSG.add(new chatItem(R.drawable.defaultpfp, sender + " " + outgoing.get(indexOut).getDateTime(), outgoing.get(indexOut).getMessage_txt()));
                indexOut++;
            }
        }

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