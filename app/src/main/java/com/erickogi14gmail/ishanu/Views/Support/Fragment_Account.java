package com.erickogi14gmail.ishanu.Views.Support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;
import com.erickogi14gmail.ishanu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eric on 11/23/2017.
 */

public class Fragment_Account extends Fragment {
    PrefrenceManager prefrenceManager;
    private TextView myName, myRoute, myCar, date, myEmail, myNo, myPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefrenceManager = new PrefrenceManager(getContext());
        myName = view.findViewById(R.id.txt_my_name);
        myCar = view.findViewById(R.id.txt_my_car);
        myRoute = view.findViewById(R.id.txt_my_route);
        date = view.findViewById(R.id.txt_date);
        myEmail = view.findViewById(R.id.txt_my_email);
        myNo = view.findViewById(R.id.txt_my_mobile);
        myPass = view.findViewById(R.id.txt_my_password);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String datee = DATE_FORMAT.format(today);
        date.setText(datee);
        MyAccountModel myAccountModel = prefrenceManager.getAccount();
        myName.setText(myAccountModel.getName());
        myCar.setText(myAccountModel.getCar());
        myRoute.setText(myAccountModel.getRoute());
        myEmail.setText(myAccountModel.getEmail());
        myNo.setText(myAccountModel.getMobilr());
        myPass.setText(myAccountModel.getPassword());
    }
}
