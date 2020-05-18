package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.EditAccountController;
import com.example.onlineshop.model.User;

public class EditAccountActivityViewImpl extends BaseViewEntity implements EditAccountActivityView {

    private EditAccountController editAccountController;

    private EditText etFirstName, etLastName, etEmail, etPhoneNumber, etPassword, etBirthDate, etGender, etAddress;
    private Button btnCancel, btnSubmit;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public EditAccountActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_edit_account, container);
        this.editAccountController = new EditAccountController(this);
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
        etPassword = rootView.findViewById(R.id.etPassword);
        etBirthDate = rootView.findViewById(R.id.etBirthDate);
        etGender = rootView.findViewById(R.id.etGender);
        etAddress = rootView.findViewById(R.id.etAddress);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);

        btnCancel.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            navigateToAccountInfoActivity();
        });

        btnSubmit.setOnClickListener((view) -> {

            if (    etFirstName.getText().toString().isEmpty() ||
                    etLastName.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty() ||
                    etPhoneNumber.getText().toString().isEmpty() ||
                    etPassword.getText().toString().isEmpty() ||
                    etBirthDate.getText().toString().isEmpty() ||
                    etGender.getText().toString().isEmpty() ||
                    etAddress.getText().toString().isEmpty()        ) {

                showToast("Please enter all fields!");

            } else {
                showProgress(true, mMainFormView, mProgressView, tvLoad);

                editAccountController.onSubmitButtonClicked(
                        etFirstName.getText().toString().trim(),
                        etLastName.getText().toString().trim(),
                        etEmail.getText().toString().trim(),
                        etPhoneNumber.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        etBirthDate.getText().toString().trim(),
                        etGender.getText().toString().trim(),
                        etAddress.getText().toString().trim());
            }
        });
    }

    @Override
    public void bindDataToView() {
        editAccountController.onViewLoaded();
    }

    @Override
    public void updateViewOnLoad(User user) {

        etFirstName.setText(user.getFirstName());
        etLastName.setText(user.getLastName());
        etEmail.setText(user.getEmail());
        etPhoneNumber.setText(user.getPhone());
        etBirthDate.setText(user.getBirthDate());
        etGender.setText(user.getGender());
        etAddress.setText(user.getAddress());

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }
}
