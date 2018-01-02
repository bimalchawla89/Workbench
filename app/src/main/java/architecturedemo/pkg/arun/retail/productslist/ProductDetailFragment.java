package architecturedemo.pkg.arun.retail.productslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.ProductsActivity;
import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {

    public static final String ARGUMENT_PRODUCT_ID = "PRODUCT_ID";

    private FragmentProductDetailBinding mFragmentProductDetailBinding;
    private ProductDetailViewModel mProductDetailViewModel;

    public static ProductDetailFragment newInstance(String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PRODUCT_ID, taskId);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        mFragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false);
        mProductDetailViewModel = ProductsActivity.obtainViewModel(getActivity());
        mFragmentProductDetailBinding.setModel(mProductDetailViewModel);
        return mFragmentProductDetailBinding.getRoot();
    }

//    private TaskDetailUserActionsListener getTaskDetailUserActionsListener() {
//        return new TaskDetailUserActionsListener() {
//            @Override
//            public void onCompleteChanged(View v) {
//                mViewModel.setCompleted(((CheckBox) v).isChecked());
//            }
//        };
//    }

}
