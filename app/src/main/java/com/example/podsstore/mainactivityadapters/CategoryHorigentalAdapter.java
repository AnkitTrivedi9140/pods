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
import com.example.podsstore.category.CategoryAdapter;
import com.example.podsstore.data.response.BusinessCatResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryHorigentalAdapter extends RecyclerView.Adapter<CategoryHorigentalAdapter.MyViewHolder> {
    private CategoryHorigentalAdapter.AdapterListener adapterListener;
    private List<BusinessCatResponse> productResponseList;
    private Context context;

    private CategoryHorigentalAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(CategoryHorigentalAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(CategoryHorigentalAdapter.InventoryAdapterListener adapterListener) {
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

    public CategoryHorigentalAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public CategoryHorigentalAdapter(List<BusinessCatResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public CategoryHorigentalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontalproductitems, parent, false);

        return new CategoryHorigentalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryHorigentalAdapter.MyViewHolder holder, int position) {
        BusinessCatResponse movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProductname());
        holder.description.setText("$_"+movies.getStatus());

        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(movies.getProductimage().trim().toString())
                .into(holder.productiv);

    }

    public void addAll(List<BusinessCatResponse> list) {

        for (BusinessCatResponse d : list) {
            add(d);
        }
    }

    public void add(BusinessCatResponse data) {
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


        void onItemClick(BusinessCatResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}