package architecturedemo.pkg.arun.retail.data.source;

import android.content.Context;

import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public interface ProductsDataSource {

    interface GetProductsCallback {
        void onProductsLoaded(ProductList productList);

        void onFailure();
    }

    interface GetProductDataCallback {
        void onProductDataLoaded(ProductData productData);

        void onFailure();
    }

    interface GetTokenCallback {
        void onTokenFetched();

        void onFailure();
    }

    void getToken(GetTokenCallback getTokenCallback);

    void getProductsList(Context context, GetProductsCallback getProductsCallback);

    void getProductData(Context context, String productId, GetProductDataCallback getProductsCallback);

    void saveProducts(ProductList productList);
}
