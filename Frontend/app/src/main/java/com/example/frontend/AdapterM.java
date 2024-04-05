package com.example.frontend;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterM extends RecyclerView.Adapter<ViewholderClass> {

    Context context;
    List<chatItem> items;

    public AdapterM(Context context, List<chatItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewholderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewholderClass(LayoutInflater.from(context).inflate(R.layout.chat_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderClass holder, int position) {
        holder.imageView.setImageResource(items.get(position).getPfp());
        holder.usernameView.setText(items.get(position).getUsername());
        holder.lastOnlineView.setText(items.get(position).getLastOnline());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
