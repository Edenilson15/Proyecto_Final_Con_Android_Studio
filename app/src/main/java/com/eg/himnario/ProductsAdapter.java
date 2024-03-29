package com.eg.himnario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

//import android.support.v7.widget.RecyclerView;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {



    private List<Productos> productList;

    public ProductsAdapter(Context mCtx, List<Productos> productList) {

        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(null);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Productos product = productList.get(position);

        //loading the image


        //Toast.makeText(mCtx, ""+im, Toast.LENGTH_SHORT).show();
        String Ac = product.getAutor();

        if(Ac.isEmpty()) {

            holder.textViewCodigo1.setText(String.valueOf(product.getCodigo()));
            holder.textViewletra1.setText(product.getLetra());
            holder.textViewnombre1.setText(product.getNombre());
            holder.textViewActor1.setText(String.valueOf(product.getAutor()));

        }else{




            holder.textViewCodigo1.setText(String.valueOf(product.getCodigo()));
            holder.textViewletra1.setText(product.getLetra());
            holder.textViewnombre1.setText(product.getNombre());
            holder.textViewActor1.setText(String.valueOf(product.getAutor()));
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCodigo1, textViewActor1, textViewletra1,textViewnombre1;


        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewletra1 = itemView.findViewById(R.id.textViewletra1);
            textViewActor1= itemView.findViewById(R.id.textViewActor1);
            textViewnombre1 = itemView.findViewById(R.id.textViewnombre1);

        }
    }


}
