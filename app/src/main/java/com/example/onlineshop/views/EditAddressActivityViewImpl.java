package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.EditAddressController;
import com.example.onlineshop.model.User;

public class EditAddressActivityViewImpl extends BaseViewEntity implements EditAddressActivityView {

    private EditAddressController editAddressController;

    private EditText etFirstName, etLastName, etEmail, etPhoneNumber, etAddress;
    private Button btnCancel, btnSubmit;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public EditAddressActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_edit_address, container);
        this.editAddressController = new EditAddressController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        etFirstName = rootView.findViewById(R.id.etFirstName);
        etLastName = rootView.findViewById(R.id.etLastName);
        etEmail = rootView.findViewById(R.id.etEmail);
        etPhoneNumber = rootView.findViewById(R.id.etPhoneNumber);
        etAddress = rootView.findViewById(R.id.etAddress);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);

        btnCancel.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            navigateToCheckoutActivity();
        });

        btnSubmit.setOnClickListener((view) -> {

            if (    etFirstName.getText().toString().isEmpty() ||
                    etLastName.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty() ||
                    etPhoneNumber.getText().toString().isEmpty() ||
                    etAddress.getText().toString().isEmpty()        ) {

                showToast("Please enter all fields!");

            } else {
                showProgress(true, mMainFormView, mProgressView, tvLoad);

                editAddressController.onSubmitButtonClicked(
                        etFirstName.getText().toString().trim(),
                        etLastName.getText().toString().trim(),
                        etEmail.getText().toString().trim(),
                        etPhoneNumber.getText().toString().trim(),
                        etAddress.getText().toString().trim());
            }
        });
    }

    @Override
    public void bindDataToView() {
        editAddressController.onViewLoaded();
    }

    @Override
    public void updateViewOnLoad(User user) {

        etFirstName.setText(user.getFirstName());
        etLastName.setText(user.getLastName());
        etEmail.setText(user.getEmail());
        etPhoneNumber.setText(user.getPhone());
        etAddress.setText(user.getAddress());

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }
}
