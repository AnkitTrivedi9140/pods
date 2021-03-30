package com.example.podsstore.addtocart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;

import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.response.CartResponse;


import java.util.ArrayList;
import java.util.List;

public class AddtocartAdapter extends RecyclerView.Adapter<AddtocartAdapter.MyViewHolder> {
    private AddtocartAdapter.AdapterListener adapterListener;
    private List<CartResponse> productResponseList;
    private Context context;
    DataTransferInterface dtInterface;
String qty;
    private QuantityViewModel viewModel;
    private AddtocartAdapter.InventoryAdapterListener openListener;
    private AddtocartAdapter.AdapterListenerplus adapterListenerplus;
    private AddtocartAdapter.AdapterListenerless adapterListenerless;

    private AddtocartAdapter.AdapterListenercart adapterListenercart;

    public void setAdapterListener(AddtocartAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }
    public void setAdapterListenercart(AddtocartAdapter.AdapterListenercart adapterListener) {
        this.adapterListenercart = adapterListener;
    }
    public void setAdapterListeners(AddtocartAdapter.InventoryAdapterListener adapterListener) {
        this.openListener = adapterListener;
    }
    public void setAdapterListenerplus(AddtocartAdapter.AdapterListenerplus adapterListener) {
        this.adapterListenerplus = adapterListener;
    }

    public void setAdapterListenersless(AddtocartAdapter.AdapterListenerless adapterListener) {
        this.adapterListenerless = adapterListener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, tvAssetType,tvqty,prnumber;
        public ImageView productiv,deleteproductiv;
        public CardView cardView,less,more;
        RelativeLayout wishlist;
        ArrayList<String> arrayList;
        int counter=1;
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

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterListenercart != null) {
                        adapterListenercart.onItemClick(productResponseList.get(getAdapterPosition()));
                    }
                }
            });
            wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openListener != null) {
                        openListener.onAdapterItemClicked(productResponseList.get(getAdapterPosition()));
                    }
                }
            });
            deleteproductiv.setOnClickListener( v -> {

                if (adapterListener != null) {
                    adapterListener.onItemClick(productResponseList.get(getAdapterPosition()));
                }
            }
            );

            less.setOnClickListener(v -> {

            counter=counter-1;
            if(counter<=0)  {
                prnumber.setText(String.valueOf("1"));
            }else{
                prnumber.setText(String.valueOf(counter));
            }


                if (adapterListener != null) {
                    adapterListenerplus.onItemClickplus(productResponseList.get(getAdapterPosition()),prnumber.getText().toString());
                }
            });
            more.setOnClickListener(v -> {
                counter=counter+1;

                if(counter<=0)  {
                    prnumber.setText(String.valueOf("1"));
                }else{
                    prnumber.setText(String.valueOf(counter));
                }
              //  prnumber.setText(String.valueOf(counter));

                for(int i=0;i<productResponseList.size();i++) {
                    for(int j=0;j<arrayList.size();j++) {

/*
                        update(arrayList.get(j),productResponseList.get(i).getProductid().toString());*/

                    }
                }
                if (adapterListener != null) {
                    adapterListenerless.onItemClickless(productResponseList.get(getAdapterPosition()),prnumber.getText().toString());
                }
            });
            arrayList=new ArrayList<>();
            arrayList.add(prnumber.getText().toString());
            dtInterface.onSetValues(arrayList);



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
        holder.tvAssetType.setText(cartResponse.getProducttype());
        holder.description.setText("$ "+cartResponse.getPrice());
        holder.tvqty.setText("Qty_"+cartResponse.getQty());
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(QuantityViewModel.class);
        String lastqty = viewModel.getqty(cartResponse.getProductid().toString());

if(lastqty==null){
    holder.prnumber.setText(String.valueOf("1"));
}else {
    holder.prnumber.setText(String.valueOf(lastqty));
}

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

    public interface AdapterListenercart {


        void onItemClick(CartResponse data);
    }

    public interface InventoryAdapterListener {
        void onAdapterItemClicked(CartResponse data);
    }
    public interface DataTransferInterface {
        public void onSetValues(ArrayList<String> al);
    }

    public interface AdapterListenerplus {


        void onItemClickplus(CartResponse data,String qty);
    }

    public interface AdapterListenerless{
        void onItemClickless(CartResponse data,String qty);
    }
}