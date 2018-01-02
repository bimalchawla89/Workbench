package architecturedemo.pkg.arun.retail.productslist;

import architecturedemo.pkg.arun.retail.data.models.ProductData;

public interface ProductItemActionsListener {
    void onProductClicked(ProductData productData);
}