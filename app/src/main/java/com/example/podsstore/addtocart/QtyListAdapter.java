package com.example.podsstore.addtocart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.podsstore.R;
import com.example.podsstore.data.response.CartResponse;

public class QtyListAdapter  extends RecyclerView.Adapter<QtyListAdapter.MyViewHolder>{
    private MyListDataQty[] listdata;
private  QtyListAdapter.QtyAdapterListenertxt qtyAdapterListener;
    // RecyclerView recyclerView;

    public void setAdapterListenerqty(QtyListAdapter.QtyAdapterListenertxt adapterListener) {
        this.qtyAdapterListener = adapterListener;
    }
    public QtyListAdapter(MyListDataQty[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.qtylistitems, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyListDataQty myListData = listdata[position];
        holder.textView.setText(listdata[position].getQuantity());

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public RelativeLayout qtyrl;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
     qtyrl = itemView.findViewById(R.id.qtyrl);
            qtyrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (qtyAdapterListener!= null) {
                        qtyAdapterListener.onAdapterItemClickedqty(textView.getText().toString());

                    }
                }
            });

        }
    }
    public interface QtyAdapterListenertxt {
        void onAdapterItemClickedqty(String qty);
    }
}