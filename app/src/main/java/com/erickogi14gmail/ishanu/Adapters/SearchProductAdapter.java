package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.R;

import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.MyViewHolder> {
    private LinkedList<ProductModel> modelList;
    private Context context;

    public SearchProductAdapter(LinkedList<ProductModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @Override
    public SearchProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_dialog_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchProductAdapter.MyViewHolder holder, int position) {

        ProductModel productModel = modelList.get(position);
        holder.txtProductName.setText(productModel.getProduct_name());
        holder.txtProductPrice.setText(String.valueOf(productModel.getProduct_price()) + " Ksh");
        holder.txtProductQuantity.setText(String.valueOf(productModel.getProduct_load_quantity()));
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(LinkedList<ProductModel> newList) {
        modelList = newList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductName, txtProductPrice, txtProductQuantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductPrice = itemView.findViewById(R.id.product_price);
            txtProductQuantity = itemView.findViewById(R.id.product_quantity);

        }
    }
}
