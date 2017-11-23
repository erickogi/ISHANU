package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.Toast;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eric on 11/21/2017.
 */

public class FragmentOne extends Fragment implements BlockingStep {
    PrefrenceManager prefrenceManager;
    private TextView myName, myRoute, myCar, date;
    private Spinner spinerCustomer, spinnerSerial;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefrenceManager = new PrefrenceManager(getContext());
        myName = view.findViewById(R.id.txt_my_name);
        myCar = view.findViewById(R.id.txt_my_car);
        myRoute = view.findViewById(R.id.txt_my_route);
        date = view.findViewById(R.id.txt_date);

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

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.getStepperLayout().showProgress("Operation in progress, please wait...");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (spinerCustomer.getSelectedItem().toString().equals("Select Customer") || spinnerSerial.getSelectedItem().toString().equals("Select Serial")) {
                    Toast.toast("Select Customer and Serial", getContext(), R.drawable.ic_error_outline_black_24dp);
                    callback.getStepperLayout().hideProgress();
                } else {
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

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
