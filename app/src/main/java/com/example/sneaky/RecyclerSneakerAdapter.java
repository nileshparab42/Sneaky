package com.example.sneaky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class RecyclerSneakerAdapter extends RecyclerView.Adapter<RecyclerSneakerAdapter.ViewHolder> {

    private final RecyclerSneakerInterface recyclerSneakerInterface;

    Context context;
    ArrayList<SneakerModel> arrSneaker;

    RecyclerSneakerAdapter(Context context,ArrayList<SneakerModel> arrSneaker, RecyclerSneakerInterface recyclerSneakerInterface){
        this.context=context;
        this.arrSneaker=arrSneaker;
        this.recyclerSneakerInterface=recyclerSneakerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_sneaker,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,recyclerSneakerInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(arrSneaker.get(position).img).into(holder.ivSneaker);
        holder.tvSname.setText(arrSneaker.get(position).name);
        holder.tvCompany.setText(arrSneaker.get(position).company);
    }

    @Override
    public int getItemCount() {
        return arrSneaker.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivSneaker;
        TextView tvSname,tvCompany;


        public ViewHolder(@NonNull View itemView, RecyclerSneakerInterface recyclerSneakerInterface) {
            super(itemView);

            tvSname = itemView.findViewById(R.id.snName);
            tvCompany = itemView.findViewById(R.id.snCompany);
            ivSneaker = itemView.findViewById(R.id.snImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerSneakerInterface!=null){
                        int pos = getAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerSneakerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
