package architecturedemo.pkg.arun.retail.productslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.data.models.Product;
import architecturedemo.pkg.arun.retail.databinding.FragmentProductListBinding;

/**
 * A fragment representing a list of Items.
 */
public class ProductsFragment extends Fragment {

    private ProductViewModel mProductViewModel;

    private FragmentProductListBinding mProductListBinding;

    private ProductsListAdapter mListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsFragment() {
    }

    @SuppressWarnings("unused")
    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductListBinding = FragmentProductListBinding.inflate(inflater, container, false);
        mProductViewModel = HomeActivity.obtainViewModel(getActivity());
        mProductListBinding.setModel(mProductViewModel);
        return mProductListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupListAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mProductViewModel.getAllProducts();
    }

    private void setupListAdapter() {
        ListView productListView =  mProductListBinding.lvProducts;

        mListAdapter = new ProductsListAdapter(
                new ArrayList<Product>(0),
                mProductViewModel
        );
        productListView.setAdapter(mListAdapter);
    }
}
