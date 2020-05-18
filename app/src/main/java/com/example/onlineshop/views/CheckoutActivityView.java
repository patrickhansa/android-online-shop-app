package com.example.onlineshop.views;

import com.example.onlineshop.model.User;

public interface CheckoutActivityView extends BaseView {

    void updateViewOnLoad(User user, Float totalPrice);
}
