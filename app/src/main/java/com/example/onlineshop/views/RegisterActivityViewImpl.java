package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.RegisterController;

public class RegisterActivityViewImpl extends BaseViewEntity implements RegisterActivityView {

    private RegisterController registerController;

    private EditText etUsername, etEmail, etPassword;
    private Button btnRegister, btnCancel;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public RegisterActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_register, container);
        this.registerController = new RegisterController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        etUsername = rootView.findViewById(R.id.etUsername);
        etEmail = rootView.findViewById(R.id.etEmail);
        etPassword = rootView.findViewById(R.id.etPassword);
        btnRegister = rootView.findViewById(R.id.btnRegister);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        btnRegister.setOnClickListener((view) -> {

            if (etUsername.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty() ||
                    etPassword.getText().toString().isEmpty()) {

                showToast("Please enter all fields!");
            } else {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                showProgress(true, mMainFormView, mProgressView, tvLoad);

                registerController.onRegisterButtonClicked(username, password);
            }
        });

        btnCancel.setOnClickListener((view) -> navigateToLoginActivity());
    }

    @Override
    public void bindDataToView() {

    }
}
