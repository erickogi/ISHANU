package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.PaymentMethods;
import com.erickogi14gmail.ishanu.Interfaces.PaymentCardClickListener;
import com.erickogi14gmail.ishanu.R;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created by Eric on 11/30/2017.
 */

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.MyViewHolder> {
    private Context context;
    private int checkedPosition = -1;
    private LinkedList<PaymentMethods> modelList;
    private PaymentCardClickListener paymentCardClickListener;

    public PaymentMethodsAdapter(Context context, LinkedList<PaymentMethods> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public PaymentMethodsAdapter(Context context, LinkedList<PaymentMethods> modelList, PaymentCardClickListener paymentCardClickListener) {
        this.context = context;
        this.modelList = modelList;
        this.paymentCardClickListener = paymentCardClickListener;
    }

    @Override
    public PaymentMethodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_option_item, parent, false);
        return new MyViewHolder(itemView, paymentCardClickListener);
    }

    @Override
    public void onBindViewHolder(PaymentMethodsAdapter.MyViewHolder holder, int position) {

        PaymentMethods paymentMethods = modelList.get(position);
        holder.txtMethodName.setText(paymentMethods.getName());
        if (position == checkedPosition) {
            holder.cardView.setEnabled(false);
            // holder.cardView.setCardElevation(20);
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.light_blue));

        } else {
            holder.cardView.setEnabled(true);
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.transparent));

        }


        if (paymentMethods.getName().toLowerCase().contains("mpesa")) {
            holder.imgMethodPic.setImageResource(R.drawable.mpesa);
            holder.txtMethodName.setTextColor(context.getResources().getColor(R.color.google_green));
        } else if (paymentMethods.getName().toLowerCase().contains("airtel money")) {
            holder.imgMethodPic.setImageResource(R.drawable.airtell);
            holder.txtMethodName.setTextColor(context.getResources().getColor(R.color.google_red));
        } else if (paymentMethods.getName().toLowerCase().contains("cash")) {
            holder.imgMethodPic.setImageResource(R.drawable.cash);
            holder.txtMethodName.setTextColor(context.getResources().getColor(R.color.md_brown_700));
        } else if (paymentMethods.getName().toLowerCase().contains("cheque")) {
            holder.imgMethodPic.setImageResource(R.drawable.cheque);
            holder.txtMethodName.setTextColor(context.getResources().getColor(R.color.google_blue));
        } else if (paymentMethods.getName().toLowerCase().contains("equitel")) {
            holder.imgMethodPic.setImageResource(R.drawable.equitell);
            holder.txtMethodName.setTextColor(context.getResources().getColor(R.color.google_yellow));
        } else if (paymentMethods.getName().toLowerCase().contains("")) {

        }

    }

    public boolean isChecked(int position) {
        return checkedPosition == position;
    }

    public void setChecked(int position) {
        int prevChecked = checkedPosition;
        checkedPosition = position;

        if (prevChecked != -1) {
            notifyItemChanged(prevChecked);
        }
        notifyItemChanged(checkedPosition);
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(LinkedList<PaymentMethods> newList) {
        modelList = newList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtMethodName;
        ImageView imgMethodPic;
        CardView cardView;
        private WeakReference<PaymentCardClickListener> listenerWeakReference;


        public MyViewHolder(View itemView, PaymentCardClickListener paymentCardClickListener) {
            super(itemView);
            listenerWeakReference = new WeakReference<PaymentCardClickListener>(paymentCardClickListener);

            cardView = itemView.findViewById(R.id.payment_card);
            txtMethodName = itemView.findViewById(R.id.txt_method_name);
            imgMethodPic = itemView.findViewById(R.id.img_method_pic);

            cardView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.payment_card:
                    // v.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    listenerWeakReference.get().onClick(getAdapterPosition(), modelList.get(getAdapterPosition()), v);

                    break;

            }
        }
    }
}
