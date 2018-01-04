package architecturedemo.pkg.arun.retail.data.source;

import android.content.Context;

import architecturedemo.pkg.arun.retail.data.models.CategoryList;
import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import architecturedemo.pkg.arun.retail.data.models.SubcategoryList;

public interface AppDataSource {

    interface GetProductsCallback {
        void onProductsLoaded(ProductList productList);

        void onFailure();
    }

    interface GetCategoriesCallback {
        void onCategoriesLoaded(CategoryList categoryList);

        void onFailure();
    }

    interface GetSubCategoriesCallback {
        void onSubCategoriesLoaded(SubcategoryList subcategoryList);

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

    void getProductsList(Context context, String productId, GetProductsCallback getProductsCallback);

    void getCategoriesList(Context context, GetCategoriesCallback getCategoriesCallback);

    void getSubCategoriesList(Context context, String categoryId, GetSubCategoriesCallback getCategoriesCallback);

    void getProductData(Context context, String productId, GetProductDataCallback getProductsCallback);

    void saveProducts(ProductList productList);

    void saveCategories(CategoryList categoryList);

    void saveSubCategories(SubcategoryList subcategoryList);
}