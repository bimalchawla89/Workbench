package architecturedemo.pkg.arun.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import architecturedemo.pkg.arun.retail.ProductsActivity;
import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.ProductsRepository;
import architecturedemo.pkg.arun.retail.util.SingleLiveEvent;

/**
 * Created by Arun.Kumar04 on 12/26/2017.
 */

public class ProductViewModel extends AndroidViewModel {

    public final ObservableList<ProductData> items = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenProductEvent = new SingleLiveEvent<>();

    private final Context mContext;
    private final ProductsRepository mProductsRepository;


    public ProductViewModel(
            Application context,
            ProductsRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mProductsRepository = repository;
    }

    public void insertDummyData() {

    }

    public void getAllProducts() {
        mProductsRepository.getProductsList(mContext, new ProductsDataSource.GetProductsCallback() {

            @Override
            public void onProductsLoaded(ProductList productList) {
                items.clear();
                items.addAll(productList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public SingleLiveEvent<String> getOpenProductEvent() {
        return mOpenProductEvent;
    }
}
