package com.example.podsstore.mainactivityadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.data.response.FaqResponse;
import com.example.podsstore.data.response.TopBrandsResponse;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {
    private FaqAdapter.AdapterListener adapterListener;
    private List<FaqResponse> productResponseList;
    private Context context;

    private FaqAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(FaqAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(FaqAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvquestion;
        CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            tvquestion =  view.findViewById(R.id.tvquestion);

            cardView =  view.findViewById(R.id.cardView);

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

    public FaqAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public FaqAdapter(List<FaqResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public FaqAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faqitems, parent, false);

        return new FaqAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FaqAdapter.MyViewHolder holder, int position) {
        FaqResponse movies = productResponseList.get(position);
holder.tvquestion.setText(movies.getQuestion());
        //  Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
//        for(int i=0;i<movies){}


    }

    public void addAll(List<FaqResponse> list) {

        for (FaqResponse d : list) {
            add(d);
        }
    }

    public void add(FaqResponse data) {
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


        void onItemClick(FaqResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}