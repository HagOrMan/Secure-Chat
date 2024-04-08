package com.example.frontend;

import android.util.Log;
import com.google.firebase.database.*;
import com.example.frontend.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ReadWriteSnippets {

    public DatabaseReference msgDatabase;
    public DatabaseReference mtgDatabase;
    private static final String MESSAGE_DB_ROOT = "messages";
    private static final String MEETING_DB_ROOT = "meetings";
    private static final String TAG = "firebasedb";

    public ReadWriteSnippets() {
        msgDatabase = FirebaseDatabase.getInstance().getReference(MESSAGE_DB_ROOT);
        mtgDatabase = FirebaseDatabase.getInstance().getReference(MEETING_DB_ROOT);
    }

    public ReadWriteSnippets(String ref) {
        msgDatabase = FirebaseDatabase.getInstance().getReference(ref);
    }

    public ReadWriteSnippets(String ref, String inst) {
        msgDatabase = FirebaseDatabase.getInstance(inst).getReference(ref);
    }

    public void writeNewMessage(String senderID, String targetID, String message_txt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        Message msg = new Message(formattedDateTime, message_txt);

        msgDatabase.child(senderID).child(targetID).push().setValue(msg);
    }

    public void writeNewMessageWithListeners(String senderID, String targetID, String message_txt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        Message msg = new Message(formattedDateTime, message_txt);

        msgDatabase.child(senderID).child(targetID).push().setValue(msg)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        Log.d(TAG, "Succeeded sending :-" + msg);
                })
                .addOnFailureListener(e -> Log.e(TAG, "Failed sending :-" + msg));
    }

    public void writeNewMeetingWithListeners(ArrayList<String> participants, ScheduleMeeting.MeetingInfo meetingInfo) {

        for (String participant: participants){
            mtgDatabase.child(participant).push().setValue(meetingInfo)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            Log.d(TAG, "Succeeded sending :-" + meetingInfo);
                    })
                    .addOnFailureListener(e -> Log.e(TAG, "Failed sending :-" + meetingInfo));
        }
    }

    public void addMessageEventListener(String senderID, String targetID) {
        ValueEventListener msgListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Message message = dataSnapshot.getValue(Message.class);
                System.out.println(dataSnapshot.getChildrenCount());
                // TODO: Do something with message
//                Log.i(TAG, "Message received :- " + message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        msgDatabase.child(senderID).child(targetID).addValueEventListener(msgListener);
    }

    public void addMessageEventListener(DatabaseReference mMessageReference, String senderID, String targetID) {
        ValueEventListener msgListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Message message = dataSnapshot.getValue(Message.class);
                System.out.println(dataSnapshot.getChildrenCount());
                // TODO: Do something with message
//                Log.i(TAG, "Message received :- " + message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mMessageReference.child(senderID).child(targetID).addValueEventListener(msgListener);
    }

    public void addMessageChildEventListener(String senderID, String targetID) {
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                Message message = dataSnapshot.getValue(Message.class);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }
        };
        msgDatabase.child(senderID).child(targetID).addChildEventListener(childEventListener);
    }

    public void addMessageChildEventListener(DatabaseReference mMessageReference, String senderID, String targetID) {
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                Message message = dataSnapshot.getValue(Message.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so displayed the changed comment.
//                Comment newComment = dataSnapshot.getValue(Comment.class);
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so remove it.
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
//
//                // A comment has changed position, use the key to determine if we are
//                // displaying this comment and if so move it.
//                Comment movedComment = dataSnapshot.getValue(Comment.class);
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//                Toast.makeText(mContext, "Failed to load comments.",
//                        Toast.LENGTH_SHORT).show();
            }
        };
        mMessageReference.child(senderID).child(targetID).addChildEventListener(childEventListener);
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteSnippets rws = new ReadWriteSnippets();

        rws.writeNewMessage("ahmed", "michael", "hi michael");

        CountDownLatch lock = new CountDownLatch(1);
        lock.await(2000, TimeUnit.MILLISECONDS);

    }

}