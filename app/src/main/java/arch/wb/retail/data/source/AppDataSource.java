package arch.wb.retail.data.source;

import android.content.Context;

import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.models.SubcategoryList;

public interface AppDataSource {

    void getToken(GetTokenCallback getTokenCallback);

    void getProductsListFromSubCategory(Context context, String subCategory, GetProductsCallback getProductsCallback);

    void getCategoriesList(Context context, GetCategoriesCallback getCategoriesCallback);

    void getSubCategoriesList(Context context, String categoryId, GetSubCategoriesCallback getCategoriesCallback);

    void getProductDetails(Context context, String productId, GetProductDataCallback getProductsCallback);

    void saveProducts(ProductList productList);

    void saveCategories(CategoryList categoryList);

    void saveSubCategories(SubcategoryList subcategoryList);

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
}
