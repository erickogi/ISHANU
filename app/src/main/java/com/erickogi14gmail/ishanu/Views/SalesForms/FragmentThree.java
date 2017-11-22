package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by Eric on 11/22/2017.
 */

public class FragmentThree extends Fragment implements BlockingStep {

    String[] returns;
    String[] sales;
    View view;
    private TextView txtTotalSales, txtTotalReturns, txtTotalDue, txtPaid, txtBalance;
    private TextInputEditText edtMpesa, edtCash, edtCheque, edtComments;
    private PrefrenceManager prefrenceManager;
    private Double total_sales = 0.0, total_returns = 0.0, total_due = 0.0;
    private Double total_paid = 0.0;

    private void intUi(View view) {
        prefrenceManager = new PrefrenceManager(getContext());
        txtTotalSales = view.findViewById(R.id.txt_total_sales);
        txtTotalReturns = view.findViewById(R.id.txt_total_returns);
        txtTotalDue = view.findViewById(R.id.txt_total_due);
        txtPaid = view.findViewById(R.id.txt_paid);
        txtBalance = view.findViewById(R.id.txt_balance);
        edtMpesa = view.findViewById(R.id.edt_mpesa);
        edtCash = view.findViewById(R.id.edt_cash);
        edtCheque = view.findViewById(R.id.edt_cheque);
        edtComments = view.findViewById(R.id.edt_comments);
        returns = prefrenceManager.getReturns();
        sales = prefrenceManager.getSales();


        total_sales = 0.0;
        total_returns = 0.0;


        if (!sales[1].equals("null")) {
            total_sales = Double.valueOf(sales[1]);
            txtTotalSales.setText(sales[1] + " Ksh");
        }
        if (!returns[1].equals("null")) {

            total_returns = Double.valueOf(returns[1]);
            txtTotalReturns.setText(returns[1] + " Ksh");
        }

        txtTotalDue.setText(String.valueOf(total_sales - total_returns));
        total_due = total_sales - total_returns;


        edtMpesa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    if (Double.valueOf(s.toString()) > 0.0) {
                        Double mpesa = Double.valueOf(s.toString());
                        Double cash = Double.valueOf(edtCash.getText().toString());
                        Double cheque = Double.valueOf(edtCheque.getText().toString());
                        total_paid = mpesa + cash + cheque;
                        txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
                        txtBalance.setText("Balance : " + String.valueOf(total_due - total_paid) + " Ksh");


                    } else {

                        // edtMpesa.setText("0.0");
                    }
                } catch (Exception nm) {
                    nm.printStackTrace();
                }
            }
        });
        edtCheque.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (Double.valueOf(s.toString()) > 0.0) {
                        Double mpesa = Double.valueOf(edtMpesa.getText().toString());
                        Double cash = Double.valueOf(edtCash.getText().toString());
                        Double cheque = Double.valueOf(s.toString());
                        total_paid = mpesa + cash + cheque;
                        txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
                        txtBalance.setText("Balance : " + String.valueOf(total_due - total_paid) + " Ksh");
                    } else {

                        // edtCheque.setText("0.0");
                    }
                } catch (Exception nm) {
                    nm.printStackTrace();
                }
            }
        });
        edtCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (Double.valueOf(s.toString()) > 0.0) {
                        Double mpesa = Double.valueOf(edtMpesa.getText().toString());
                        Double cash = Double.valueOf(s.toString());
                        Double cheque = Double.valueOf(edtCheque.getText().toString());
                        total_paid = mpesa + cash + cheque;
                        txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
                        txtBalance.setText("Balance : " + String.valueOf(total_due - total_paid) + " Ksh");
                    } else {

                        // edtCash.setText("0.0");
                    }
                } catch (Exception nm) {
                    nm.printStackTrace();
                }
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, container, false);

        intUi(view);


        return view;
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                alertDialogDelete("You are about to transimt this data.If You are sure ,click on confirm. NB* This action is irreversible", 1
                        , callback);
                //you can do anythings you want
                //callback.complete();
            }
        }, 200L);// delay open another fragment,
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        new Handler().postDelayed(() -> {

            //you can do anythings you want
            callback.goToPrevStep();
        }, 0L);// delay open another fragment,
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        intUi(view);

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


    private void alertDialogDelete(final String message, final int id, StepperLayout.OnCompleteClickedCallback callback) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    prefrenceManager.clearReturnsData();
                    prefrenceManager.clearSalesData();
                    callback.complete();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();


                    break;
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(message).setPositiveButton("Confirm", dialogClickListener)
                .setNegativeButton("Dismiss", dialogClickListener).show();

    }
}