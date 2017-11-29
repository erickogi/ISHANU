package com.erickogi14gmail.ishanu.Views.Records;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Data.Models.RecordModel;
import com.erickogi14gmail.ishanu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 11/23/2017.
 */

public class FragmentRecordDetails extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txtCustomer, txtDate, txtDue, txtPaid, txtBalance, txtMpesa, txtCash, txtCheque, txtSales, txtReturns;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_records_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCustomer = view.findViewById(R.id.txt_customer);
        txtDate = view.findViewById(R.id.txt_date);
        txtDue = view.findViewById(R.id.txt_due);
        txtPaid = view.findViewById(R.id.txt_paid);
        txtBalance = view.findViewById(R.id.txt_balance);
        txtMpesa = view.findViewById(R.id.txt_mpesa);
        txtCash = view.findViewById(R.id.txt_cash);
        txtCheque = view.findViewById(R.id.txt_cheque);
        txtSales = view.findViewById(R.id.txt_sales);
        txtReturns = view.findViewById(R.id.txt_returns);

        RecordModel recordModel = (RecordModel) getArguments().getSerializable("data");

        txtCustomer.setText(recordModel.getCustomer_name());
        txtDate.setText(recordModel.getDate());
        txtDue.setText(String.valueOf(Double.valueOf(recordModel.getProduct_total())
                - Double.valueOf(recordModel.getReturns_total())));
        txtPaid.setText(recordModel.getPaid());
        txtBalance.setText(recordModel.getBalance());
        txtMpesa.setText(recordModel.getMpesa());
        txtCash.setText(recordModel.getCash());
        txtCheque.setText(recordModel.getCheque());
        txtSales.setText(recordModel.getProduct_total());
        txtReturns.setText(recordModel.getReturns_total());


        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment_sales fragment_sales = new Fragment_sales();
        FragmentReturns fragmentReturns = new FragmentReturns();

        Bundle args = new Bundle();
        args.putString("Sales", getArguments().getString("Sales"));
        args.putString("Returns", getArguments().getString("Returns"));


        fragment_sales.setArguments(args);
        fragmentReturns.setArguments(args);


        adapter.addFragment(fragment_sales, "lo");
        adapter.addFragment(fragmentReturns, "li");


        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);

    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setText("Sales");
        tabLayout.getTabAt(1).setText("Returns");


    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
