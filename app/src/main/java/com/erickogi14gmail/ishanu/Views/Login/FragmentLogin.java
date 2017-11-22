package com.erickogi14gmail.ishanu.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Views.MainActivity;

/**
 * Created by Eric on 11/20/2017.
 */

public class FragmentLogin extends Fragment {
    private TextInputEditText edtEmail, edtPassword;
    private Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private boolean isFilled(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().equals("")) {
            textInputEditText.setError("Required");
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            if (isFilled(edtEmail) && isFilled(edtPassword)) {
                PrefrenceManager prefrenceManager = new PrefrenceManager(getContext());
                String[] params = prefrenceManager.getLoginParams();
                if (edtPassword.getText().toString().contentEquals(params[0])) {
                    if (edtEmail.getText().toString().contentEquals(params[1]) || edtEmail.getText().toString().contentEquals(params[2])) {
                        prefrenceManager.setIsLoggedIn(true);
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    } else {
                        edtEmail.setError("Invalid");
                    }
                } else {
                    edtPassword.setError("Wrong password");
                }
            }
        });

    }
}
