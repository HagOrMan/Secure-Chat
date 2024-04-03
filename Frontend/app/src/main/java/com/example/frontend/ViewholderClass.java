package com.example.frontend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewholderClass extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView usernameView, lastOnlineView;
    public ViewholderClass(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.pfp);
        usernameView = itemView.findViewById(R.id.username);
        lastOnlineView = itemView.findViewById(R.id.lastonline);
    }
}
