package arch.wb.retail.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import arch.wb.retail.MyApplication;
import arch.wb.retail.data.models.ApiTokenModel;
import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.models.SubcategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.local.AppPreferences;
import arch.wb.retail.data.source.local.LocalSource;
import arch.wb.retail.di.component.AppComponent;
import arch.wb.retail.util.AppExecutors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteSource implements AppDataSource {

    private static volatile RemoteSource INSTANCE;
    private static AppComponent mComponent;
    private Context mContext;
    private AppExecutors mAppExecutors;
    private static final String apiKey = "1662124823BA2484B430ADBCC1A9B599";
    private static final String key = "Bearer 4C4V4yUMvgI.cwA.cYw.qakgvIUDhYbrydkQgY59UniNz7HxTi5luInXlweDEZ8";
    private static final String CATEGORY_SUB_BASE_URL = "/indexes/categories/docs?api-version=2015-02-28&$filter=parent eq";
    private static final String PRODUCT_SUB_BASE_URL = "/indexes/products/docs?api-version=2015-02-28&search=";
    @Inject
    transient public RemoteServices mRemoteServices;

    private RemoteSource(Context context, AppExecutors appExecutors) {
        this.mContext = context;
        mAppExecutors = appExecutors;
        mComponent = ((MyApplication) context).getAppComponent();
        mComponent.inject(RemoteSource.this);
    }

    public static RemoteSource getInstance(@NonNull Context context, @NonNull AppExecutors appExecutors) {
        synchronized (LocalSource.class) {
            if (null == INSTANCE) {
                INSTANCE = new RemoteSource(context, appExecutors);
            }
            return INSTANCE;
        }
    }

    @Override
    public void getProductsList(Context context, final String productId, final GetProductsCallback getProductsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<ProductList> tokenModelCall = mRemoteServices.findProductsByTitle(PRODUCT_SUB_BASE_URL+productId, apiKey);
                        tokenModelCall.enqueue(new Callback<ProductList>() {
                            @Override
                            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                                getProductsCallback.onProductsLoaded(response.body());
                            }

                            @Override
                            public void onFailure(Call<ProductList> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    @Override
    public void getCategoriesList(Context context, final GetCategoriesCallback getCategoriesCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<CategoryList> tokenModelCall = mRemoteServices.getCategories(CATEGORY_SUB_BASE_URL+" null", apiKey);
                        tokenModelCall.enqueue(new Callback<CategoryList>() {
                            @Override
                            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                                getCategoriesCallback.onCategoriesLoaded(response.body());
                            }

                            @Override
                            public void onFailure(Call<CategoryList> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    @Override
    public void getSubCategoriesList(Context context, final String categoryId, final GetSubCategoriesCallback getSubCategoriesCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<SubcategoryList> tokenModelCall = mRemoteServices.getSubCategories(CATEGORY_SUB_BASE_URL+" \'"+categoryId+"\'", apiKey);
                        tokenModelCall.enqueue(new Callback<SubcategoryList>() {
                            @Override
                            public void onResponse(Call<SubcategoryList> call, Response<SubcategoryList> response) {
                                getSubCategoriesCallback.onSubCategoriesLoaded(response.body());
                            }

                            @Override
                            public void onFailure(Call<SubcategoryList> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    @Override
    public void getProductData(Context context, String productId, GetProductDataCallback getProductsCallback) {

    }

    @Override
    public void saveProducts(ProductList productList) {

    }

    @Override
    public void saveCategories(CategoryList categoryList) {

    }

    @Override
    public void saveSubCategories(SubcategoryList subcategoryList) {

    }

    @Override
    public void getToken(final GetTokenCallback getTokenCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<ApiTokenModel> tokenModelCall = mRemoteServices.getApiToken(key);
                        tokenModelCall.enqueue(new Callback<ApiTokenModel>() {
                            @Override
                            public void onResponse(Call<ApiTokenModel> call, Response<ApiTokenModel> response) {
                                ApiTokenModel apiTokenModel = response.body();
                                AppPreferences.saveToken(mContext, apiTokenModel.getToken());
                                getTokenCallback.onTokenFetched();
                            }

                            @Override
                            public void onFailure(Call<ApiTokenModel> call, Throwable t) {
                                getTokenCallback.onFailure();
                            }
                        });
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }
}
