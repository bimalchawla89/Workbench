package arch.wb.retail.productslist;

import arch.wb.retail.data.models.ProductData;

public interface ProductItemActionsListener {
    void onProductClicked(ProductData productData);
}