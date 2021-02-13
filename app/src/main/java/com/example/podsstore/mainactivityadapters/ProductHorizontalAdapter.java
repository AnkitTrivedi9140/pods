package com.example.podsstore.mainactivityadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.product.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductHorizontalAdapter extends RecyclerView.Adapter<ProductHorizontalAdapter.MyViewHolder> {
    private ProductHorizontalAdapter.AdapterListener adapterListener;
    private List<ProductResponse> productResponseList;

    private Context context;

    private ProductHorizontalAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(ProductHorizontalAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(ProductHorizontalAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType;
        public ImageView productiv;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.description);
            tvAssetType = (TextView) view.findViewById(R.id.tvAssetType);
            cardView = view.findViewById(R.id.cardview);
            productiv = view.findViewById(R.id.productiv);



            cardView.setOnClickListener(v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                }
            });
        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public ProductHorizontalAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public ProductHorizontalAdapter(List<ProductResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public ProductHorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontalproductitems, parent, false);

        return new ProductHorizontalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductHorizontalAdapter.MyViewHolder holder, int position) {
        ProductResponse movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProdname());
        holder.description.setText("$_"+movies.getPrice());

        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
//        Glide.with(context)
//                .load("http://216.10.243.60:8080/PodContent/Medical-Googles.jpg")
//                .into(holder.productiv);
        Glide.with(context)
                .load(movies.getImageurl())
                .into(holder.productiv);
    }

    public void addAll(List<ProductResponse> list) {

        for (ProductResponse d : list) {
            add(d);
        }
    }

    public void add(ProductResponse data) {
        productResponseList.add(data);
        notifyItemInserted(productResponseList.size() - 1);
    }

    public int getSize() {
        return productResponseList.size();
    }

    public void clear() {

        if (!productResponseList.isEmpty()) {
            productResponseList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return productResponseList.size();
    }

    public interface AdapterListener {


        void onItemClick(ProductResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}