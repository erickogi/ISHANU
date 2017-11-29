package com.erickogi14gmail.ishanu.Views.Records;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erickogi14gmail.ishanu.Adapters.RecordsDetailAdapter;
import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Eric on 11/24/2017.
 */

public class Fragment_sales extends Fragment {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinkedList<ProductModel> productModels;
    private RecordsDetailAdapter recordsDetailAdapter;
    private DbOperations dbOperations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbOperations = new DbOperations(getContext());
        return inflater.inflate(R.layout.fragment_sales_records, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);

        recordsDetailAdapter = new RecordsDetailAdapter(getContext(), getProducts());

        recordsDetailAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recordsDetailAdapter);
    }

    private LinkedList<ProductModel> getProducts() {
        productModels = new LinkedList<>();
        String p = getArguments().getString("Sales");
        Gson gson = new Gson();

        Type collectionType1 = new TypeToken<Collection<ProductModel>>() {
        }.getType();
        Collection<ProductModel> enums = gson.fromJson(p, collectionType1);
        productModels.addAll(enums);
        return productModels;
    }
}
