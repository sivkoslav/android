package com.example.airservice;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GradAdapter extends RecyclerView.Adapter<GradAdapter.GradViewHolder> {

    private List<Grad> gradovi=new ArrayList<>();
    CardView card;

    @NonNull
    @Override
    public GradViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.grad_red,parent,false);


        return new GradViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GradViewHolder holder, int position) {
        Grad trenutni=gradovi.get(position);
        card=holder.itemView.findViewById(R.id.card);
        if(trenutni.getAqi()<50){

            card.setCardBackgroundColor(Color.parseColor("#90ee90"));
        }else if(trenutni.getAqi()>50 && trenutni.getAqi()<100){
            card.setCardBackgroundColor(Color.YELLOW);
        }else{
            card.setCardBackgroundColor(Color.parseColor("#cd5c5c"));
        }
        holder.tv1.setText(trenutni.getImeGrada());
        holder.tv2.setText(trenutni.getImeDrzave());
        holder.tv3.setText(trenutni.getAqi()+"");
    }

    @Override
    public int getItemCount() {
        return gradovi.size();
    }

    public void setLista(List<Grad> gradovi){
        this.gradovi=gradovi;
        notifyDataSetChanged();
    }

    public Grad getGradNa(int position){
        return gradovi.get(position);
    }

    class GradViewHolder extends RecyclerView.ViewHolder{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;

        public GradViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tvimeGrada);
            tv2=itemView.findViewById(R.id.tvimeDrzave);
            tv3=itemView.findViewById(R.id.tvaqi);
        }
    }
}
