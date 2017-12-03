package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.CustomerModel;
import com.erickogi14gmail.ishanu.Data.Models.DataGen;
import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.Toast;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class FragmentOne extends Fragment implements BlockingStep, DialogSearchCustomer.DialogSearchListener {
    PrefrenceManager prefrenceManager;
    private TextView myName, myRoute, myCar, date;
    private Spinner spinerCustomer, spinnerSerial;

    private TextView lmyName, lmyDate, lmyRoute, lmyCar;
    private Button btnSelectCustomer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }


    public void initUI() {
        String customersName = prefrenceManager.getCustomer();
        if (customersName.equals("Customers Name")) {
            btnSelectCustomer.setText("Select Customer");
            spinnerSerial.setSelection(0);

        } else {
            spinnerSerial.setSelection(prefrenceManager.getSortiePos());
            btnSelectCustomer.setText(customersName);
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefrenceManager = new PrefrenceManager(getContext());
        myName = view.findViewById(R.id.txt_my_name);
        myCar = view.findViewById(R.id.txt_my_car);
        myRoute = view.findViewById(R.id.txt_my_route);
        date = view.findViewById(R.id.txt_date);
        btnSelectCustomer = view.findViewById(R.id.btn_select_customer);
        btnSelectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSelectCustomer(DataGen.genData());
            }
        });

        spinerCustomer = view.findViewById(R.id.spinner_customer);
        spinnerSerial = view.findViewById(R.id.spinner_serial);


        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String datee = DATE_FORMAT.format(today);
        date.setText(datee);
        MyAccountModel myAccountModel = prefrenceManager.getAccount();
        myName.setText(myAccountModel.getName());
        myCar.setText(myAccountModel.getCar());
        myRoute.setText(myAccountModel.getRoute());


        try {


            lmyName = view.findViewById(R.id.my_name);
            lmyDate = view.findViewById(R.id.my_date);
            lmyRoute = view.findViewById(R.id.my_route);
            lmyCar = view.findViewById(R.id.my_car);


            lmyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.name, 0);
            lmyDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.today, 0);

            lmyRoute.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.route, 0);
            lmyCar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.car, 0);

        } catch (Exception nm) {

            nm.printStackTrace();
        }

        initUI();
    }

    private void dialogSelectCustomer(LinkedList<CustomerModel> customerModels) {
        FragmentManager fm = getFragmentManager();
        DialogSearchCustomer dialogSearch = DialogSearchCustomer.newInstance("Customer", 1, customerModels);
        // dialogSearch.show(fm,"dialog");

        dialogSearch.setTargetFragment(FragmentOne.this, 300);
        dialogSearch.show(fm, "fragment_search");


    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.getStepperLayout().showProgress("Operation in progress, please wait...");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (btnSelectCustomer.getText().toString().toLowerCase().equals("select customer") || spinnerSerial.getSelectedItem().toString().equals("Select Sortie")) {
                    Toast.toast("Select Customer and Serial", getContext(), R.drawable.ic_error_outline_black_24dp);
                    callback.getStepperLayout().hideProgress();
                } else {
                    prefrenceManager.setCustomersName(btnSelectCustomer.getText().toString());
                    prefrenceManager.setSortie(spinnerSerial.getSelectedItem().toString(),
                            spinnerSerial.getSelectedItemPosition());
                    //you can do anythings you want
                    callback.goToNextStep();
                    callback.getStepperLayout().hideProgress();
                }
            }
        }, 200L);// delay open another fragment,
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                //you can do anythings you want
                callback.goToPrevStep();
            }
        }, 0L);// delay open another fragment,
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        initUI();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSelected(CustomerModel model) {

        btnSelectCustomer.setText(model.getCustomer_name());
    }
}
