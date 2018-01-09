package arch.wb.retail.productdetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;

public class ProductDetailViewModel extends AndroidViewModel {

    public final ObservableField<ProductData> task = new ObservableField<>();
    public final ObservableBoolean addedToCart = new ObservableBoolean();
    private final SingleLiveEvent<Void> mAddToCartEvent = new SingleLiveEvent<>();

    private final Context mContext;
    private final AppRepository mAppRepository;


    public ProductDetailViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mAppRepository = repository;
    }

    public void getProductDetails(String productId) {
        mAppRepository.getProductDetails(mContext, productId, new AppDataSource.GetProductDataCallback() {

            @Override
            public void onProductDataLoaded(ProductData productData) {
                task.set(productData);
            }

            @Override
            public void onFailure() {
                //failing
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