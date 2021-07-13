package com.example.podsstore.addtocart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;

import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.request.QtyRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.QtyResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AddtocartAdapter extends RecyclerView.Adapter<AddtocartAdapter.MyViewHolder> {
    private AddtocartAdapter.AdapterListener adapterListener;
    private List<CartResponse> productResponseList;
    private Context context;
    DataTransferInterface dtInterface;
String qty;

    private AddtocartAdapter.InventoryAdapterListener openListener;
    private AddtocartAdapter.AdapterListenerplus adapterListenerplus;
    private AddtocartAdapter.AdapterListenerless adapterListenerless;
    private AddtocartAdapter.QtyAdapterListener qtyAdapterListener;

    private AddtocartAdapter.AdapterListenercart adapterListenercart;

    public void setAdapterListener(AddtocartAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }
    public void setAdapterListenercart(AddtocartAdapter.AdapterListenercart adapterListener) {
        this.adapterListenercart = adapterListener;
    }
    public void setAdapterListenerqty(AddtocartAdapter.QtyAdapterListener adapterListener) {
        this.qtyAdapterListener = adapterListener;
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
        EditText tvqtybtn;
        public ImageView productiv,deleteproductiv,ivgo;
        public CardView cardView;

                RelativeLayout less,more;
        RelativeLayout wishlist;
        ArrayList<String> arrayList;
        int counter=1;
        public MyViewHolder(View view) {
            super(view);
            tvqty = (TextView) view.findViewById(R.id.tvqty);
            ivgo =  view.findViewById(R.id.ivgo);
            tvqtybtn = view.findViewById(R.id.tvqtybtn);
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
            tvqtybtn.setOnClickListener( v -> {

                        if (qtyAdapterListener != null) {
                            qtyAdapterListener.onAdapterItemClickedqty(productResponseList.get(getAdapterPosition()));
                        }
                    }
            );

            less.setOnClickListener(v -> {

            counter=Integer.valueOf(prnumber.getText().toString())-1;
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
                counter=Integer.valueOf(prnumber.getText().toString())+1;

                if(counter<=0)  {
                    prnumber.setText(String.valueOf("1"));
                }else{
                    prnumber.setText(String.valueOf(counter));
                }
              //  prnumber.setText(String.valueOf(counter));

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

if(cartResponse.getOfferflag()==null){
    holder.wishlist.setVisibility(View.VISIBLE);
}else{
    holder.wishlist.setVisibility(View.GONE);
}
if(cartResponse.getQty().toString().equalsIgnoreCase("0")){
    holder.prnumber.setText("1");
}else{
    holder.prnumber.setText(cartResponse.getQty().toString());
}


      // Toast.makeText(context,movies.getImageUrl(),Toast.LENGTH_LONG).show();
      Glide.with(context)
              .load(cartResponse.getImageUrl().toString())
              .into(holder.productiv);

holder.ivgo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String aa=extractInt(  holder.prnumber.getText().toString());
        // for(int i=0;i<productResponseList.size();i++){
        if(aa.contains("-")){
            // Toast.makeText(context,"-----",Toast.LENGTH_LONG).show();
            aa.replace("-", "");

            addqty(charRemoveAt(aa,0),productResponseList.get(position).getProductid().toString());

        }else {

            aa.replace("-", "");
            addqty(aa.trim().toString(),productResponseList.get(position).getProductid().toString());

        }
    }
});

//        holder.tvqtybtn.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                holder.  tvqtybtn.setCursorVisible(true);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String aa=extractInt(  holder.tvqtybtn.getText().toString());
//                holder. tvqtybtn.setCursorVisible(true);
//                //  addqty(aa.trim().toString(),productResponseList.get(0).getProductid().toString());
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String aa=extractInt(  holder.tvqtybtn.getText().toString());
//               // for(int i=0;i<productResponseList.size();i++){
//                if(aa.contains("-")){
//                   // Toast.makeText(context,"-----",Toast.LENGTH_LONG).show();
//                    aa.replace("-", "");
//
//                    addqty(charRemoveAt(aa,0),productResponseList.get(position).getProductid().toString());
//
//                }else {
//
//                    aa.replace("-", "");
//                    addqty(aa.trim().toString(),productResponseList.get(position).getProductid().toString());
//
//                }
//
//
//
//
//                // }
//                //tvqtybtn.setCursorVisible(false);
//            }
//        });
    }
    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
    static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" -", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }

    @SuppressLint("CheckResult")
    private void addqty( String qty, String prodid) {


        QtyRequest r = new QtyRequest();
        r.setQuantity(qty);
        r.setProductid(prodid);


        // list.add(r);

        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().addqty(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<QtyResponse>>() {
                    @Override
                    public void onSuccess(Response<QtyResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            QtyResponse successResponse = response.body();

                            Toast.makeText(context, successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context, AddToCartActivity.class);
                           context. startActivity(i);

//                            Log.e("onSuccessaa", successResponse.getChallanid());


                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            //Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
        // binding.progressbar.setVisibility(View.VISIBLE);

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
    public interface QtyAdapterListener {
        void onAdapterItemClickedqty(CartResponse data);
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