package com.example.onlineshop.views;

import com.example.onlineshop.model.Product;

import java.util.List;

public interface ShoppingCartActivityView extends BaseView {

    void showProductsInShoppingCart(List<Product> products);
}
