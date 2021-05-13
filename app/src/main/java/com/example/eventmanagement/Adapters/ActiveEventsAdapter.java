package com.example.eventmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;

import java.util.List;

import retrofit2.http.POST;

public class ActiveEventsAdapter extends RecyclerView.Adapter<ActiveEventsAdapter.MyViewHolder> {
    private Context context;
    private List<EventModel> activeEvents;

    public ActiveEventsAdapter(Context context, List<EventModel> activeEvents) {
        this.context = context;
        this.activeEvents = activeEvents;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_event_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtEventName.setText(activeEvents.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return activeEvents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtEventName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEventName = itemView.findViewById(R.id.txtEventName);
        }
    }
}