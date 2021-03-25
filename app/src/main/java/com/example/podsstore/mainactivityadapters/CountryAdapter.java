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
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.CountryResponse;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private CountryAdapter.AdapterListener adapterListener;
    private List<CountryResponse> productResponseList;

    private Context context;

    private CountryAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(CountryAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(CountryAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countrytv, tvAssetType,tvaddress1;
        public ImageView productiv;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            countrytv = (TextView) view.findViewById(R.id.tvcountry);
            productiv =  view.findViewById(R.id.countryimage);







        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public CountryAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public CountryAdapter(List<CountryResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countrylistitems, parent, false);

        return new CountryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.MyViewHolder holder, int position) {
        CountryResponse addressResponse = productResponseList.get(position);
       holder.countrytv.setText(addressResponse.getCountryname());
        Glide.with(context)
                .load(addressResponse.getConuntryimage())
                .into(holder.productiv);
    }

    public void addAll(List<CountryResponse> list) {

        for (CountryResponse d : list) {
            add(d);
        }
    }

    public void add(CountryResponse data) {
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


        void onItemClick(CountryResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}