package architecturedemo.pkg.arun.retail.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import architecturedemo.pkg.arun.retail.data.source.local.AppPreferences;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public class ProductsRepository implements ProductsDataSource {

    private static ProductsRepository INSTANCE = null;

    private final ProductsDataSource mLocalDataSource;
    private final ProductsDataSource mRemoteDataSource;

    private ProductsRepository(ProductsDataSource localDataSource, ProductsDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static ProductsRepository getInstance(ProductsDataSource localDataSource, ProductsDataSource remoteDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new ProductsRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getProductsList(@NonNull Context context, GetProductsCallback callback) {
        getProductsFromLocalSource(context, callback);
    }

    @Override
    public void getProductData(@NonNull Context context, String productId, GetProductDataCallback callback) {
        getProductDataFromLocalSource(context, productId, callback);
    }

    @Override
    public void saveProducts(ProductList productList) {
        mLocalDataSource.saveProducts(productList);
    }

    @Override
    public void getToken(GetTokenCallback getTokenCallback) {
        mRemoteDataSource.getToken(getTokenCallback);
    }

    private void getProductsFromLocalSource(@NonNull final Context context, @NonNull final GetProductsCallback callback) {
        mLocalDataSource.getProductsList(context, new GetProductsCallback() {
            @Override
            public void onProductsLoaded(ProductList productList) {
                if (null == productList || null == productList.getValue() || productList.getValue().isEmpty()) {
                    getProductsFromRemoteSource(context, callback);
                } else {
                    callback.onProductsLoaded(productList);
                }
            }

            @Override
            public void onFailure() {
                getProductsFromRemoteSource(context, callback);
            }
        });
    }

    private void getProductDataFromLocalSource(@NonNull final Context context, final String productId, @NonNull final GetProductDataCallback callback) {
        mLocalDataSource.getProductData(context, productId, new GetProductDataCallback() {
            @Override
            public void onProductDataLoaded(ProductData productData) {
//                if (null == productData) {
//                    getProductsFromRemoteSource(context, callback);
//                } else {
                    callback.onProductDataLoaded(productData);
//                }
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

    private void getProductsFromRemoteSource(@NonNull final Context context, @NonNull final GetProductsCallback callback) {
        /*if (null == AppPreferences.getToken(context)) {
            getToken(new GetTokenCallback() {
                @Override
                public void onTokenFetched() {
                    getProducts(context, callback);
                }

                @Override
                public void onFailure() {

                }
            });
        } else {
            getProducts(context, callback);
        }*/
        getProducts(context, callback);
    }

    private void getProducts(@NonNull Context context, @NonNull final GetProductsCallback callback) {
        mRemoteDataSource.getProductsList(context, new GetProductsCallback() {
            @Override
            public void onProductsLoaded(ProductList productList) {
                saveProducts(productList);
                callback.onProductsLoaded(productList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
