package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.erickogi14gmail.ishanu.Adapters.PaymentMethodsAdapter;
import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.DataGen;
import com.erickogi14gmail.ishanu.Data.Models.PaymentMethods;
import com.erickogi14gmail.ishanu.Data.Models.PaymentsModel;
import com.erickogi14gmail.ishanu.Data.Models.RecordModel;
import com.erickogi14gmail.ishanu.Interfaces.PaymentCardClickListener;
import com.erickogi14gmail.ishanu.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Eric on 11/22/2017.
 */

public class FragmentThree extends Fragment implements BlockingStep, TextWatcher {

    String[] returns;
    String[] sales;
    View view;
    //private QBadgeView qBadgeView;
    DbOperations dbOperations;
    LinkedList<PaymentMethods> paymentMethods;
    LinkedList<PaymentsModel> paymentsModels;
    private TextView txtTotalSales, txtTotalReturns, txtTotalDue, txtPaid, txtBalance;
    private TextInputEditText edtMpesa, edtCash, edtCheque, edtComments;
    private PrefrenceManager prefrenceManager;
    private Double total_sales = 0.0, total_returns = 0.0, total_due = 0.0;
    private Double total_paid = 0.0;
    private ImageView inspectSales, inspectRetruns;
    private String returnsData = "null", salesData = "null";
    private double total_balance = 0.0;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinearLayout linearLayoutEmpty;
    private PaymentMethodsAdapter paymentMethodsAdapter;
    private TextView paymentName;
    private TextInputEditText edtCode, edtAmountPaid;

    private double getTotalPaid() {
        double value = 0.0;
        for (PaymentsModel paymentsModel : paymentsModels) {
            value += paymentsModel.getPayment_amount();
        }
        return value;
    }




