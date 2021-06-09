package com.example.podsstore.mainactivityadapters;

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
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.data.response.MakeofferhistoryResponse;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MakeOfferHistoryAdapter extends RecyclerView.Adapter<MakeOfferHistoryAdapter.MyViewHolder> {

    private MakeOfferHistoryAdapter. AdapterListener adapterListener;
    private List<MakeofferhistoryResponse> productResponseList;
    private Context context;

    private MakeOfferHistoryAdapter.InventoryAdapterListener openListener;
    private MakeOfferHistoryAdapter.EditAdapterListener editListener;

    public void setAdapterListener(MakeOfferHistoryAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(MakeOfferHistoryAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }
    public void setAdapterListenersedit(MakeOfferHistoryAdapter.EditAdapterListener adapterListener) {
        this.editListener = adapterListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvproductname,tvorderdate,tvorderid ,tvordertotal,tvorderqty,tvbuyerofferprice,tvbuyerofferremarks,tvsellerofferprice,tvsellerofferremarks;
        public ImageView ivaccept,ivedit,ivdeclined;
        public CardView cardView;
        CircleImageView productimage;
        RelativeLayout wishlist;
        int counter=0;
        public MyViewHolder(View view) {
            super(view);
            productimage = view.findViewById(R.id.productimage);
            tvproductname = (TextView) view.findViewById(R.id.tvproductname);
            tvorderdate = (TextView) view.findViewById(R.id.tvorderdate);
            tvorderid = (TextView) view.findViewById(R.id.tvorderid);
            tvordertotal = (TextView) view.findViewById(R.id.tvordertotal);

            tvorderqty = view.findViewById(R.id.tvorderqty);
            tvbuyerofferprice = view.findViewById(R.id.tvbuyerofferprice);
            tvbuyerofferremarks = view.findViewById(R.id.tvbuyerofferremarks);
            tvsellerofferprice = view.findViewById(R.id.tvsellerofferprice);
            tvsellerofferremarks = view.findViewById(R.id.tvsellerofferremarks);

            ivaccept = view.findViewById(R.id.ivaccept);
            ivedit = view.findViewById(R.id.ivedit);
            ivdeclined = view.findViewById(R.id.ivdeclined);

            ivaccept.setOnClickListener(v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
               }
           });
            ivdeclined.setOnClickListener(v -> {

                if ( openListener!= null) {
                    openListener.onAdapterItemClicked(productResponseList.get(getAdapterPosition()));
                }
            });
            ivedit.setOnClickListener(v -> {

                if ( editListener!= null) {
                    editListener.onAdapterItemClickededit(productResponseList.get(getAdapterPosition()));
                }
            });
        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public MakeOfferHistoryAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public MakeOfferHistoryAdapter(List<MakeofferhistoryResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public MakeOfferHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.makeofferhistoryitems, parent, false);

        return new MakeOfferHistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MakeOfferHistoryAdapter.MyViewHolder holder, int position) {
        MakeofferhistoryResponse cartResponse = productResponseList.get(position);
        if(cartResponse!=null){
            Glide.with(context)
                    .load(cartResponse.getImage().toString())
                    .into(holder.productimage);
            holder.tvproductname.setText(cartResponse.getProductname());
            holder.tvorderdate.setText(cartResponse.getDatetime().toString());

          holder.tvordertotal.setText(cartResponse.getActualprice().toString());


            holder.tvorderid.setText(cartResponse.getOfferid().toString());
            holder.tvorderqty.setText(cartResponse.getQuantity());
            holder.tvbuyerofferprice.setText(cartResponse.getBuyerbidbrprice().toString());
            holder.tvbuyerofferremarks.setText(cartResponse.getBuyerremark().toString());
//            holder.tvsellerofferprice.setText(cartResponse.getSellerprice().toString());
//            holder.tvsellerofferremarks.setText(cartResponse.getSellerremark().toString());

            if(cartResponse.getSellerprice()==null){
                holder.tvsellerofferprice.setText("Wait for Response ");
            }else{
                holder.tvsellerofferprice.setText(cartResponse.getSellerprice().toString());
            }

            if(cartResponse.getSellerremark()==null){
                holder.tvsellerofferremarks.setText("Wait for Response ");
            }else{
                holder.tvsellerofferremarks.setText(cartResponse.getSellerremark().toString());
            }
            if(cartResponse.getStatus()==null){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
            }else{
                holder.ivdeclined.setVisibility(View.VISIBLE);
                holder.ivedit.setVisibility(View.VISIBLE);
                holder.ivaccept.setVisibility(View.VISIBLE);
            }
        }


        // Toast.makeText(context,movies.getImageUrl(),Toast.LENGTH_LONG).show();


    }

    public void addAll(List<MakeofferhistoryResponse> list) {

        for (MakeofferhistoryResponse d : list) {
            add(d);
        }
    }

    public void add(MakeofferhistoryResponse data) {
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


        void onItemClick(MakeofferhistoryResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(MakeofferhistoryResponse data);
    }
    public interface EditAdapterListener {
        void onAdapterItemClickededit(MakeofferhistoryResponse data);
    }
}