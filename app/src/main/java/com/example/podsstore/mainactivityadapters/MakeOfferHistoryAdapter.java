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
    private MakeOfferHistoryAdapter.PlaceorderAdapterListener placeListener;
    public void setAdapterListener(MakeOfferHistoryAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(MakeOfferHistoryAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }
    public void setAdapterListenersedit(MakeOfferHistoryAdapter.EditAdapterListener adapterListener) {
        this.editListener = adapterListener;
    }
    public void setAdapterListenerplaceorder(MakeOfferHistoryAdapter.PlaceorderAdapterListener adapterListener) {
        this.placeListener = adapterListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvorderqtytotal,tvplaceordermakeoffer,tvproductname,tvorderdate,tvorderid ,tvordertotal,tvorderqty,tvbuyerofferprice,tvbuyerofferremarks,tvsellerofferprice,tvsellerofferremarks;
        public ImageView ivaccept,ivedit,ivdeclined;
        public CardView cardView;
        CircleImageView productimage;
        RelativeLayout rlsellerremarks,rlsellerprice,rlbuyerprice,rlbuyerremaks;
        RelativeLayout wishlist;
        int counter=0;
        public MyViewHolder(View view) {
            super(view);
            tvorderqtytotal = view.findViewById(R.id.tvorderqtytotal);
            rlbuyerprice = view.findViewById(R.id.rlbuyerprice);
            rlbuyerremaks = view.findViewById(R.id.rlbuyerremarks);
            rlsellerremarks = view.findViewById(R.id.rlsellerremarks);
            rlsellerprice = view.findViewById(R.id.rlsellerprice);
            tvplaceordermakeoffer = view.findViewById(R.id.tvplaceordermakeoffer);
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
            tvplaceordermakeoffer.setOnClickListener(v -> {

                if ( placeListener!= null) {
                    placeListener.onAdapterItemClickplaceorder(productResponseList.get(getAdapterPosition()));
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


            holder.tvorderid.setText(cartResponse.getOfferid().toString()+"("+cartResponse.getStatus()+")");
            holder.tvorderqty.setText(cartResponse.getQuantity());

            holder.tvbuyerofferremarks.setText(cartResponse.getBuyerremark().toString());
//            holder.tvsellerofferprice.setText(cartResponse.getSellerprice().toString());
            holder.tvsellerofferremarks.setText(cartResponse.getBuyerremark().toString());




            if(cartResponse.getBuyerbidbrprice()==null) {

            }else{
                Double aa=Double.valueOf(cartResponse.getQuantity())*Double.valueOf(cartResponse.getBuyerbidbrprice());
                holder.tvorderqtytotal.setText(String.valueOf(aa));

            }
            if(cartResponse.getSellerprice()==null){

            }else{
                Double aa=Double.valueOf(cartResponse.getQuantity())*Double.valueOf(cartResponse.getSellerprice());
                holder.tvorderqtytotal.setText(String.valueOf(aa));

            }

            if(cartResponse.getUsertype().equalsIgnoreCase("Buyer")){
                holder.rlsellerprice.setVisibility(View.GONE);
                holder.rlsellerremarks.setVisibility(View.GONE);


            }else{
                holder.rlsellerprice.setVisibility(View.VISIBLE);
                holder.rlsellerremarks.setVisibility(View.VISIBLE);
            }

            if(cartResponse.getStatus()==null){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
                holder.tvplaceordermakeoffer.setVisibility(View.GONE);
            }else{
                if(cartResponse.getUsertype().equalsIgnoreCase("Seller") && cartResponse.getStatus().equalsIgnoreCase("New") ){
                    holder.rlbuyerprice.setVisibility(View.GONE);
                    holder.rlbuyerremaks.setVisibility(View.GONE);

                    holder.ivdeclined.setVisibility(View.VISIBLE);
                    holder.ivedit.setVisibility(View.VISIBLE);
                    holder.ivaccept.setVisibility(View.VISIBLE);
                    //holder.tvplaceordermakeoffer.setVisibility(View.GONE);
                }else{
                    holder.ivdeclined.setVisibility(View.GONE);
                    holder.ivedit.setVisibility(View.GONE);
                    holder.ivaccept.setVisibility(View.GONE);
                    holder.rlbuyerprice.setVisibility(View.VISIBLE);
                    holder.rlbuyerremaks.setVisibility(View.VISIBLE);
                }
            }

            if(cartResponse.getUsertype().equalsIgnoreCase("Seller")){
                holder.rlbuyerprice.setVisibility(View.GONE);
                holder.rlbuyerremaks.setVisibility(View.GONE);

            }else{

                holder.rlbuyerprice.setVisibility(View.VISIBLE);
                holder.rlbuyerremaks.setVisibility(View.VISIBLE);
            }

            if(cartResponse.getBuyerbidbrprice()==null){
                holder.tvbuyerofferprice.setText("");
            }else{
                holder.tvbuyerofferprice.setText(cartResponse.getBuyerbidbrprice().toString());

            }

            if(cartResponse.getSellerprice()==null){
                holder.tvsellerofferprice.setText("");
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
            }else{
                holder.tvsellerofferprice.setText(cartResponse.getSellerprice().toString());
                holder.ivdeclined.setVisibility(View.VISIBLE);
                holder.ivedit.setVisibility(View.VISIBLE);
                holder.ivaccept.setVisibility(View.VISIBLE);
            }

            if(cartResponse.getStatus()==null){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
                holder.tvplaceordermakeoffer.setVisibility(View.GONE);
            }
           else if(cartResponse.getStatus().equalsIgnoreCase("Processed")){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
                holder.tvplaceordermakeoffer.setVisibility(View.GONE);
            }
            else if(cartResponse.getStatus().equalsIgnoreCase("Declined")){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
                holder.tvplaceordermakeoffer.setVisibility(View.GONE);
            }
            else if(cartResponse.getStatus().equalsIgnoreCase("Accepted")){
                holder.ivdeclined.setVisibility(View.GONE);
                holder.ivedit.setVisibility(View.GONE);
                holder.ivaccept.setVisibility(View.GONE);
                holder.tvplaceordermakeoffer.setVisibility(View.VISIBLE);
            }else if(cartResponse.getStatus().equalsIgnoreCase("New")){
//                holder.ivdeclined.setVisibility(View.VISIBLE);
//                holder.ivedit.setVisibility(View.VISIBLE);
//                holder.ivaccept.setVisibility(View.VISIBLE);
//                holder.tvplaceordermakeoffer.setVisibility(View.GONE);
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

    public interface PlaceorderAdapterListener {
        void onAdapterItemClickplaceorder(MakeofferhistoryResponse data);

    }
}