    private void intUi(View view) {

        paymentsModels = new LinkedList<>();
        LinkedList<PaymentMethods> paymentMethods = DataGen.genData(true);
        for (PaymentMethods paymentMethods1 : paymentMethods) {
            PaymentsModel paymentsModel = new PaymentsModel();
            paymentsModel.setPayment_name(paymentMethods1.getName());
            paymentsModel.setPayment_id(paymentMethods1.getId());
            paymentsModel.setPayment_amount(0.0);
            paymentsModel.setPayment_code("");

            paymentsModels.add(paymentsModel);


        }
        dbOperations = new DbOperations(getContext());
        prefrenceManager = new PrefrenceManager(getContext());
        txtTotalSales = view.findViewById(R.id.txt_total_sales);
        txtTotalReturns = view.findViewById(R.id.txt_total_returns);
        txtTotalDue = view.findViewById(R.id.txt_total_due);
        txtPaid = view.findViewById(R.id.txt_paid_m);
        txtBalance = view.findViewById(R.id.txt_balance_m);


        edtMpesa = view.findViewById(R.id.edt_mpesa);
        edtCash = view.findViewById(R.id.edt_cash);
        edtCheque = view.findViewById(R.id.edt_cheque);

        edtAmountPaid = view.findViewById(R.id.edt_cash_m);
        edtAmountPaid.addTextChangedListener(this);

        edtMpesa.setText("0.0");
        edtCash.setText("0.0");
        edtCheque.setText("0.0");
        //  qBadgeView = new QBadgeView(getContext());
        inspectRetruns = view.findViewById(R.id.inspect_returns);
        inspectSales = view.findViewById(R.id.inspect_sales);

        inspectSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepperLayout stepperLayout = getActivity().findViewById(R.id.stepperLayout);
                stepperLayout.setCurrentStepPosition(1);
            }
        });
        inspectRetruns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepperLayout stepperLayout = getActivity().findViewById(R.id.stepperLayout);
                stepperLayout.setCurrentStepPosition(2);
            }
        });

        edtComments = view.findViewById(R.id.edt_comments);
        returns = prefrenceManager.getReturns();
        sales = prefrenceManager.getSales();


        total_sales = 0.0;
        total_returns = 0.0;


        if (!sales[1].equals("null")) {
            salesData = sales[0];
            total_sales = Double.valueOf(sales[1]);
            txtTotalSales.setText(sales[1] + " Ksh");


        }
        if (!returns[1].equals("null")) {

            returnsData = returns[0];
            total_returns = Double.valueOf(returns[1]);
            txtTotalReturns.setText(returns[1] + " Ksh");

        }


        txtTotalDue.setText(String.valueOf(total_sales - total_returns));

        total_balance = total_sales - total_returns;

        total_due = total_sales - total_returns;


        total_paid = getTotalPaid();


        txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
        txtBalance.setText("Balance : " + String.valueOf(total_due - total_paid) + " Ksh");
        total_balance = total_due - total_paid;


        recyclerView = view.findViewById(R.id.recyclerView);
        paymentName = view.findViewById(R.id.txt_payment_name);


        paymentMethodsAdapter = new PaymentMethodsAdapter(getContext(), paymentMethods, new PaymentCardClickListener() {
            @Override
            public void onClick(int position, PaymentMethods paymentMethods, View v) {


                YoYo.with(Techniques.Wobble)
                        .duration(200)
                        .repeat(4)
                        .playOn(v);
                paymentName.setText(paymentMethods.getName());
                if (!paymentMethodsAdapter.isChecked(position)) {
                    paymentMethodsAdapter.setChecked(position);
                }


                for (int a = 0; a < paymentsModels.size(); a++) {
                    if (paymentsModels.get(a).getPayment_id() == paymentMethods.getId()) {
                        try {
                            edtAmountPaid.setText(String.valueOf(paymentsModels.get(a).getPayment_amount()));
                        } catch (Exception nm) {

                            nm.printStackTrace();
                        }
                    }
                }


            }
        });
        paymentMethodsAdapter.setChecked(0);
        paymentMethodsAdapter.notifyDataSetChanged();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(paymentMethodsAdapter);


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

                if (!salesData.equals("null") || !returnsData.equals("null")) {
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                    String datee = DATE_FORMAT.format(today);
                    RecordModel recordModel = new RecordModel();
                    recordModel.setProducts(salesData);
                    recordModel.setProduct_total(String.valueOf(total_sales));

                    recordModel.setRetutns(returnsData);
                    recordModel.setReturns_total(String.valueOf(total_returns));

                    recordModel.setPaid(String.valueOf(total_paid));
                    recordModel.setBalance(String.valueOf(total_balance));
                    recordModel.setDate(datee);
                    recordModel.setCustomer_name(prefrenceManager.getCustomer());
                    recordModel.setCash(edtCash.getText().toString());
                    recordModel.setCheque(edtCheque.getText().toString());
                    recordModel.setMpesa(edtMpesa.getText().toString());

                    recordModel.setPaymentsModels(paymentsModels);


                    alertDialogDelete("You are about to transimt this data.If You are sure ,click on confirm. NB* This action is irreversible", 1
                            , callback, recordModel);
                } else {
                    com.erickogi14gmail.ishanu.Utils.Toast.toast("No data for transmission", getContext(), R.drawable.ic_error_outline_black_24dp);
                }
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


    private void alertDialogDelete(final String message, final int id, StepperLayout.OnCompleteClickedCallback callback, RecordModel recordModel) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:


                    if (dbOperations.insertRecord(recordModel)) {
                        prefrenceManager.clearCustomerName();
                        prefrenceManager.clearReturnsData();
                        prefrenceManager.clearSalesData();

                        callback.complete();
                    } else {
                        Toast.makeText(getContext(), "Error Saving", Toast.LENGTH_SHORT).show();
                    }
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


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            if (Double.valueOf(s.toString()) >= 0.0) {


                Double alreadyPaid = 0.0;
                for (PaymentsModel paymentsModel : paymentsModels) {

                    alreadyPaid += paymentsModel.getPayment_amount();

                }

                total_paid = alreadyPaid + Double.valueOf(edtAmountPaid.getText().toString());
                total_balance = total_due - total_paid;

                txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
                txtBalance.setText("Balance : " + String.valueOf(total_balance) + " Ksh");

                for (int a = 0; a < paymentsModels.size(); a++) {
                    if (paymentsModels.get(a).getPayment_name().toLowerCase().equals(paymentName.getText().toString().toLowerCase())) {
                        paymentsModels.get(a).setPayment_amount(Double.valueOf(edtAmountPaid.getText().toString()));
                    }
                }


//                        Double mpesa = Double.valueOf(edtMpesa.getText().toString());
//                        Double cash = Double.valueOf(s.toString());
//                        Double cheque = Double.valueOf(edtCheque.getText().toString());
//                        total_paid = mpesa + cash + cheque;
//                        txtPaid.setText("Total Paid : " + String.valueOf(total_paid) + " Ksh");
//                        txtBalance.setText("Balance : " + String.valueOf(total_due - total_paid) + " Ksh");
//                        total_balance = total_due - total_paid;
            } else {

                // edtCash.setText("0.0");
            }
        } catch (Exception nm) {
            nm.printStackTrace();
        }
    }
}
