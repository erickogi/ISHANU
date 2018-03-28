package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.erickogi14gmail.ishanu.Adapters.SearchProductAdapter;
import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.RecyclerTouchListener;

import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class DialogSearch extends android.support.v4.app.DialogFragment {


    private DbOperations dbOperations;

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private SearchProductAdapter searchProductAdapter;
    private int type = 1;

    public DialogSearch() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DialogSearch newInstance(String title, int type) {
        DialogSearch frag = new DialogSearch();
        Bundle args = new Bundle();
        // args.putSerializable("data", productModels);
        args.putString("title", title);
        args.putInt("type", type);
        frag.setArguments(args);
        return frag;
    }

    public void sendBackResult(ProductModel model) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        DialogSearchListener listener = (DialogSearchListener) getTargetFragment();
        listener.onSelected(model);
        dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        // LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // View layout = inflater.inflate(R.layout.your_dialog_layout, null);
        View view = inflater.inflate(R.layout.dialog_search_item, container);
        view.setMinimumWidth((int) (displayRectangle.width() * 0.8f));
        view.setMinimumHeight((int) (displayRectangle.height() * 0.8f));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbOperations = new DbOperations(getActivity());
        type = getArguments().getInt("type");

        // Get field from view
        recyclerView = view.findViewById(R.id.recycler_view);
        LinkedList<ProductModel> productModels = dbOperations.getAllItems("", type);
        for (ProductModel productModel : productModels) {
            Log.d("dla", "" + productModel.getProduct_name());
        }


        searchProductAdapter = new SearchProductAdapter(productModels, getActivity(), type);

        searchProductAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchProductAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        sendBackResult(productModels.get(position));
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Search");
        getDialog().setTitle(title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

            final SearchView search = view.findViewById(R.id.search_bar);
            search.setOnClickListener(v -> search.setIconified(false));

            search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


                @Override
                public boolean onQueryTextSubmit(String query) {


                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    try {


                        searchProductAdapter.updateList(dbOperations.getAllItems(newText, type));


                    } catch (Exception m) {

                    }
                    return false;
                }
            });

        }
    }

    public interface DialogSearchListener {
        void onSelected(ProductModel model);
    }


}
