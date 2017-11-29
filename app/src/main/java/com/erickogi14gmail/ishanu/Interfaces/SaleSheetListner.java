package com.erickogi14gmail.ishanu.Interfaces;

import com.erickogi14gmail.ishanu.Data.Models.ProductModel;

import java.util.LinkedList;

/**
 * Created by Eric on 11/21/2017.
 */

public interface SaleSheetListner {
    void onEditClicked(int pos, LinkedList<ProductModel> productModels);

    void onDeleteClicked(int pos, LinkedList<ProductModel> productModels);
}
