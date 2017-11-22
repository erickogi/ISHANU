package com.erickogi14gmail.ishanu.Views.SalesForms;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.erickogi14gmail.ishanu.Adapters.SaleSheetAdapter;
import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.Interfaces.SaleSheetListner;
import com.erickogi14gmail.ishanu.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public class FragmentTwoR extends Fragment implements BlockingStep, DialogSearch.DialogSearchListener {
    int type = 1;
    private Button buttonAdd;
    private Dialog dialog;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinkedList<ProductModel> productModels;
    private SaleSheetAdapter saleSheetAdapter;
    private TextView txtTotalPrice, txtTotalQuantity;
    private PrefrenceManager prefrenceManager;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        initD();

        buttonAdd = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.recycler_view);
        txtTotalPrice = view.findViewById(R.id.total_price);
        txtTotalQuantity = view.findViewById(R.id.total_quantity);

        buttonAdd.setOnClickListener(v -> showEditDialog());


        intViews();
        return view;

    }


    private void initD() {
        type = getArguments().getInt("type");

        prefrenceManager = new PrefrenceManager(getContext());
        productModels = new LinkedList<>();

        if (!prefrenceManager.getReturns()[1].equals("null")) {
            String products = prefrenceManager.getReturns()[0];
            Gson gson = new Gson();
            Type collectionType1 = new TypeToken<Collection<ProductModel>>() {
            }.getType();
            Collection<ProductModel> enums = gson.fromJson(products, collectionType1);
            productModels.addAll(enums);
        }

    }

    private void intViews() {

        saleSheetAdapter = new SaleSheetAdapter(getContext(), productModels, new SaleSheetListner() {
            @Override
            public void onEditClicked(int pos) {

                quantityDialog(productModels.get(pos), 2, pos, productModels.get(pos).getProduct_sale_quantity());

            }

            @Override
            public void onDeleteClicked(int pos) {


                alertDialogDelete("Please Confirm the action to delete this item :> " + productModels.get(pos).getProduct_name(), pos);

            }
        });

        saleSheetAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(saleSheetAdapter);
        setTotals();


    }

    private double calculateTotalPrice() {
        double price = 0;
        for (ProductModel model : productModels) {
            price = price + (model.getProduct_price() * model.getProduct_sale_quantity());

        }
        return price;
    }

    private double calculateTotalQuantity() {
        double quantity = 0;
        for (ProductModel model : productModels) {
            quantity = quantity + (model.getProduct_sale_quantity());

        }
        return quantity;
    }

    private void setTotals() {
        if (saleSheetAdapter.getItemCount() > 1) {
            txtTotalQuantity.setText(String.valueOf(calculateTotalQuantity()));
            txtTotalPrice.setText(String.valueOf(calculateTotalPrice()));
        } else {
            txtTotalQuantity.setText(String.valueOf(0.0));
            txtTotalPrice.setText(String.valueOf(0.0));
        }
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.getStepperLayout().showProgress("Operation in progress, please wait...");

        new Handler().postDelayed(() -> {

            // if(Double.valueOf(txtTotalPrice.getText().toString())>0.0){
            Gson gson = new Gson();
            String data = gson.toJson(productModels);

            prefrenceManager.storeReturns(data, txtTotalPrice.getText().toString());
            Log.d("saveddd", data);
            //Toast.makeText(getContext(), "saved"+data, Toast.LENGTH_SHORT).show();

            //   }


            //you can do anythings you want
            callback.goToNextStep();
            callback.getStepperLayout().hideProgress();
        }, 0L);// delay open another fragment,
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {


    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        new Handler().postDelayed(() -> {

            if (Double.valueOf(txtTotalPrice.getText().toString()) > 0.0) {
                Gson gson = new Gson();
                String data = gson.toJson(productModels);

                prefrenceManager.storeReturns(data, txtTotalPrice.getText().toString());

            }

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

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        DialogSearch dialogSearch = DialogSearch.newInstance("Search", type);
        // dialogSearch.show(fm,"dialog");

        dialogSearch.setTargetFragment(FragmentTwoR.this, 300);
        dialogSearch.show(fm, "fragment_search");
    }

    private void quantityDialog(ProductModel model, int requestCode, @Nullable int pos, double initialQuantity) {
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
        Button btnD = dialog.findViewById(R.id.btn_dismiss);
        Button btnU = dialog.findViewById(R.id.btn_update);


        TextView prouctName = dialog.findViewById(R.id.product_name);
        TextView productPrice = dialog.findViewById(R.id.product_price);

        edtQ.setText(String.valueOf(initialQuantity));
        prouctName.setText(model.getProduct_name());
        productPrice.setText(String.valueOf(model.getProduct_price()) + " Ksh");


        edtQ.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try {
                    if (Double.valueOf(s.toString()) > 0.0) {
                        double newPrice = Double.valueOf(s.toString()) * model.getProduct_price();
                        productPrice.setText(String.valueOf(newPrice));
                    } else {
                        //edtQ.setError("Invalid Value");
                        edtQ.setText(String.valueOf(initialQuantity));
                    }
                } catch (Exception nm) {

                }


            }
        });

        btnP.setOnClickListener(v -> {
            Double quantityNow = Double.valueOf(edtQ.getText().toString());
            edtQ.setText(String.valueOf(quantityNow + 1));

        });


        btnM.setOnClickListener(v -> {
            Double quantityNow = Double.valueOf(edtQ.getText().toString());
            if (quantityNow > 1) edtQ.setText(String.valueOf(quantityNow - 1));
        });

        btnD.setOnClickListener(v -> dialog.dismiss());


        btnU.setOnClickListener(v -> {
            ProductModel productModel = new ProductModel();
            productModel.setProduct_name(model.getProduct_name());
            productModel.setProduct_price(model.getProduct_price());
            productModel.setProduct_sale_quantity(Double.valueOf(edtQ.getText().toString()));
            productModel.setProduct_load_quantity(model.getProduct_load_quantity());
            if (requestCode == 1) {
                populateList(productModel);
            } else {
                updateItem(productModel, pos);
            }
            dialog.dismiss();
        });


        dialog.show();
        // Toast.makeText(getContext(), ""+model.getProduct_name(), Toast.LENGTH_SHORT).show();
    }

    private void updateItem(ProductModel model, int pos) {
        productModels.get(pos).setProduct_price(model.getProduct_price());
        productModels.get(pos).setProduct_sale_quantity(model.getProduct_sale_quantity());
        saleSheetAdapter.updateItemItem(pos, model);
        setTotals1();
    }

    @Override
    public void onSelected(ProductModel model) {
        quantityDialog(model, 1, 0, 1.0);
    }

    private void populateList(ProductModel model) {
        productModels.add(model);
        saleSheetAdapter.updateList(productModels);
        setTotals1();

    }

    private void setTotals1() {
        try {
            txtTotalQuantity.setText(String.valueOf(calculateTotalQuantity()));
            txtTotalPrice.setText(String.valueOf(calculateTotalPrice()));
        } catch (Exception nm) {
            nm.printStackTrace();
        }

    }

    private void alertDialogDelete(final String message, final int id) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    productModels.remove(id);
                    saleSheetAdapter.updateList(productModels);
                    setTotals1();

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();


                    break;
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(message).setPositiveButton("Confirm", dialogClickListener)
                .setNegativeButton("Dismiss", dialogClickListener).show();

    }

}
