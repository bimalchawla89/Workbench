package architecturedemo.pkg.arun.retail.data.source;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public interface ProductsDataSource {

    interface GetProductsCallback {
        void onProductsLoaded(List<Product> productList);

        void onFailure();
    }

    void getProductsList(GetProductsCallback getProductsCallback);
}
