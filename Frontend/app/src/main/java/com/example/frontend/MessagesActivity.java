package com.example.frontend;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_messages);

        List<chatItem> itemsMSG = new ArrayList<chatItem>();

        itemsMSG.add(new chatItem(R.drawable.defaultpfp, "Michael1 10:02PM", "Hi Michael2!"));
        itemsMSG.add(new chatItem(R.drawable.defaultpfp, "Michael2 10:03PM", "Hi Michael1! This is a sample long message to test if the spacing works correctly This is more words to make the message evenlonger for testing purposes"));
        itemsMSG.add(new chatItem(R.drawable.defaultpfp, "Michael2 10:03PM", "Hi Michael1! This is a sample long message to test if the spacing works correctly This is more words to make the message evenlonger for testing purposes Hi Michael1! This is a sample long message to test if the spacing works correctly This is more words to make the message evenlonger for testing purposes Hi Michael1! This is a sample long message to test if the spacing works correctly This is more words to make the message evenlonger for testing purposes"));


        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterM(getApplicationContext(), itemsMSG));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}