package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.Interfaces.SaleSheetListner;
import com.erickogi14gmail.ishanu.R;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class SaleSheetAdapter extends RecyclerView.Adapter<SaleSheetAdapter.MyViewHolder> {
    private Context context;
    private LinkedList<ProductModel> modelList;
    private SaleSheetListner saleItemClickListener;

    public SaleSheetAdapter(Context context, LinkedList<ProductModel> modelList, SaleSheetListner saleItemClickListener) {
        this.context = context;
        this.modelList = modelList;
        this.saleItemClickListener = saleItemClickListener;
    }

    @Override
    public SaleSheetAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_sheet_list_item, parent, false);
        return new MyViewHolder(itemView, saleItemClickListener);

    }

    @Override
    public void onBindViewHolder(SaleSheetAdapter.MyViewHolder holder, int position) {
        ProductModel productModel = modelList.get(position);
        holder.txtItemName.setText(productModel.getProduct_name() + " * " + productModel.getProduct_sale_quantity());
        double Qty = Double.valueOf(productModel.getProduct_sale_quantity()) * Double.valueOf(productModel.getProduct_price());
        try {
            holder.txtItemTotalPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pric, 0, 0, 0);
        } catch (Exception nm) {

        }
        holder.txtItemTotalPrice.setText("" + String.valueOf(Qty) + " Ksh");

        holder.txtItemPrice.setText("@ : " + productModel.getProduct_price() + "  Ksh");
    }

    public void updateList(LinkedList<ProductModel> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public void updateItemItem(int position, ProductModel productModel) {
        modelList.get(position).setProduct_sale_quantity(productModel.getProduct_sale_quantity());
        modelList.get(position).setProduct_price(productModel.getProduct_price());
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItemName, txtItemPrice, txtItemTotalPrice;
        Button btnRemove, btnChange;
        private WeakReference<SaleSheetListner> listenerWeakReference;

        public MyViewHolder(View itemView, SaleSheetListner saleSheetListner) {
            super(itemView);
            listenerWeakReference = new WeakReference<SaleSheetListner>(saleSheetListner);

            txtItemName = itemView.findViewById(R.id.item_name);
            txtItemTotalPrice = itemView.findViewById(R.id.item_total_price);
            txtItemPrice = itemView.findViewById(R.id.item_price);

            btnChange = itemView.findViewById(R.id.btn_change);
            btnRemove = itemView.findViewById(R.id.btn_remove);

            btnRemove.setOnClickListener(this);
            btnChange.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_remove:
                    listenerWeakReference.get().onDeleteClicked(getAdapterPosition(), modelList);

                    break;
                case R.id.btn_change:
                    listenerWeakReference.get().onEditClicked(getAdapterPosition(), modelList);


                    break;
            }
        }
    }
}
