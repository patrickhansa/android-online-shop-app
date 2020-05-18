package com.example.onlineshop.views;

import com.example.onlineshop.model.Product;

import java.util.List;

public interface ProductListActivityView extends BaseView {

    void showAllProducts(List<Product> products);
}
