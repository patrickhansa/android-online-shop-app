package com.example.onlineshop.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.activities.ProductInfo;
import com.example.onlineshop.controllers.ProductListController;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.views.adapters.ProductAdapter;

import java.util.List;

public class ProductListActivityViewImpl extends BaseViewEntity implements ProductListActivityView, ListItemView.ListItemClickListener {

    private ProductListController productListController;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public ProductListActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_product_list, container);
        this.productListController = new ProductListController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView = rootView.findViewById(R.id.productList);
        recyclerView.setLayoutManager(linearLayoutManager);

        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);
    }

    @Override
    public void bindDataToView() {
        productListController.onViewLoaded();
    }

    @Override
    public void showAllProducts(List<Product> products) {
        productAdapter = new ProductAdapter(rootView.getContext(), products, this);
        productAdapter.activateButtons(false);
        recyclerView.setAdapter(productAdapter);

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }

    @Override
    public void onItemClicked(Long productId) {

        Intent intent = new Intent(rootView.getContext(), ProductInfo.class);
        intent.putExtra("product_id", productId);
        rootView.getContext().startActivity(intent);
    }

    @Override
    public void onRemoveClicked(Long productId) {

    }
}
