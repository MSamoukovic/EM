package com.example.eventmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventmanagement.DateTime;
import com.example.eventmanagement.Models.EventModel;
import com.example.eventmanagement.R;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    private Context context;
    private List<EventModel> events;
    private DateTime dateTime;

    public EventsAdapter(Context context, List<EventModel> events) {
        this.context = context;
        this.events = events;
        dateTime = new DateTime();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtEventName.setText(events.get(position).getName());
        holder.txtStartDate.setText(dateTime.getDate(events.get(position).getStartDate()));
        holder.txtEndDate.setText(dateTime.getDate(events.get(position).getEndDate()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtEventName, txtStartDate, txtEndDate, txtTopic, txtOrganization;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            txtStartDate = itemView.findViewById(R.id.txtStartDate);
            txtEndDate = itemView.findViewById(R.id.txtEndDate);
        }
    }
}