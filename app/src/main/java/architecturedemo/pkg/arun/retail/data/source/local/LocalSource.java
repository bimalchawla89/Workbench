package architecturedemo.pkg.arun.retail.data.source.local;

import android.support.annotation.NonNull;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.util.AppExecutors;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public class LocalSource implements ProductsDataSource {

    private static volatile LocalSource INSTANCE;
    private AppExecutors mAppExecutors;
    private ProductsDao mProductsDao;

    private LocalSource(AppExecutors appExecutors, ProductsDao productsDao) {
        mAppExecutors = appExecutors;
        mProductsDao = productsDao;
        insertDummyData();
    }

    public static LocalSource getInstance(@NonNull AppExecutors appExecutors, @NonNull ProductsDao productsDao) {
        synchronized (LocalSource.class) {
            if (null == INSTANCE) {
                INSTANCE = new LocalSource(appExecutors, productsDao);
            }
            return INSTANCE;
        }
    }

    private void insertDummyData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Product> productList = mProductsDao.getAllProducts();
                        if (null == productList || productList.isEmpty()) {
                            for (int i = 0; i < 20; i++) {
                                Product product = new Product("" + (i + 1), "Product " + (i + 1), "description " + (i + 1), "");
                                mProductsDao.insertProduct(product);
                            }
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void getProductsList(@NonNull final GetProductsCallback productsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Product> productList = mProductsDao.getAllProducts();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (null != productList && !productList.isEmpty()) {
                            productsCallback.onProductsLoaded(productList);
                        } else {
                            productsCallback.onFailure();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
