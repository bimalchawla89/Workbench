package architecturedemo.pkg.arun.retail.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;

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
    public void getProductsList(GetProductsCallback callback) {
        getProductsFromLocalSource(callback);
    }

    private void getProductsFromLocalSource(@NonNull final GetProductsCallback callback) {
        mLocalDataSource.getProductsList(new GetProductsCallback() {
            @Override
            public void onProductsLoaded(List<Product> productList) {
                if (null == productList || productList.isEmpty()) {
                    getProductsFromRemoteSource(callback);
                } else {
                    callback.onProductsLoaded(productList);
                }
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

    private void getProductsFromRemoteSource(@NonNull final GetProductsCallback callback) {
        mRemoteDataSource.getProductsList(new GetProductsCallback() {
            @Override
            public void onProductsLoaded(List<Product> productList) {
                callback.onProductsLoaded(productList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
