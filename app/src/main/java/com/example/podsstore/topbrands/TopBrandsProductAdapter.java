package com.example.podsstore.topbrands;

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
import com.example.podsstore.data.response.TopBrandsProductResponse;

import java.util.ArrayList;
import java.util.List;

public class TopBrandsProductAdapter extends RecyclerView.Adapter<TopBrandsProductAdapter.MyViewHolder> {
    private TopBrandsProductAdapter.AdapterListener adapterListener;
    private List<TopBrandsProductResponse> productResponseList;
    private Context context;

    private TopBrandsProductAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(TopBrandsProductAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(TopBrandsProductAdapter.InventoryAdapterListener adapterListener) {
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

    public TopBrandsProductAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }


    @Override
    public TopBrandsProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategoryitems, parent, false);

        return new TopBrandsProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopBrandsProductAdapter.MyViewHolder holder, int position) {
        TopBrandsProductResponse movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProdtype());
        holder.description.setVisibility(View.GONE);

        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(movies.getImageurl())
                .into(holder.productiv);

    }

    public void addAll(List<TopBrandsProductResponse> list) {

        for (TopBrandsProductResponse d : list) {
            add(d);
        }
    }

    public void add(TopBrandsProductResponse data) {
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


        void onItemClick(TopBrandsProductResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}