package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.CheckoutController;
import com.example.onlineshop.model.User;

public class CheckoutActivityViewImpl extends BaseViewEntity implements CheckoutActivityView {

    private CheckoutController checkoutController;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    private TextView tvName, tvEmail, tvPhone, tvAddress, tvTotalPrice;
    private Button btnChangeAddress, btnPurchase;

    public CheckoutActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_checkout, container);
        this.checkoutController = new CheckoutController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        tvName = rootView.findViewById(R.id.tvName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvPhone = rootView.findViewById(R.id.tvPhone);
        tvAddress = rootView.findViewById(R.id.tvAddress);
        tvTotalPrice = rootView.findViewById(R.id.tvTotalPrice);
        btnChangeAddress = rootView.findViewById(R.id.btnChangeAddress);
        btnPurchase = rootView.findViewById(R.id.btnPurchase);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);

        btnChangeAddress.setOnClickListener((view) -> navigateToEditAddressActivity());

        btnPurchase.setOnClickListener((view) -> navigateToPurchasedActivity());
    }

    @Override
    public void bindDataToView() {
        checkoutController.onViewLoaded();
    }

    @Override
    public void updateViewOnLoad(User user, Float totalPrice) {
        String name = "Name: " + user.getFirstName() + " " + user.getLastName();
        String email = "Email: " + user.getEmail();
        String phone = "Phone: " + user.getPhone();
        String address = "Address: " + user.getAddress();
        String price = "Price: " + totalPrice + " $";

        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvAddress.setText(address);
        tvTotalPrice.setText(price);

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }
}
