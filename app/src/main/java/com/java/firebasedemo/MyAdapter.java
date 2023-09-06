package com.java.firebasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Stol> list;



    public MyAdapter(Context context, ArrayList<Stol> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent,false);


        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Stol stol = list.get(position);
        holder.ime_adimnaSobe.setText(stol.getIme_adimnaSobe());
        holder.ulaganje.setText(stol.getUlaganje());
        holder.broj_igraca.setText(stol.getBroj_igraca());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ime_adimnaSobe, ulaganje, broj_igraca;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ime_adimnaSobe = itemView.findViewById(R.id.textime_adimnaSobe);
            ulaganje = itemView.findViewById(R.id.textulaganje);
            broj_igraca = itemView.findViewById(R.id.textbroj_igraca);

        }
    }



}
