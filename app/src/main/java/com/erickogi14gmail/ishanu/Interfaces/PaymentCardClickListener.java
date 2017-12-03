package com.erickogi14gmail.ishanu.Interfaces;

import android.view.View;

import com.erickogi14gmail.ishanu.Data.Models.PaymentMethods;

/**
 * Created by Eric on 11/30/2017.
 */

public interface PaymentCardClickListener {
    void onClick(int position, PaymentMethods paymentMethods, View v);
}
