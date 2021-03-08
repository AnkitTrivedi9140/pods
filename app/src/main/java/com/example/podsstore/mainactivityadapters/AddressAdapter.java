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
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.product.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter  extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private AddressAdapter.AdapterListener adapterListener;
    private List<AddressResponse> productResponseList;

    private Context context;

    private AddressAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(AddressAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(AddressAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvaddress1;
        public ImageView productiv;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.tvname);
            tvAssetType = (TextView) view.findViewById(R.id.tvaddress);
            tvaddress1 = (TextView) view.findViewById(R.id.tvaddress1);






        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public AddressAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public AddressAdapter(List<AddressResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addresslistitems, parent, false);

        return new AddressAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddressAdapter.MyViewHolder holder, int position) {
        AddressResponse addressResponse = productResponseList.get(position);
        holder.description.setText(addressResponse.getUseraddressline1());
        holder.tvAssetType.setText(addressResponse.getUseraddressline2());

        holder.tvaddress1.setText(addressResponse.getUseraddressline3()+","+addressResponse.getUsercountry()+"("+addressResponse.getUserzipcode()+")");
    }

    public void addAll(List<AddressResponse> list) {

        for (AddressResponse d : list) {
            add(d);
        }
    }

    public void add(AddressResponse data) {
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


        void onItemClick(AddressResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(int position);
    }

}