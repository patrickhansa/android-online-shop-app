package com.example.onlineshop.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.controllers.ProductInfoController;
import com.example.onlineshop.model.Product;

public class ProductInfoActivityViewImpl extends BaseViewEntity implements ProductInfoActivityView {

    private ProductInfoController productInfoController;
    private long displayedProductId;

    private ImageView ivProductImage;
    private TextView tvProductName, tvProductDescription, tvProductStock, tvProductPrice;
    private Button btnCancel, btnAddToCart;

    public View mMainFormView;
    public View mProgressView;
    public TextView tvLoad;

    public ProductInfoActivityViewImpl(Context context, ViewGroup container, long displayedProductId) {
        super.rootView = LayoutInflater.from(context).inflate(R.layout.activity_product_info, container);
        this.productInfoController = new ProductInfoController(this);
        this.displayedProductId = displayedProductId;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        ivProductImage = rootView.findViewById(R.id.ivProductImage);
        tvProductName = rootView.findViewById(R.id.tvProductName);
        tvProductDescription = rootView.findViewById(R.id.tvProductDescription);
        tvProductStock = rootView.findViewById(R.id.tvProductStock);
        tvProductPrice = rootView.findViewById(R.id.tvProductPrice);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnAddToCart = rootView.findViewById(R.id.btnAddToCart);
        mMainFormView = rootView.findViewById(R.id.main_form);
        mProgressView = rootView.findViewById(R.id.loading_progress);
        tvLoad = rootView.findViewById(R.id.tvLoad);

        showProgress(true, mMainFormView, mProgressView, tvLoad);

        btnAddToCart.setOnClickListener((view) -> {
            showProgress(true, mMainFormView, mProgressView, tvLoad);
            productInfoController.onAddToCartClicked(displayedProductId);

            navigateToShoppingCartActivity();
        });

        btnCancel.setOnClickListener((view) -> navigateToProductListActivity());
    }

    @Override
    public void bindDataToView() {
        productInfoController.onViewLoaded(displayedProductId);
    }

    @Override
    public void updateViewOnLoad(Product product) {
        int stock = Integer.parseInt(product.getStock());
        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);

        ivProductImage.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
        tvProductName.setText(product.getName());
        tvProductDescription.setText(product.getDescription());
        tvProductStock.setText(stock > 0 ? "In stock" : "Out of stock");
        tvProductStock.setTextColor(stock > 0 ? Color.parseColor("#28a745") : Color.parseColor("#dc3545"));
        tvProductPrice.setText(String.format("Price: %s $", product.getPrice()));

        showProgress(false, mMainFormView, mProgressView, tvLoad);
    }
}
