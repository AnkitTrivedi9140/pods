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
import com.example.podsstore.addtocart.AddtocartAdapter;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    private MyOrderAdapter.AdapterListener adapterListener;

    private MyOrderAdapter.AdapterListenerviewdetails adapterListenerviewdetails;
    private List<OrderResponse> productResponseList;
    private Context context;

    String qty;
    private MyOrderAdapter.InventoryAdapterListenerreview openListener;
    private MyOrderAdapter.InventoryAdapterListenerreturn openListenerreturn;


    public void setAdapterListener(MyOrderAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }
    public void setAdapterListenerviewdetails(MyOrderAdapter.AdapterListenerviewdetails adapterListenerviewdetails) {
        this.adapterListenerviewdetails = adapterListenerviewdetails;
    }



    public void setAdapterListenerreview(MyOrderAdapter.InventoryAdapterListenerreview adapterListener) {
        this.openListener = adapterListener;
    }
    public void setAdapterListenerreturn(MyOrderAdapter.InventoryAdapterListenerreturn adapterListener) {
        this.openListenerreturn = adapterListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvqty,tvcome,tvreview,tvreturn,tvorderdate,tvvieworderdetails;
        public ImageView productiv,deleteproductiv;
        public CardView cardView,less,more;
        RelativeLayout wishlist;
        ArrayList<String> arrayList;
        int counter=0;
        public MyViewHolder(View view) {
            super(view);
            tvqty = (TextView) view.findViewById(R.id.tvqty);
            tvvieworderdetails = (TextView) view.findViewById(R.id.tvvieworderdetails);
            tvorderdate = (TextView) view.findViewById(R.id.tvorderdate);
            tvreview = (TextView) view.findViewById(R.id.tvreview);
            tvreturn = (TextView) view.findViewById(R.id.tvreturn);
            description = (TextView) view.findViewById(R.id.description);
            tvAssetType = (TextView) view.findViewById(R.id.tvAssetType);
            cardView = view.findViewById(R.id.cardview);
            productiv = view.findViewById(R.id.productiv);

            tvcome = view.findViewById(R.id.tvcome);

            cardView.setOnClickListener(v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                }
            });
            tvreview.setOnClickListener(v -> {

                if (openListener != null) {
                    openListener.onAdapterItemClickedreview(productResponseList.get(getAdapterPosition()));
                }
            });
            tvreturn.setOnClickListener(v -> {

                if (openListenerreturn != null) {
                    openListenerreturn.onAdapterItemClickedreturn(productResponseList.get(getAdapterPosition()));
                }
            });
            tvvieworderdetails.setOnClickListener(v -> {

                if (adapterListenerviewdetails != null) {
                    adapterListenerviewdetails.onItemClickviewdetails(productResponseList.get(getAdapterPosition()));
                }
            });


        }
    }

    public void remove(int position) {
        productResponseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, productResponseList.size());
    }

    public MyOrderAdapter(Context context) {
        this.context = context;
        productResponseList = new ArrayList<>();



    }

    public MyOrderAdapter(List<OrderResponse> moviesList) {

        this.productResponseList = moviesList;
    }

    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorderitems, parent, false);

        return new MyOrderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyOrderAdapter.MyViewHolder holder, int position) {
        OrderResponse cartResponse = productResponseList.get(position);
        holder.tvAssetType.setText(cartResponse.getProducttype());
        holder.description.setText("$_"+cartResponse.getPrice());
        holder.tvqty.setText("Qty: "+cartResponse.getQty());
        holder.tvorderdate.setText(cartResponse.getItempresentin().toString());
        holder.tvcome.setText("Order Status- "+cartResponse.getCurrentorderstatus());
        // Toast.makeText(context,movies.getImageUrl(),Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(cartResponse.getProductimage())
                .into(holder.productiv);

    }

    public void addAll(List<OrderResponse> list) {

        for (OrderResponse d : list) {
            add(d);
        }
    }

    public void add(OrderResponse data) {
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


        void onItemClick(OrderResponse data);
    }
    public interface AdapterListenerviewdetails {


        void onItemClickviewdetails(OrderResponse data);
    }

    public interface InventoryAdapterListenerreview {
        void onAdapterItemClickedreview(OrderResponse data);
    }

    public interface InventoryAdapterListenerreturn {
        void onAdapterItemClickedreturn(OrderResponse data);
    }
}