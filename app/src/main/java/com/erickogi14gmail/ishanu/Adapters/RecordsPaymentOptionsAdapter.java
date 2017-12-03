package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.PaymentsModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.Commafy;

import java.util.LinkedList;

/**
 * Created by Eric on 12/3/2017.
 */

public class RecordsPaymentOptionsAdapter extends RecyclerView.Adapter<RecordsPaymentOptionsAdapter.MyViewHolder> {
    private Context context;
    private LinkedList<PaymentsModel> modelList;

    public RecordsPaymentOptionsAdapter(Context context, LinkedList<PaymentsModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_payment_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PaymentsModel paymentsModel = modelList.get(position);
        holder.textName.setText(paymentsModel.getPayment_name());
        holder.textAmount.setText(Commafy.addCommify(String.valueOf(paymentsModel.getPayment_amount())));
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(LinkedList<PaymentsModel> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textAmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            textAmount = itemView.findViewById(R.id.amount);
            textName = itemView.findViewById(R.id.name);
        }
    }
}
