package arch.wb.retail.productslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;


public class ProductViewModel extends AndroidViewModel {

    public final MutableLiveData<List<ProductData>> productsList = new MutableLiveData<>();
    private final SingleLiveEvent<String> mOpenProductEvent = new SingleLiveEvent<>();

    private final AppRepository mProductsRepository;


    public ProductViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mProductsRepository = repository;
    }

    public void getAllProducts(Context context, String subCategory) {
        mProductsRepository.getProductsListFromSubCategory(context.getApplicationContext(), subCategory, new AppDataSource.GetProductsCallback() {

            @Override
            public void onProductsLoaded(ProductList productList) {
                productsList.setValue(productList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public SingleLiveEvent<String> getOpenProductEvent() {
        return mOpenProductEvent;
    }

    public LiveData<List<ProductData>> getProductsData() {
        return productsList;
    }
}
