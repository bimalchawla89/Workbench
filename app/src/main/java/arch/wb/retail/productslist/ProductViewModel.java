package arch.wb.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;


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

    public void getAllProducts(String subCategory) {
        mProductsRepository.getProductsListFromSubCategory(mContext, subCategory, new AppDataSource.GetProductsCallback() {

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
