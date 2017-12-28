package architecturedemo.pkg.arun.retail.productslist;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;
import architecturedemo.pkg.arun.retail.databinding.RowProductsListBinding;


public class ProductsListAdapter extends BaseAdapter {

    private ArrayList<Product> mProductsList;
    private final ProductViewModel mProductViewModel;

    public ProductsListAdapter(ArrayList<Product> products, ProductViewModel mProductViewModel) {
        this.mProductsList = products;
        this.mProductViewModel = mProductViewModel;
    }

    @Override
    public int getCount() {
        return null == mProductsList ? 0 : mProductsList.size();
    }

    @Override
    public Product getItem(int i) {
        return mProductsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RowProductsListBinding productsListBinding;
        if (null == view) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            productsListBinding = RowProductsListBinding.inflate(inflater, viewGroup, false);
        } else {
            productsListBinding = DataBindingUtil.getBinding(view);
        }

        productsListBinding.setProduct(mProductsList.get(i));
        productsListBinding.executePendingBindings();
        return productsListBinding.getRoot();
    }

    public void updateProductsList(List<Product> productsList) {
        mProductsList = new ArrayList<>(productsList);
        notifyDataSetChanged();
    }
}
