package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.R;

import java.util.LinkedList;

/**
 * Created by Eric on 11/24/2017.
 */

public class RecordsDetailAdapter extends RecyclerView.Adapter<RecordsDetailAdapter.MyViewHolder> {
    private Context context;
    private LinkedList<ProductModel> modelList;

    public RecordsDetailAdapter(Context context, LinkedList<ProductModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_sheet_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductModel productModel = modelList.get(position);
        holder.txtItemName.setText(productModel.getProduct_name() + " * " + productModel.getProduct_sale_quantity());
        double Qty = Double.valueOf(productModel.getProduct_sale_quantity()) * Double.valueOf(productModel.getProduct_price());
        holder.txtItemTotalPrice.setText("" + String.valueOf(Qty) + " Ksh");
        holder.txtItemPrice.setText("@ : " + productModel.getProduct_price() + "  Ksh");
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemName, txtItemPrice, txtItemTotalPrice;
        Button btnRemove, btnChange;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.item_name);
            txtItemTotalPrice = itemView.findViewById(R.id.item_total_price);
            txtItemPrice = itemView.findViewById(R.id.item_price);

            btnChange = itemView.findViewById(R.id.btn_change);
            btnRemove = itemView.findViewById(R.id.btn_remove);

            btnChange.setVisibility(View.GONE);
            btnRemove.setVisibility(View.GONE);
        }
    }
}
