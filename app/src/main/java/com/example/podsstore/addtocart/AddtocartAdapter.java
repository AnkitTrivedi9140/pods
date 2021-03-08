package com.example.podsstore.addtocart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;

import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddtocartAdapter extends RecyclerView.Adapter<AddtocartAdapter.MyViewHolder> {
    private AddtocartAdapter.AdapterListener adapterListener;
    private List<CartResponse> productResponseList;
    private Context context;
    DataTransferInterface dtInterface;
String qty;
    private AddtocartAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(AddtocartAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(AddtocartAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvqty,prnumber;
        public ImageView productiv,deleteproductiv;
        public CardView cardView,less,more;
        RelativeLayout wishlist;
        ArrayList<String> arrayList;
int counter=0;
        public MyViewHolder(View view) {
            super(view);
            tvqty = (TextView) view.findViewById(R.id.tvqty);
            description = (TextView) view.findViewById(R.id.description);
            tvAssetType = (TextView) view.findViewById(R.id.tvAssetType);
            cardView = view.findViewById(R.id.cardview);
            productiv = view.findViewById(R.id.productiv);
            deleteproductiv = view.findViewById(R.id.deleteproductiv);
            less = view.findViewById(R.id.less);
            more = view.findViewById(R.id.more);
            prnumber = view.findViewById(R.id.prnumber);
            wishlist = view.findViewById(R.id.rlwishlist);
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
            arrayList=new ArrayList<>();
            arrayList.add("1");
            arrayList.add("22");
            arrayList.add("33");
            arrayList.add("4");
dtInterface.onSetValues(arrayList);
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

    public AddtocartAdapter(Context context, DataTransferInterface dtInterface) {
        this.context = context;
        productResponseList = new ArrayList<>();
        this.dtInterface = dtInterface;


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

    public interface DataTransferInterface {
        public void onSetValues(ArrayList<String> al);
    }
}