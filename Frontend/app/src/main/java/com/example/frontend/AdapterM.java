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
    private final RecyclerChatListInterface recyclerChatListInterface;

    Context context;
    List<chatItem> items;

    public AdapterM(Context context, List<chatItem> items, RecyclerChatListInterface recyclerChatListInterface) {
        this.context = context;
        this.items = items;
        this.recyclerChatListInterface = recyclerChatListInterface;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerChatListInterface != null) {
                    recyclerChatListInterface.onItemClick(position, "Michael3", items.get(position).getUsername());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
