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
import com.erickogi14gmail.ishanu.Utils.Commafy;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Eric on 12/3/2017.
 */

public class FragmentThew extends Fragment implements BlockingStep, TextWatcher {
    String[] returns;
    String[] sales;
    View view;
    ///Define all lists to be Used
    private LinkedList<PaymentMethods> paymentMethodFromDataGen;
    private LinkedList<PaymentsModel> paymentsModelForKeepingPayments;


    ///Define all ui elements
    private TextView txt_total_sales;
    private TextView txt_total_returns;


    private TextView txt_total_amount_due;


    private TextView txt_payments_option_name;
    private TextInputEditText edt_payment;
    private TextInputEditText edt_payment_code;


    private TextView txt_amount_paid;
    private TextView txt_balance;


    private TextInputEditText edt_comments;

    private ImageView inspectSales, inspectRetruns;
    //Payments
    private RecyclerView recyclerView;


    private PrefrenceManager prefrenceManager;
    private DbOperations dbOperations;
    private PaymentMethodsAdapter paymentMethodsAdapter;

    private StaggeredGridLayoutManager mStaggeredLayoutManager;


    private void initUIViews(View view) {

        txt_total_sales = view.findViewById(R.id.txt_total_sales);
        txt_total_returns = view.findViewById(R.id.txt_total_returns);
        txt_total_amount_due = view.findViewById(R.id.txt_total_due);

        txt_payments_option_name = view.findViewById(R.id.txt_payment_name);
        edt_payment = view.findViewById(R.id.edt_cash_m);
        edt_payment_code = view.findViewById(R.id.edt_code_m);


        txt_amount_paid = view.findViewById(R.id.txt_paid_m);
        txt_balance = view.findViewById(R.id.txt_balance_m);


        edt_comments = view.findViewById(R.id.edt_comments_m);

        recyclerView = view.findViewById(R.id.recyclerView);

        edt_payment.addTextChangedListener(new TextWatcher() {
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

                        for (int a = 0; a < paymentsModelForKeepingPayments.size(); a++) {
                            if (txt_payments_option_name.getText().toString().equals(paymentsModelForKeepingPayments.get(a).getPayment_name())) {
                                Double initial = paymentsModelForKeepingPayments.get(a).getPayment_amount();
                                paymentsModelForKeepingPayments.get(a).setPayment_amount(Double.valueOf(edt_payment.getText().toString()));
                                calculateDisplayDueBalnce();
                                // edt_payment.setSelection(edt_payment.getText().length());
                            }
                        }
                    } else {
                        for (int a = 0; a < paymentsModelForKeepingPayments.size(); a++) {
                            if (txt_payments_option_name.getText().toString().equals(paymentsModelForKeepingPayments.get(a).getPayment_name())) {
                                Double initial = paymentsModelForKeepingPayments.get(a).getPayment_amount();
                                paymentsModelForKeepingPayments.get(a).setPayment_amount(0.0);
                                calculateDisplayDueBalnce();
                                //  edt_payment.setSelection(edt_payment.getText().length());
                            }
                        }
                    }
                } catch (Exception nm) {
                    nm.printStackTrace();
                    for (int a = 0; a < paymentsModelForKeepingPayments.size(); a++) {
                        if (txt_payments_option_name.getText().toString().equals(paymentsModelForKeepingPayments.get(a).getPayment_name())) {
                            Double initial = paymentsModelForKeepingPayments.get(a).getPayment_amount();
                            paymentsModelForKeepingPayments.get(a).setPayment_amount(0.0);
                            calculateDisplayDueBalnce();
                            // edt_payment.setSelection(edt_payment.getText().length());
                        }
                    }
                }
            }
        });
        edt_payment_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    for (int a = 0; a < paymentsModelForKeepingPayments.size(); a++) {
                        if (txt_payments_option_name.getText().toString().equals(paymentsModelForKeepingPayments.get(a).getPayment_name())) {
                            paymentsModelForKeepingPayments.get(a).setPayment_code(edt_payment_code.getText().toString());
                        }
                    }

                } catch (Exception nm) {
                    nm.printStackTrace();
                    for (int a = 0; a < paymentsModelForKeepingPayments.size(); a++) {
                        if (txt_payments_option_name.getText().toString().equals(paymentsModelForKeepingPayments.get(a).getPayment_name())) {
                            paymentsModelForKeepingPayments.get(a).setPayment_code("null");

                        }
                    }
                }
            }
        });

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
        initExternalClass();


    }//Step 1


    private void initExternalClass() {
        prefrenceManager = new PrefrenceManager(getContext());
        dbOperations = new DbOperations(getContext());
        paymentMethodFromDataGen = new LinkedList<>();
        paymentsModelForKeepingPayments = new LinkedList<>();

        fetchPrefrences();
        displayAndCalculateDue();

    } //Step 2

    private String[] fetchSales() {
        sales = prefrenceManager.getSales();
        return sales;
    }//step 4

    private String[] fetchReturns() {
        returns = prefrenceManager.getReturns();
        return returns;
    }//step 5

    private void fetchPrefrences() {
        fetchReturns();
        fetchSales();
    }

    private void displayAndCalculateDue() {


        if (!sales[1].equals("null")) {

            // sales[1] + " Ksh"
            txt_total_sales.setText(Commafy.addCommify(fetchSales()[1]));


        }
        if (!returns[1].equals("null")) {

            // returns[1] + " Ksh"
            txt_total_returns.setText(Commafy.addCommify(fetchReturns()[1]));

        }


        Double sales = Double.valueOf(Commafy.removeCommify(txt_total_sales.getText().toString()));
        Double returns = Double.valueOf(Commafy.removeCommify(txt_total_returns.getText().toString()));


        txt_total_amount_due.setText(Commafy.addCommify(String.valueOf(sales - returns)));
        txt_balance.setText(txt_total_amount_due.getText().toString());
        txt_amount_paid.setText("0.0");


        initPayMentOptionsList();


    }//Step 3


    private LinkedList<PaymentMethods> getPaymentsOptions() {
        paymentMethodFromDataGen = DataGen.genData(true);
        return paymentMethodFromDataGen;
    }

    private void initPayMentOptionsList() {
        paymentMethodsAdapter = new PaymentMethodsAdapter(getContext(), getPaymentsOptions(), new PaymentCardClickListener() {
            @Override
            public void onClick(int position, PaymentMethods paymentMethods, View v) {


                YoYo.with(Techniques.Wobble)
                        .duration(200)
                        .repeat(4)
                        .playOn(v);
                txt_payments_option_name.setText(paymentMethods.getName());
                if (!paymentMethodsAdapter.isChecked(position)) {
                    paymentMethodsAdapter.setChecked(position);
                }


                OnPaymentOptionClicked(position, paymentMethods, v);


            }
        });
        txt_payments_option_name.setText(getPaymentsOptions().get(0).getName());

        ///Add all optionsToPayModel
        for (PaymentMethods paymentMethods : paymentMethodFromDataGen) {
            PaymentsModel paymentsModel = new PaymentsModel();
            paymentsModel.setPayment_id(paymentMethods.getId());
            paymentsModel.setPayment_code("");
            paymentsModel.setPayment_name(paymentMethods.getName());
            paymentsModel.setPayment_amount(0);


            paymentsModelForKeepingPayments.add(paymentsModel);
        }


        paymentMethodsAdapter.setChecked(0);
        paymentMethodsAdapter.notifyDataSetChanged();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(paymentMethodsAdapter);

    }


    public void OnPaymentOptionClicked(int positionClicked, PaymentMethods dataOfOptioClicked, View viewClicked) {

        for (PaymentsModel paymentsModel : paymentsModelForKeepingPayments) {
            if (paymentsModel.getPayment_id() == dataOfOptioClicked.getId()) {
                if (paymentsModel.getPayment_amount() == 0.0) {
                    edt_payment.setText(String.valueOf(0));
                } else {
                    edt_payment.setText(String.valueOf(paymentsModel.getPayment_amount()));
                }
                edt_payment.setSelection(edt_payment.getText().length());

                edt_payment_code.setText(paymentsModel.getPayment_code());
            }
        }

    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // salesData = sales[0];
                if (!sales[0].equals("null") || !returns[0].equals("null")) {
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                    String datee = DATE_FORMAT.format(today);
                    RecordModel recordModel = new RecordModel();
                    recordModel.setProducts(sales[0]);
                    recordModel.setProduct_total(String.valueOf(sales[1]));

                    recordModel.setRetutns(returns[0]);
                    recordModel.setReturns_total(String.valueOf(returns[1]));

                    recordModel.setPaid(String.valueOf(Commafy.removeCommify(txt_amount_paid.getText().toString())));
                    recordModel.setBalance(String.valueOf(Commafy.removeCommify(txt_balance.getText().toString())));
                    recordModel.setDate(datee);
                    recordModel.setCustomer_name(prefrenceManager.getCustomer());
                    recordModel.setCash("");
                    recordModel.setCheque("");
                    recordModel.setMpesa("");

                    recordModel.setPaymentsModels(paymentsModelForKeepingPayments);


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

        initUIViews(view);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initUIViews(view);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    private void calculateDisplayDueBalnce() {
        edt_payment.setSelection(edt_payment.getText().length());
        Double due = Double.valueOf(Commafy.removeCommify(txt_total_amount_due.getText().toString()));
        Double paid = 0.0;
        for (PaymentsModel paymentsModel : paymentsModelForKeepingPayments) {
            paid += paymentsModel.getPayment_amount();
        }

        txt_amount_paid.setText(Commafy.addCommify(String.valueOf(paid)));
        txt_balance.setText(Commafy.addCommify(String.valueOf(due - paid)));
    }


    private void alertDialogDelete(final String message,
                                   final int id, StepperLayout.OnCompleteClickedCallback callback,
                                   RecordModel recordModel) {
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


}
