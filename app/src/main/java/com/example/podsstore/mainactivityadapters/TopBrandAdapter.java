package com.example.podsstore.mainactivityadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;

import com.example.podsstore.data.response.TopBrandsResponse;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopBrandAdapter extends RecyclerView.Adapter<TopBrandAdapter.MyViewHolder> {
    private TopBrandAdapter.AdapterListener adapterListener;
    private List<TopBrandsResponse> productResponseList;
    private Context context;

    private TopBrandAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(TopBrandAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(TopBrandAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
 RelativeLayout relativeLayout;
        CircularImageView ivtopbrand;
        public MyViewHolder(View view) {
            super(view);
            ivtopbrand =  view.findViewById(R.id.ivtopbrand);

            relativeLayout =  view.findViewById(R.id.rltopimage);

            relativeLayout.setOnClickListener(v -> {

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

    public TopBrandAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public TopBrandAdapter(List<TopBrandsResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public TopBrandAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topbrandsitems, parent, false);

        return new TopBrandAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopBrandAdapter.MyViewHolder holder, int position) {
        TopBrandsResponse movies = productResponseList.get(position);

        //  Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
//        for(int i=0;i<movies){}

        Glide.with(context)
                .load(movies.getImage())
                .into(holder.ivtopbrand);

    }

    public void addAll(List<TopBrandsResponse> list) {

        for (TopBrandsResponse d : list) {
            add(d);
        }
    }

    public void add(TopBrandsResponse data) {
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


        void onItemClick(TopBrandsResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}