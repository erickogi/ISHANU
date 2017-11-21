package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by Eric on 11/21/2017.
 */

public class FragmentTwo extends Fragment implements BlockingStep, DialogSearch.DialogSearchListener {
    private Button buttonAdd;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonAdd = view.findViewById(R.id.btn_add);
        buttonAdd.setOnClickListener(v -> showEditDialog());

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.getStepperLayout().showProgress("Operation in progress, please wait...");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //you can do anythings you want
                callback.goToNextStep();
                callback.getStepperLayout().hideProgress();
            }
        }, 2000L);// delay open another fragment,
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

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        DialogSearch dialogSearch = DialogSearch.newInstance("Search");
        // dialogSearch.show(fm,"dialog");

        dialogSearch.setTargetFragment(FragmentTwo.this, 300);
        dialogSearch.show(fm, "fragment_search");
    }

    @Override
    public void onSelected(ProductModel model) {
        dialog = new Dialog(getContext());

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_quantity, null);
        // View view=inflater.inflate(R.layout.dialog_search_item, container);
        view.setMinimumWidth((int) (displayRectangle.width() * 0.8f));
        // view.setMinimumHeight((int)(displayRectangle.height() * 0.8f));


        //dialog.setTitle("Update");
        dialog.setContentView(view);
        final EditText edtQ = dialog.findViewById(R.id.edt_quantity);
        final ImageButton btnP = dialog.findViewById(R.id.btn_add);
        ImageButton btnM = dialog.findViewById(R.id.btn_minus);
        // Button btnD = (Button) dialog.findViewById(R.id.btn_dismiss);
        Button btnU = dialog.findViewById(R.id.btn_update);

        dialog.show();
        // Toast.makeText(getContext(), ""+model.getProduct_name(), Toast.LENGTH_SHORT).show();
    }
}
