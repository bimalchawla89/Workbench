package architecturedemo.pkg.arun.retail.productslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import architecturedemo.pkg.arun.retail.ProductsActivity;
import architecturedemo.pkg.arun.retail.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {

    public static final String ARGUMENT_PRODUCT_ID = "PRODUCT_ID";
    private ProductDetailViewModel mProductDetailViewModel;

    public static ProductDetailFragment newInstance(String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PRODUCT_ID, taskId);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProductDetailViewModel.getProductData(getArguments().getString(ARGUMENT_PRODUCT_ID));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProductDetailBinding mFragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false);
        mProductDetailViewModel = ProductsActivity.obtainViewModel(getActivity());

        mFragmentProductDetailBinding.setModel(mProductDetailViewModel);
        AddToCartActionListener addToCartActionListener = new AddToCartActionListener() {
            @Override
            public void onAddToCartClicked() {
                mProductDetailViewModel.addToCart();
            }
        };
        mFragmentProductDetailBinding.setListener(addToCartActionListener);
        mFragmentProductDetailBinding.executePendingBindings();

        return mFragmentProductDetailBinding.getRoot();
    }

}
