package com.example.podsstore.wishlist;

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
import com.example.podsstore.addtocart.AddtocartAdapter;
import com.example.podsstore.data.response.CartResponse;

import java.util.ArrayList;
import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {
    private WishListAdapter.AdapterListener adapterListener;
    private List<CartResponse> productResponseList;
    private Context context;

    private WishListAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(WishListAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(WishListAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvqty,prnumber,tvaddwishlist;
        public ImageView productiv,deleteproductiv;
        public CardView cardView,less,more;
        RelativeLayout wishlist;
        int counter=0;
        public MyViewHolder(View view) {
            super(view);
            tvqty = (TextView) view.findViewById(R.id.tvqty);
            tvaddwishlist = (TextView) view.findViewById(R.id.tvaddwishlist);
            description = (TextView) view.findViewById(R.id.description);
            tvAssetType = (TextView) view.findViewById(R.id.tvAssetType);
            cardView = view.findViewById(R.id.cardview);
            productiv = view.findViewById(R.id.productiv);
            deleteproductiv = view.findViewById(R.id.deleteproductiv);
            less = view.findViewById(R.id.less);
            more = view.findViewById(R.id.more);
            prnumber = view.findViewById(R.id.prnumber);
            wishlist = view.findViewById(R.id.rlwishlist);
            tvaddwishlist.setText("MOVE TO CART");
            wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openListener != null) {
                        openListener.onAdapterItemClicked(productResponseList.get(getAdapterPosition()));
                    }
                }
            });
            deleteproductiv.setOnClickListener(v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                }
            });

            less.setOnClickListener(v -> {
                counter=counter-1;
                prnumber.setText(String.valueOf(counter));
            });
            more.setOnClickListener(v -> {
                counter=counter+1;
                prnumber.setText(String.valueOf(counter));
            });
        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public WishListAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public WishListAdapter(List<CartResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartitems, parent, false);

        return new WishListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WishListAdapter.MyViewHolder holder, int position) {
        CartResponse cartResponse = productResponseList.get(position);
        holder.tvAssetType.setText(cartResponse.getProductname());
        holder.description.setText("$_"+cartResponse.getPrice());
        holder.tvqty.setText("Qty_"+cartResponse.getQty());

        // Toast.makeText(context,movies.getImageUrl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(cartResponse.getImageUrl().toString())
                .into(holder.productiv);

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
        void onAdapterItemClicked(CartResponse data);
    }

}