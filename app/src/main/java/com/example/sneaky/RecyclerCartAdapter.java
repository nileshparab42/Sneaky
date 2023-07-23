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

import java.util.ArrayList;

public class RecyclerCartAdapter extends RecyclerView.Adapter<RecyclerCartAdapter.ViewHolder> {

    Context context;
    ArrayList<CartModel> arrCart = new ArrayList<>();

    RecyclerCartAdapter(Context context,ArrayList<CartModel> arrCart){
        this.context=context;
        this.arrCart=arrCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.card_cart,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(arrCart.get(position).img).into(holder.img);
        holder.name.setText(arrCart.get(position).sname);
        holder.price.setText(arrCart.get(position).price);
        holder.date.setText(arrCart.get(position).date);
        holder.style.setText(arrCart.get(position).style);
        holder.email.setText(arrCart.get(position).email);
    }

    @Override
    public int getItemCount() {
        return arrCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView style,date,name,price,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            img = itemView.findViewById(R.id.img);
            style = itemView.findViewById(R.id.style);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            email = itemView.findViewById(R.id.email);
        }
    }
}
