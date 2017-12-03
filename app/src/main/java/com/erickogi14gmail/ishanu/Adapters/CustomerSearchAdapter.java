package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.CustomerModel;
import com.erickogi14gmail.ishanu.R;

import java.util.LinkedList;

/**
 * Created by Eric on 11/30/2017.
 */

public class CustomerSearchAdapter extends RecyclerView.Adapter<CustomerSearchAdapter.MyViewHolder> {

    private Context context;
    private LinkedList<CustomerModel> modelList;

    public CustomerSearchAdapter(Context context, LinkedList<CustomerModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public CustomerSearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerSearchAdapter.MyViewHolder holder, int position) {

        CustomerModel customerModel = modelList.get(position);
        holder.txtCustomerName.setText(customerModel.getCustomer_name());
        holder.txtCustomerLocation.setText(customerModel.getCustomer_location());


    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(LinkedList<CustomerModel> newList) {
        modelList = newList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCustomerName, txtCustomerLocation;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtCustomerName = itemView.findViewById(R.id.txt_customer_name);
            txtCustomerLocation = itemView.findViewById(R.id.txt_customer_location);

        }
    }
}
