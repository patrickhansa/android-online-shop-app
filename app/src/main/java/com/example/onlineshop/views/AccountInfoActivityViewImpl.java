package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.AccountInfoController;
import com.example.onlineshop.model.User;


public class AccountInfoActivityViewImpl extends BaseViewEntity implements AccountInfoActivityView {

    private AccountInfoController accountInfoController;

    private TextView tvFullName, tvEmail, tvPhoneNumber, tvBirthDate, tvGender, tvAddress;
    private Button btnEdit, btnDelete;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public AccountInfoActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_account_info, container);
        this.accountInfoController = new AccountInfoController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        tvFullName = rootView.findViewById(R.id.tvFullName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvPhoneNumber = rootView.findViewById(R.id.tvPhoneNumber);
        tvBirthDate = rootView.findViewById(R.id.tvBirthDate);
        tvGender = rootView.findViewById(R.id.tvGender);
        tvAddress = rootView.findViewById(R.id.tvAddress);
        btnEdit = rootView.findViewById(R.id.btnEdit);
        btnDelete = rootView.findViewById(R.id.btnDelete);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);

        btnEdit.setOnClickListener((view) -> navigateToEditAccountActivity());

        btnDelete.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            accountInfoController.onDeleteButtonClicked();
        });
    }

    @Override
    public void bindDataToView() {
        accountInfoController.onViewLoaded();
    }


    @Override
    public void updateViewOnLoad(User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();

        tvFullName.setText(fullName);
        tvEmail.setText(user.getEmail());
        tvPhoneNumber.setText(user.getPhone());
        tvBirthDate.setText(user.getBirthDate());
        tvGender.setText(user.getGender());
        tvAddress.setText(user.getAddress());

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }
}
