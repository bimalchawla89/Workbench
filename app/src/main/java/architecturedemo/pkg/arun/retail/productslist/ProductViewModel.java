package architecturedemo.pkg.arun.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import architecturedemo.pkg.arun.retail.data.source.AppDataSource;
import architecturedemo.pkg.arun.retail.data.source.AppRepository;
import architecturedemo.pkg.arun.retail.util.SingleLiveEvent;


public class ProductViewModel extends AndroidViewModel {

    public final ObservableList<ProductData> items = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenProductEvent = new SingleLiveEvent<>();

    private final Context mContext;
    private final AppRepository mProductsRepository;


    public ProductViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mProductsRepository = repository;
    }

    public void insertDummyData() {

    }

    public void getAllProducts(String productId) {
        mProductsRepository.getProductsList(mContext, productId, new AppDataSource.GetProductsCallback() {

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
