package com.example.podsstore.addtocart;

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
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.mainactivityadapters.CategoryHorigentalAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddtocartAdapter extends RecyclerView.Adapter<AddtocartAdapter.MyViewHolder> {
    private AddtocartAdapter.AdapterListener adapterListener;
    private List<CartResponse> productResponseList;
    private Context context;

    private AddtocartAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(AddtocartAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(AddtocartAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvqty;
        public ImageView productiv;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            tvqty = (TextView) view.findViewById(R.id.tvqty);
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

    public AddtocartAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public AddtocartAdapter(List<CartResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public AddtocartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartitems, parent, false);

        return new AddtocartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddtocartAdapter.MyViewHolder holder, int position) {
        CartResponse movies = productResponseList.get(position);
        holder.tvAssetType.setText(movies.getProductname());
        holder.description.setText("$_"+movies.getPrice());
        holder.tvqty.setText("Qty_"+movies.getQty());
        //Toast.makeText(context,movies.getImageurl(),Toast.LENGTH_LONG).show();
//        Glide.with(context)
//                .load(movies.getProductimage().trim().toString())
//                .into(holder.productiv);

    }

    public void addAll(List<CartResponse> list) {

        for (CartResponse d : list) {
            add(d);
        }
    }

    public void add(CartResponse data) {
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


        void onItemClick(CartResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}