package com.example.podsstore.category;

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
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.SubCategoryResponce;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter  extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    private SubCategoryAdapter.AdapterListener adapterListener;
    private List<SubCategoryResponce> productResponseList;
    private Context context;

    private SubCategoryAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(SubCategoryAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(SubCategoryAdapter.InventoryAdapterListener adapterListener) {
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

    public SubCategoryAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public SubCategoryAdapter(List<SubCategoryResponce> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public SubCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategoryitems, parent, false);

        return new SubCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubCategoryAdapter.MyViewHolder holder, int position) {
        SubCategoryResponce movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProductname());
        holder.description.setVisibility(View.GONE);

        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(movies.getProductimage())
                .into(holder.productiv);

    }

    public void addAll(List<SubCategoryResponce> list) {

        for (SubCategoryResponce d : list) {
            add(d);
        }
    }

    public void add(SubCategoryResponce data) {
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


        void onItemClick(SubCategoryResponce data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}