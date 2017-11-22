package com.erickogi14gmail.ishanu.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Views.MainActivity;

/**
 * Created by Eric on 11/20/2017.
 */

public class FragmentSignUp extends Fragment {

    private TextInputEditText edtName, edtEmail, edtMobile, edtPassword, edtConfirmPass, edtCar;
    private Spinner spinnerRoute;
    private Button btnSignUp;

    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerRoute = view.findViewById(R.id.spinner_route);
        edtName = view.findViewById(R.id.txt_firstname);
        edtEmail = view.findViewById(R.id.txt_emailAdress);
        edtCar = view.findViewById(R.id.txt_car);
        edtMobile = view.findViewById(R.id.txt_mobile);
        edtPassword = view.findViewById(R.id.txt_password);
        edtConfirmPass = view.findViewById(R.id.txt_confirm_password);
        btnSignUp = view.findViewById(R.id.btn_register);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFilled(edtName) && isFilled(edtEmail) && isFilled(edtMobile)
                        && isFilled(edtCar) && isFilled(edtConfirmPass)
                        && isFilled(edtPassword) && isSpinnerFilled(spinnerRoute)) {

                    if (validateFields()) {
                        MyAccountModel myAccountModel = new MyAccountModel();
                        myAccountModel.setName(edtName.getText().toString());
                        myAccountModel.setEmail(edtEmail.getText().toString());
                        myAccountModel.setMobilr(edtMobile.getText().toString());
                        myAccountModel.setPassword(edtPassword.getText().toString());
                        myAccountModel.setCar(edtCar.getText().toString());
                        myAccountModel.setRoute(spinnerRoute.getSelectedItem().toString());
                        PrefrenceManager prefrenceManager = new PrefrenceManager(getContext());
                        prefrenceManager.createLogin(myAccountModel);
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                } else {
                    // Toast.makeText(getContext(), "Fill all fields ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isSpinnerFilled(Spinner spinner) {
        if (!spinner.getSelectedItem().toString().isEmpty() && spinner.getSelectedItem().toString() != "Select Route") {
            return true;
        } else {
            Toast.makeText(getContext(), "Select Route", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validateFields() {
        if (!isValidEmail(edtEmail.getText().toString())) {
            edtEmail.setError("Invalid email");
            return false;
        } else if (!isValidPhoneNumber(edtMobile.getText().toString())) {
            edtMobile.setError("Invalid Number");
            return false;
        } else if (!isPasswordSimmilar(edtPassword.getText().toString(), edtConfirmPass.getText().toString())) {
            edtConfirmPass.setError("Passwords don't match");
            return false;
        } else {
            return true;
        }


    }

    private boolean isPasswordSimmilar(String password, String confirmPassword) {
        return (password.contentEquals(confirmPassword));
    }

    private boolean isFilled(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().equals("")) {
            textInputEditText.setError("Required");
            return false;
        } else {
            return true;
        }

    }












}
