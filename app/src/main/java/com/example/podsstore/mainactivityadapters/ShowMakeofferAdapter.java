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
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.product.ProductListAdapter;
import com.example.podsstore.wishlist.WishListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowMakeofferAdapter extends RecyclerView.Adapter<ShowMakeofferAdapter.MyViewHolder> {

    private ShowMakeofferAdapter. AdapterListener adapterListener;
    private List<MakeOfferResponse> productResponseList;
    private Context context;

    private ShowMakeofferAdapter.InventoryAdapterListener openListener;


    public void setAdapterListener(ShowMakeofferAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public void setAdapterListeners(ShowMakeofferAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvdateddd,tvdatess,tvviewofferhistory, tvviewofferdetails,tvaddress,tvdate,tvremarks, tvproductname,tvtotalqty,tvqtyperunit,tvtotalamount,tvofferstatus;
        public ImageView ivproduct,deleteproductiv;
        public CardView cardView,less,more;
        RelativeLayout wishlist;
        int counter=0;
        public MyViewHolder(View view) {
            super(view);
            tvviewofferhistory = (TextView) view.findViewById(R.id.tvviewofferhistory);
            tvdateddd =view.findViewById(R.id.tvdateddd);
            tvdatess =view.findViewById(R.id.tvdatess);
            ivproduct =view.findViewById(R.id.ivproduct);
            tvremarks = (TextView) view.findViewById(R.id.tvremarks);
            tvviewofferdetails = (TextView) view.findViewById(R.id.tvviewofferdetails);
            tvproductname = (TextView) view.findViewById(R.id.tvproductname);
            tvtotalqty = (TextView) view.findViewById(R.id.tvtotalqty);

            tvtotalamount = view.findViewById(R.id.tvtotalamount);

            tvaddress = view.findViewById(R.id.tvaddress);

            tvviewofferdetails.setOnClickListener(v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                }
            });
            tvviewofferhistory.setOnClickListener(v -> {

                if (openListener != null) {
                    openListener.onAdapterItemClicked(productResponseList.get(getAdapterPosition()));
                }
            });
        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public ShowMakeofferAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();

    }

    public ShowMakeofferAdapter(List<MakeOfferResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public ShowMakeofferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.makeofferitems, parent, false);

        return new ShowMakeofferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowMakeofferAdapter.MyViewHolder holder, int position) {
        MakeOfferResponse cartResponse = productResponseList.get(position);
        if(cartResponse!=null){
            if(cartResponse.getFinalupdatedamount()==null){
                holder.tvproductname.setText(cartResponse.getFirstbidamount());
            }else{
                holder.tvproductname.setText(cartResponse.getFinalupdatedamount());
            }


//            holder.tvdate.setText(cartResponse.getRemarks());

               holder.tvdateddd.setText(cartResponse.getOffercreatedat());

            holder. tvdatess.setText(cartResponse.getOfferstatus());
         //   holder.tvofferstatus.setText(cartResponse.getOfferaddress());
            holder.tvremarks.setText(cartResponse.getActualamount());
            holder.tvtotalqty.setText(cartResponse.getQuantitydetails());
            if(cartResponse.getFinalupdatedamount()==null){}else{
                Double aa=Double.valueOf(cartResponse.getQuantitydetails())*Double.valueOf(cartResponse.getFinalupdatedamount());
                holder.tvtotalamount.setText(String.valueOf(aa));
            }

            Glide.with(context)
                    .load(cartResponse.getOfferimage())
                    .into(holder.ivproduct);

        }


        // Toast.makeText(context,movies.getImageUrl(),Toast.LENGTH_LONG).show();


    }

    public void addAll(List<MakeOfferResponse> list) {

        for (MakeOfferResponse d : list) {
            add(d);
        }
    }

    public void add(MakeOfferResponse data) {
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


        void onItemClick(MakeOfferResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(MakeOfferResponse data);
    }

}