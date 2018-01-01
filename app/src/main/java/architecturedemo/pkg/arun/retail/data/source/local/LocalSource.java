package architecturedemo.pkg.arun.retail.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
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
        // insertDummyData();
    }

    public static LocalSource getInstance(@NonNull AppExecutors appExecutors, @NonNull ProductsDao productsDao) {
        synchronized (LocalSource.class) {
            if (null == INSTANCE) {
                INSTANCE = new LocalSource(appExecutors, productsDao);
            }
            return INSTANCE;
        }
    }

//    private void insertDummyData() {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                mAppExecutors.diskIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<Product> productList = mProductsDao.getAllProducts();
//                        if (null == productList || productList.isEmpty()) {
//                            for (int i = 0; i < 20; i++) {
//                                Product product = new Product("" + (i + 1), "Product " + (i + 1), "description " + (i + 1), "");
//                                mProductsDao.insertProduct(product);
//                            }
//                        }
//                    }
//                });
//            }
//        };
//        mAppExecutors.diskIO().execute(runnable);
//
//    }

    @Override
    public void getProductsList(@NonNull final Context context, @NonNull final GetProductsCallback productsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ProductData> productList = mProductsDao.getAllProductsData();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (null != productList && !productList.isEmpty()) {
                            ProductList list = new ProductList();
                            list.setValue(productList);
                            productsCallback.onProductsLoaded(list);
                        } else {
                            productsCallback.onFailure();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getProductData(@NonNull final Context context, final String productId, @NonNull final GetProductDataCallback productsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final ProductData productData = mProductsDao.getProductById(productId);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (null != productData) {
                            productsCallback.onProductDataLoaded(productData);
                        } else {
                            productsCallback.onFailure();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveProducts(final ProductList productList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < productList.getValue().size(); i++) {
                    mProductsDao.insertProduct(productList.getValue().get(i));
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getToken(GetTokenCallback getTokenCallback) {

    }
}
