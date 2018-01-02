package architecturedemo.pkg.arun.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.ProductsRepository;
import architecturedemo.pkg.arun.retail.util.SingleLiveEvent;

public class ProductDetailViewModel extends AndroidViewModel {

    public final ObservableField<ProductData> task = new ObservableField<>();
    private final SingleLiveEvent<Void> mAddToCartEvent = new SingleLiveEvent<>();

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

    public void addToCart() {
        mAddToCartEvent.call();
    }

    public SingleLiveEvent<Void> addToCartEvent() {
        return mAddToCartEvent;
    }

}