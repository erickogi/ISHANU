package com.erickogi14gmail.ishanu.Views.Records;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.erickogi14gmail.ishanu.Adapters.RecordsAdapter;
import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Models.RecordModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.RecyclerTouchListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Eric on 11/23/2017.
 */

public class FrgamentRecords extends Fragment {
    LinkedList<RecordModel> recordModel;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private DbOperations dbOperations;
    private LinearLayout linearLayoutEmpty;
    private RecordsAdapter recordsAdapter;
    // private SearchView search;
    private Fragment fragment;
    private Button btnChooseDate;
    private int mYear, mMonth, mDay, mHour, mMinute, cl;


    public void datePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),

                new DatePickerDialog.OnDateSetListener() {


                    @Override

                    public void onDateSet(DatePicker view, int year,

                                          int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        //Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String date = simpleDateFormat.format(calendar.getTime());


                        //  btnChooseDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        btnChooseDate.setText(date);
                        recordModel = dbOperations.getAllRecords(date);

                        recordsAdapter.updateList(dbOperations.getAllRecords(date));


                    }

                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    public void setView(View view, Context context) {

        dbOperations = new DbOperations(context);
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutEmpty = view.findViewById(R.id.empty_layout);
        recordModel = dbOperations.getAllRecords("");
        recordsAdapter = new RecordsAdapter(getContext(), recordModel);
        recordsAdapter.notifyDataSetChanged();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recordsAdapter);

        if (recordsAdapter.getItemCount() > 0) {
            linearLayoutEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        } else {
            linearLayoutEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                fragment = new FragmentRecordDetails();
                Bundle args = new Bundle();
                args.putString("Sales", recordModel.get(position).getProducts());
                args.putString("Returns", recordModel.get(position).getRetutns());
                args.putSerializable("data", recordModel.get(position));
                fragment.setArguments(args);
                setUpView();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbOperations = new DbOperations(getContext());
        setView(view, getContext());
        btnChooseDate = view.findViewById(R.id.chooseDate);
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        // search = view.findViewById(R.id.search_bar);


    }


}
