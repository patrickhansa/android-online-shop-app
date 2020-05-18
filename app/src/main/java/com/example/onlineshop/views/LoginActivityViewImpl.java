package com.example.onlineshop.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.LoginController;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivityViewImpl extends BaseViewEntity implements LoginActivityView {

    private LoginController loginController;

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private CheckBox cbRememberMe;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public LoginActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_login, container);
        this.loginController = new LoginController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        etUsername = rootView.findViewById(R.id.etUsername);
        etPassword = rootView.findViewById(R.id.etPassword);
        btnLogin = rootView.findViewById(R.id.btnLogin);
        btnRegister = rootView.findViewById(R.id.btnRegister);
        cbRememberMe = rootView.findViewById(R.id.cbRememberMe);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        btnLogin.setOnClickListener((view) -> {
            if (etUsername.getText().toString().isEmpty() ||
                    etPassword.getText().toString().isEmpty()) {
                showToast("Please enter all fields!");
            } else {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                showProgress(true, mMainFormView, mProgressView, tvLoad);

                loginController.onLoginButtonClicked(username, password);
            }
        });

        btnRegister.setOnClickListener((view) -> {
            navigateToRegisterActivity();
        });

        cbRememberMe.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                SharedPreferences preferences =
                        rootView.getContext().getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.apply();
            } else {
                SharedPreferences preferences =
                        rootView.getContext().getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
            }
        });
    }

    @Override
    public void bindDataToView() {

    }

    @Override
    public void checkRememberMe() {
        SharedPreferences globalPreferences =
                rootView.getContext().getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = globalPreferences.getString("remember", "");

        if (checkbox.equals("true")) {
            navigateToMainActivity();
        }
    }
}
