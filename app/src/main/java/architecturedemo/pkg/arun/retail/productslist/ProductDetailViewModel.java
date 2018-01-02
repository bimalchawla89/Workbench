package architecturedemo.pkg.arun.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;

import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.ProductsRepository;

public class ProductDetailViewModel extends AndroidViewModel {

    public final ObservableField<ProductData> task = new ObservableField<>();

    private final Context mContext;
    private final ProductsRepository mProductsRepository;


    public ProductDetailViewModel(
            Application context,
            ProductsRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mProductsRepository = repository;
    }

    public void getProductData(String productId) {
        mProductsRepository.getProductData(mContext, productId, new ProductsDataSource.GetProductDataCallback() {

            @Override
            public void onProductDataLoaded(ProductData productData) {
                task.set(productData);
            }

            @Override
            public void onFailure() {

            }
        });
    }

}