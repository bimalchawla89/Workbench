package arch.wb.retail.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.models.SubcategoryList;

public class AppRepository implements AppDataSource {

    private static AppRepository INSTANCE = null;

    private final AppDataSource mLocalDataSource;
    private final AppDataSource mRemoteDataSource;

    private AppRepository(AppDataSource localDataSource, AppDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static AppRepository getInstance(AppDataSource localDataSource, AppDataSource remoteDataSource) {
        if (null == INSTANCE) {
            INSTANCE = new AppRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getProductsListFromSubCategory(@NonNull Context context, String subCategory, GetProductsCallback callback) {
        getProductsFromLocalSource(context, subCategory, callback);
    }

    @Override
    public void getCategoriesList(@NonNull Context context, GetCategoriesCallback callback) {
        getCategoriesFromLocalSource(context, callback);
    }

    @Override
    public void getSubCategoriesList(@NonNull Context context, String categoryId, GetSubCategoriesCallback callback) {
        getSubCategoriesFromLocalSource(context, categoryId, callback);
    }

    @Override
    public void getProductDetails(@NonNull Context context, String productId, GetProductDataCallback callback) {
        getProductDataFromLocalSource(context, productId, callback);
    }

    @Override
    public void saveProducts(ProductList productList) {
        mLocalDataSource.saveProducts(productList);
    }

    @Override
    public void saveCategories(CategoryList categoryList) {
        mLocalDataSource.saveCategories(categoryList);
    }

    @Override
    public void saveSubCategories(SubcategoryList subcategoryList) {
        mLocalDataSource.saveSubCategories(subcategoryList);
    }

    @Override
    public void getToken(GetTokenCallback getTokenCallback) {
        mRemoteDataSource.getToken(getTokenCallback);
    }

    private void getProductsFromLocalSource(@NonNull final Context context, final String subCategory, @NonNull final GetProductsCallback callback) {
        mLocalDataSource.getProductsListFromSubCategory(context, subCategory, new GetProductsCallback() {
            @Override
            public void onProductsLoaded(ProductList productList) {
                if (null == productList || null == productList.getValue() || productList.getValue().isEmpty()) {
                    getProductsFromRemoteSource(context, subCategory, callback);
                } else {
                    callback.onProductsLoaded(productList);
                }
            }

            @Override
            public void onFailure() {
                getProductsFromRemoteSource(context, subCategory, callback);
            }
        });
    }

    private void getCategoriesFromLocalSource(@NonNull final Context context, @NonNull final GetCategoriesCallback callback) {
        mLocalDataSource.getCategoriesList(context, new GetCategoriesCallback() {
            @Override
            public void onCategoriesLoaded(CategoryList categoryList) {
                if (null == categoryList || null == categoryList.getValue() || categoryList.getValue().isEmpty()) {
                    getCategoriesFromRemoteSource(context, callback);
                } else {
                    callback.onCategoriesLoaded(categoryList);
                }
            }

            @Override
            public void onFailure() {
                getCategoriesFromRemoteSource(context, callback);
            }
        });
    }

    private void getSubCategoriesFromLocalSource(@NonNull final Context context, final String categoryId, @NonNull final GetSubCategoriesCallback callback) {
        mLocalDataSource.getSubCategoriesList(context, categoryId, new GetSubCategoriesCallback() {
            @Override
            public void onSubCategoriesLoaded(SubcategoryList subcategoryList) {
                if (null == subcategoryList || null == subcategoryList.getValue() || subcategoryList.getValue().isEmpty()) {
                    getSubCategoriesFromRemoteSource(context, categoryId, callback);
                } else {
                    callback.onSubCategoriesLoaded(subcategoryList);
                }
            }

            @Override
            public void onFailure() {
                getSubCategoriesFromRemoteSource(context, categoryId, callback);
            }
        });
    }

    private void getProductDataFromLocalSource(@NonNull final Context context, final String productId, @NonNull final GetProductDataCallback callback) {
        mLocalDataSource.getProductDetails(context, productId, new GetProductDataCallback() {
            @Override
            public void onProductDataLoaded(ProductData productData) {
//                if (null == productsList) {
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

    private void getProductsFromRemoteSource(@NonNull final Context context, String productId, @NonNull final GetProductsCallback callback) {
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
        getProducts(context, productId, callback);
    }

    private void getCategoriesFromRemoteSource(@NonNull final Context context, @NonNull final GetCategoriesCallback callback) {
        getCategories(context, callback);
    }

    private void getSubCategoriesFromRemoteSource(@NonNull final Context context, String categoryId, @NonNull final GetSubCategoriesCallback callback) {
        getSubCategories(context, categoryId, callback);
    }

    private void getProducts(@NonNull Context context, String subCategory, @NonNull final GetProductsCallback callback) {
        mRemoteDataSource.getProductsListFromSubCategory(context, subCategory, new GetProductsCallback() {
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

    private void getCategories(@NonNull Context context, @NonNull final GetCategoriesCallback callback) {
        mRemoteDataSource.getCategoriesList(context, new GetCategoriesCallback() {
            @Override
            public void onCategoriesLoaded(CategoryList categoryList) {
                saveCategories(categoryList);
                callback.onCategoriesLoaded(categoryList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

    private void getSubCategories(@NonNull Context context, String categoryId, @NonNull final GetSubCategoriesCallback callback) {
        mRemoteDataSource.getSubCategoriesList(context, categoryId, new GetSubCategoriesCallback() {
            @Override
            public void onSubCategoriesLoaded(SubcategoryList subcategoryList) {
                saveSubCategories(subcategoryList);
                callback.onSubCategoriesLoaded(subcategoryList);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
