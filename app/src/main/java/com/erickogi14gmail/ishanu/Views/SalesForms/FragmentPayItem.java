package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.erickogi14gmail.ishanu.Adapters.PaymentMethodsAdapter;
import com.erickogi14gmail.ishanu.Data.Models.DataGen;
import com.erickogi14gmail.ishanu.Data.Models.PaymentMethods;
import com.erickogi14gmail.ishanu.Interfaces.PaymentCardClickListener;
import com.erickogi14gmail.ishanu.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.LinkedList;

/**
 * Created by Eric on 11/30/2017.
 */

public class FragmentPayItem extends Fragment implements BlockingStep {
    LinkedList<PaymentMethods> paymentMethods;
    int previousPosition = 1222;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinearLayout linearLayoutEmpty;
    private PaymentMethodsAdapter paymentMethodsAdapter;
    private TextView paymentName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pay_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        //linearLayoutEmpty = view.findViewById(R.id.empty_layout);
        paymentName = view.findViewById(R.id.txt_payment_name);


        paymentMethodsAdapter = new PaymentMethodsAdapter(getContext(), DataGen.genData(true), new PaymentCardClickListener() {
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

                // View f=    paymentMethodsAdapter.getItemId(position);
//                if(previousPosition==1222){
//
//                }
//                else {
//                    View view1=recyclerView.getChildAt(previousPosition);
//                    view1.setEnabled(true);
//                    view1.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
//                }
//                previousPosition=position;


            }
        });
        paymentMethodsAdapter.notifyDataSetChanged();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(paymentMethodsAdapter);

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        // callback.getStepperLayout().showProgress("Operation in progress, please wait...");

        new Handler().postDelayed(() -> {


            //you can do anythings you want
            callback.goToNextStep();
            //   callback.getStepperLayout().hideProgress();
        }, 100L);// delay open another fragment,
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

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

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
