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
import com.example.podsstore.data.response.SubCategoryProductResponce;
import com.example.podsstore.data.response.SubCategoryResponce;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryProductAdapter  extends RecyclerView.Adapter<SubCategoryProductAdapter.MyViewHolder> {
    private SubCategoryProductAdapter.AdapterListener adapterListener;
    private List<SubCategoryProductResponce> productResponseList;
    private Context context;

    private SubCategoryProductAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(SubCategoryProductAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(SubCategoryProductAdapter.InventoryAdapterListener adapterListener) {
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

    public SubCategoryProductAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public SubCategoryProductAdapter(List<SubCategoryProductResponce> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public SubCategoryProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategoryitems, parent, false);

        return new SubCategoryProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SubCategoryProductAdapter.MyViewHolder holder, int position) {
        SubCategoryProductResponce movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProdtype());
        holder.description.setVisibility(View.GONE);

        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(movies.getImageurl())
                .into(holder.productiv);

    }

    public void addAll(List<SubCategoryProductResponce> list) {

        for (SubCategoryProductResponce d : list) {
            add(d);
        }
    }

    public void add(SubCategoryProductResponce data) {
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


        void onItemClick(SubCategoryProductResponce data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}