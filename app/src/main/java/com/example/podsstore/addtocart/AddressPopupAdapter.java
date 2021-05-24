package com.example.podsstore.addtocart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podsstore.R;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.mainactivityadapters.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressPopupAdapter extends RecyclerView.Adapter<AddressPopupAdapter.MyViewHolder> {
    private AddressPopupAdapter.AdapterListener adapterListener;
    private List<AddressResponse> productResponseList;

    private Context context;

    private AddressPopupAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(AddressPopupAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(AddressPopupAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvaddress1;
        public ImageView deleteadd;
        public CardView cardView;
        public MyViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.tvname);
            tvAssetType = (TextView) view.findViewById(R.id.tvaddress);
            tvaddress1 = (TextView) view.findViewById(R.id.tvaddress1);
            cardView =  view.findViewById(R.id.cardview);


            deleteadd =  view.findViewById(R.id.deleteadd);
            cardView.setOnClickListener( v -> {

                        if (adapterListener != null) {
                            adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                        }
                    }
            );

        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public AddressPopupAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public AddressPopupAdapter(List<AddressResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public AddressPopupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addresspoplistitems, parent, false);

        return new AddressPopupAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AddressPopupAdapter.MyViewHolder holder, int position) {
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