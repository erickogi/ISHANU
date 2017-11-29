package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.RecordModel;
import com.erickogi14gmail.ishanu.R;

import java.util.LinkedList;

/**
 * Created by Eric on 11/23/2017.
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {
    private Context context;
    private LinkedList<RecordModel> modelList;

    public RecordsAdapter(Context context, LinkedList<RecordModel> modelList) {
        this.context = context;
        this.modelList = modelList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecordModel recordModel = modelList.get(position);
        holder.textViewId.setText("Tr.No : " + String.valueOf(recordModel.getRecord_id()));
        holder.textViewPaid.setText("Paid :" + recordModel.getPaid() + "  Ksh");
        // holder.textViewDate.setText(recordModel.getTransaction_date());
        holder.textViewDate.setText(recordModel.getDate());
        holder.textViewPrice.setText("Due  " + String.valueOf(Double.valueOf(recordModel.getProduct_total())
                - Double.valueOf(recordModel.getReturns_total())) + " Ksh");

    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(LinkedList<RecordModel> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewPaid, textViewDate, textViewPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.transaction_id);
            textViewPaid = itemView.findViewById(R.id.transaction_total_paid);
            textViewDate = itemView.findViewById(R.id.transaction_date);
            textViewPrice = itemView.findViewById(R.id.transaction_total_price);
        }
    }
}
