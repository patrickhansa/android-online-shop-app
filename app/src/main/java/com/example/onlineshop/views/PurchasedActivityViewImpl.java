package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onlineshop.R;

public class PurchasedActivityViewImpl extends BaseViewEntity implements BaseView {

    private Button btnContinueShopping;

    public PurchasedActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_purchased, container);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        btnContinueShopping = rootView.findViewById(R.id.btnContinueShopping);

        btnContinueShopping.setOnClickListener((view) -> navigateToProductListActivity());
    }

    @Override
    public void bindDataToView() {
    }
}
