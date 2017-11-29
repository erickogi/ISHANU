package com.erickogi14gmail.ishanu.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.erickogi14gmail.ishanu.Views.SalesForms.FragmentOne;
import com.erickogi14gmail.ishanu.Views.SalesForms.FragmentThree;
import com.erickogi14gmail.ishanu.Views.SalesForms.FragmentTwo;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Eric on 11/21/2017.
 */

public class MyStepperAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                final FragmentOne step1 = new FragmentOne();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);

                step1.setArguments(b1);
                return step1;
            case 1:
                final FragmentTwo step2 = new FragmentTwo();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                b2.putInt("type", 1);
                step2.setArguments(b2);
                return step2;
            case 2:
                final FragmentTwo step3 = new FragmentTwo();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                b3.putInt("type", 2);
                step3.setArguments(b3);
                return step3;
            case 3:
                final FragmentThree step4 = new FragmentThree();
                Bundle b4 = new Bundle();
                b4.putInt(CURRENT_STEP_POSITION_KEY, position);
                //b4.putInt("type",2);
                step4.setArguments(b4);
                return step4;


        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)

                        .setTitle("Start") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Sales") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Returns") //can be a CharSequence instead
                        .create();
            case 3:
                return new StepViewModel.Builder(context)
                        .setTitle("Finish") //can be a CharSequence instead
                        .create();

        }
        return null;
    }


}
