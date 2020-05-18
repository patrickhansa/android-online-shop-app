package com.example.onlineshop.views;

import com.example.onlineshop.model.Product;

public interface ProductInfoActivityView extends BaseView {

    void updateViewOnLoad(Product product);
}
