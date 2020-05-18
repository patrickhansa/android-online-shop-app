package com.example.onlineshop.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.views.ListItemView;
import com.example.onlineshop.views.ProductListItemImpl;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements ListItemView.ListItemClickListener {

    private Context context;
    private List<Product> products;
    private ListItemView.ListItemClickListener listItemClickListener;
    private static boolean activate = false;

    public ProductAdapter(Context context, List<Product> products, ListItemView.ListItemClickListener listItemClickListener) {
        this.context = context;
        this.products = products;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductListItemImpl productListItem = new ProductListItemImpl(
                (LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)), parent);
        productListItem.initViews();
        productListItem.setListItemClickListener(this);
        return new ProductViewHolder(productListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.listItem.bindDataToView(product);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.list_products;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onItemClicked(Long productId) {
        listItemClickListener.onItemClicked(productId);
    }

    @Override
    public void onRemoveClicked(Long productId) {
        listItemClickListener.onRemoveClicked(productId);
    }

    public void activateButtons(boolean activate) {
        ProductAdapter.activate = activate;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ProductListItemImpl listItem;

        ProductViewHolder(ProductListItemImpl view) {
            super(view.getRootView());
            listItem = view;
            if (activate) {
                listItem.activateButtons(true);
            } else {
                listItem.activateButtons(false);
            }
        }
    }
}
