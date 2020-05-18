package com.example.onlineshop.views;

import android.view.View;

public interface ListItemView<T> {

    interface ListItemClickListener {
        void onItemClicked(Long productId);
        void onRemoveClicked(Long productId);
    }

    void setListItemClickListener(ListItemClickListener listener);

    View getRootView();
    void initViews();
    void bindDataToView(T object);
}
