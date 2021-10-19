package com.codewithdevesh.foodybitez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class staticRvAdapter extends RecyclerView.Adapter<staticRvAdapter.StaticViewHolder> {

    private ArrayList<staticRvModel> items;
    int row_idx;

    public staticRvAdapter(ArrayList<staticRvModel> items) {
       this.items=items;
    }

    @NonNull
    @Override
    public StaticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticViewHolder staticViewHolder = new StaticViewHolder(view);
        return staticViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticViewHolder holder, final int position) {
        staticRvModel staticRvModel = items.get(position);
        holder.iv.setImageResource(staticRvModel.getImage());
        holder.tv.setText(staticRvModel.getText());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    row_idx = holder.getAdapterPosition(); // checkpoint
                    notifyDataSetChanged();
            }
        });
        if(row_idx==position){
            holder.ll.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }
        else{
            holder.ll.setBackgroundResource(R.drawable.static_rv_bg);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        LinearLayout ll;


        public StaticViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.pizzatxt);
            iv = itemView.findViewById(R.id.pizzaimg);
            ll = itemView.findViewById(R.id.linearLayout);
        }
    }
}
