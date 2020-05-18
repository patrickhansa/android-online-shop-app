package com.example.onlineshop.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;

public class ProductListItemImpl implements ListItemView<Product> {

    private View rootView;
    private Product product;

    public TextView tvTitle, tvRemove;

    private ListItemClickListener listItemClickListener;
    private static boolean activate = false;

    public ProductListItemImpl(LayoutInflater layoutInflater, ViewGroup parent) {
        rootView = layoutInflater.inflate(R.layout.list_products , parent, false);
    }

    @Override
    public void setListItemClickListener(ListItemClickListener listener) {
        this.listItemClickListener = listener;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        tvTitle = rootView.findViewById(R.id.tvProductName);
        tvRemove = rootView.findViewById(R.id.tvRemove);
    }

    @Override
    public void bindDataToView(Product object) {
        this.product = object;
        tvTitle.setText(this.product.getName());
        tvTitle.setOnClickListener((view) -> {
            if (listItemClickListener != null) {
                listItemClickListener.onItemClicked(product.getId());
            }
        });
        if (activate) {
            tvRemove.setVisibility(View.VISIBLE);
        }
        tvRemove.setOnClickListener((view) -> {
            if (listItemClickListener != null) {
                listItemClickListener.onRemoveClicked(product.getId());
            }
        });
    }

    public void activateButtons(boolean activate) {
        ProductListItemImpl.activate = activate;
    }
}
