package architecturedemo.pkg.arun.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.ProductsRepository;

/**
 * Created by Arun.Kumar04 on 12/26/2017.
 */

public class ProductViewModel extends AndroidViewModel {

    public final ObservableList<Product> items = new ObservableArrayList<>();

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
        mProductsRepository.getProductsList(new ProductsDataSource.GetProductsCallback() {
            @Override
            public void onProductsLoaded(List<Product> productList) {
                items.clear();
                items.addAll(productList);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
