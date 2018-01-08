package arch.wb.retail.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.models.SubCategoryData;
import arch.wb.retail.data.models.SubcategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.util.AppExecutors;

public class LocalSource implements AppDataSource {

    private static volatile LocalSource INSTANCE;
    private AppExecutors mAppExecutors;
    private ProductsDao mProductsDao;
    private CategoryDao mCategoriesDao;
    private SubCategoryDao mSubCategoriesDao;

    private LocalSource(AppExecutors appExecutors, ProductsDao productsDao, CategoryDao categoryDao, SubCategoryDao subCategoryDao) {
        mAppExecutors = appExecutors;
        mProductsDao = productsDao;
        mCategoriesDao = categoryDao;
        mSubCategoriesDao = subCategoryDao;
        // insertDummyData();
    }

    public static LocalSource getInstance(@NonNull AppExecutors appExecutors, @NonNull ProductsDao productsDao, @NonNull CategoryDao categoryDao, @NonNull SubCategoryDao subCategoryDao) {
        synchronized (LocalSource.class) {
            if (null == INSTANCE) {
                INSTANCE = new LocalSource(appExecutors, productsDao, categoryDao, subCategoryDao);
            }
            return INSTANCE;
        }
    }

    @Override
    public void getProductsListFromSubCategory(@NonNull final Context context, final String subCategory, @NonNull final GetProductsCallback productsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<ProductData> productList = mProductsDao.getAllProductsDataBySubCategory(subCategory);
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
    public void getCategoriesList(@NonNull final Context context, @NonNull final GetCategoriesCallback categoriesCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<CategoryData> categoryDataList = mCategoriesDao.getAllCategoriesData();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (null != categoryDataList && !categoryDataList.isEmpty()) {
                            CategoryList list = new CategoryList();
                            list.setValue(categoryDataList);
                            categoriesCallback.onCategoriesLoaded(list);
                        } else {
                            categoriesCallback.onFailure();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getSubCategoriesList(@NonNull final Context context, final String categoryId, @NonNull final GetSubCategoriesCallback subCategoriesCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<SubCategoryData> subCategoryDataList = mSubCategoriesDao.getAllSubCategoriesData(categoryId);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (null != subCategoryDataList && !subCategoryDataList.isEmpty()) {
                            SubcategoryList list = new SubcategoryList();
                            list.setValue(subCategoryDataList);
                            subCategoriesCallback.onSubCategoriesLoaded(list);
                        } else {
                            subCategoriesCallback.onFailure();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }


    @Override
    public void getProductDetails(@NonNull final Context context, final String productId, @NonNull final GetProductDataCallback productsCallback) {
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
    public void saveCategories(final CategoryList categoryList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < categoryList.getValue().size(); i++) {
                    mCategoriesDao.insertCategory(categoryList.getValue().get(i));
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveSubCategories(final SubcategoryList categoryList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < categoryList.getValue().size(); i++) {
                    mSubCategoriesDao.insertSubCategory(categoryList.getValue().get(i));
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getToken(GetTokenCallback getTokenCallback) {

    }
}
