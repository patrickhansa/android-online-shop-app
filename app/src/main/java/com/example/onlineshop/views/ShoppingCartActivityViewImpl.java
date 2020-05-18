package com.example.onlineshop.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;

import com.example.onlineshop.controllers.ShoppingCartController;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.views.adapters.ProductAdapter;

import java.util.List;

public class ShoppingCartActivityViewImpl extends BaseViewEntity implements ShoppingCartActivityView, ListItemView.ListItemClickListener {

    private ShoppingCartController shoppingCartController;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    private Button btnContinueShopping, btnCheckout, btnStartShopping;
    private TextView tvEmptyShoppingCart;
    private LinearLayout primaryContainer, secondaryContainer;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public ShoppingCartActivityViewImpl(Context context, ViewGroup container) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_shopping_cart, container);
        this.shoppingCartController = new ShoppingCartController(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView = rootView.findViewById(R.id.productList);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnContinueShopping = rootView.findViewById(R.id.btnContinueShopping);
        btnCheckout = rootView.findViewById(R.id.btnCheckout);
        btnStartShopping = rootView.findViewById(R.id.btnStartShopping);
        tvEmptyShoppingCart = rootView.findViewById(R.id.tvEmptyShoppingCart);
        primaryContainer = rootView.findViewById(R.id.primaryContainer);
        secondaryContainer = rootView.findViewById(R.id.secondaryContainer);

        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        btnContinueShopping.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            navigateToProductListActivity();
        });

        btnCheckout.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            navigateToCheckoutActivity();
        });

        btnStartShopping.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            navigateToProductListActivity();
        });

        showProgress(true, mMainFormView, mProgressView, tvLoad);
    }

    @Override
    public void bindDataToView() {
        shoppingCartController.onViewLoaded();
    }

    @Override
    public void showProductsInShoppingCart(List<Product> products) {
        if (!products.isEmpty()) {
            secondaryContainer.setVisibility(View.GONE);
            tvEmptyShoppingCart.setVisibility(View.GONE);
            productAdapter = new ProductAdapter(rootView.getContext(), products, this);
            productAdapter.activateButtons(true);
            recyclerView.setAdapter(productAdapter);
        } else {
            primaryContainer.setVisibility(View.GONE);
        }

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }

    @Override
    public void onItemClicked(Long productId) {

    }

    @Override
    public void onRemoveClicked(Long productId) {
        shoppingCartController.removeProductFromCart(productId);
        showProgress(true, mMainFormView, mProgressView, tvLoad);
    }
}
