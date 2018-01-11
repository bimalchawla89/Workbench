package arch.wb.retail.productslist;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.databinding.RowProductsListBinding;


public class ProductsListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ProductData> mProductsList;
    private final ProductViewModel mProductViewModel;

    public ProductsListAdapter(Context context, ArrayList<ProductData> products, ProductViewModel mProductViewModel) {
        mContext = context;
        this.mProductsList = products;
        this.mProductViewModel = mProductViewModel;
    }

    @Override
    public int getCount() {
        return null == mProductsList ? 0 : mProductsList.size();
    }

    @Override
    public ProductData getItem(int i) {
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

        ProductItemActionsListener userActionsListener = new ProductItemActionsListener() {
            @Override
            public void onProductClicked(ProductData productData) {
                mProductViewModel.getOpenProductEvent().setValue(productData.getId());
            }
        };

//        Picasso.with(mContext).load(mProductsList.get(i).getImageDomain() + mProductsList.get(i).getImageSuffix())
//                .into(productsListBinding.imageViewProduct);

        productsListBinding.setListener(userActionsListener);
        productsListBinding.setProduct(mProductsList.get(i));
        productsListBinding.setLifecycleOwner((ProductsActivity)mContext);
        productsListBinding.executePendingBindings();

        return productsListBinding.getRoot();
    }

    public void updateProductsList(List<ProductData> productsList) {
        mProductsList = new ArrayList<>(productsList);
        notifyDataSetChanged();
    }
}
