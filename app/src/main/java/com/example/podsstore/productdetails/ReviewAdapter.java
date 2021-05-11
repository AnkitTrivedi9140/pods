package com.example.podsstore.productdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.data.response.OrderResponse;
import com.example.podsstore.data.response.ReviewResponse;
import com.example.podsstore.mainactivityadapters.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<ReviewResponse> productResponseList;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating,remarks;

        public CardView cardView,less,more;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvname);
            rating = (TextView) view.findViewById(R.id.tvrating);
            remarks = (TextView) view.findViewById(R.id.tvremarks);


        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public ReviewAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();



    }

    public ReviewAdapter(List<ReviewResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviewitemslayout, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, int position) {
        ReviewResponse cartResponse = productResponseList.get(position);
        holder.name.setText(cartResponse.getFullname());
        holder.rating.setText(cartResponse.getRating());
        holder.remarks.setText(cartResponse.getRemark());


    }

    public void addAll(List<ReviewResponse> list) {

        for (ReviewResponse d : list) {
            add(d);
        }
    }

    public void add(ReviewResponse data) {
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

}