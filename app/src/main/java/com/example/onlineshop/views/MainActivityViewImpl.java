package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onlineshop.R;

public class MainActivityViewImpl extends BaseViewEntity implements MainActivityView {

    private Button btnStartShopping;

    public MainActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_main, container);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        btnStartShopping = rootView.findViewById(R.id.btnStartShopping);

        btnStartShopping.setOnClickListener((view) -> navigateToProductListActivity());
    }

    @Override
    public void bindDataToView() {
    }
}
