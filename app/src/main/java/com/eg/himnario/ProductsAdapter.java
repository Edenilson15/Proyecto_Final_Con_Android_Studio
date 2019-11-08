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


    private Context mCtx;
    private List<Productos> productList;

    public ProductsAdapter(Context mCtx, List<Productos> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Productos product = productList.get(position);

        //loading the image

        String im = product.getImagen();
        //Toast.makeText(mCtx, ""+im, Toast.LENGTH_SHORT).show();


        if(im.isEmpty()) {
            holder.imageView.setImageResource(R.drawable.imgnoencontrada);
            holder.textViewCodigo1.setText(String.valueOf(product.getCodigo()));
            holder.textViewgenero1.setText(product.getGenero());
            holder.textViewActor1.setText(String.valueOf(product.getActor()));

        }else{
            Glide.with(mCtx)
                    .load(product.getImagen())
                    .into(holder.imageView);

            holder.textViewCodigo1.setText(String.valueOf(product.getCodigo()));
            holder.textViewgenero1.setText(product.getGenero());
            holder.textViewActor1.setText(String.valueOf(product.getActor()));
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCodigo1, textViewActor1, textViewgenero1;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewgenero1 = itemView.findViewById(R.id.textViewgenero1);
            textViewActor1= itemView.findViewById(R.id.textViewActor1);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


}
