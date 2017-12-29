package architecturedemo.pkg.arun.retail.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import architecturedemo.pkg.arun.retail.MyApplication;
import architecturedemo.pkg.arun.retail.data.models.ApiTokenModel;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.local.AppPreferences;
import architecturedemo.pkg.arun.retail.data.source.local.LocalSource;
import architecturedemo.pkg.arun.retail.di.component.AppComponent;
import architecturedemo.pkg.arun.retail.util.AppExecutors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public class RemoteSource implements ProductsDataSource {

    private static volatile RemoteSource INSTANCE;
    private static AppComponent mComponent;
    private Context mContext;
    private AppExecutors mAppExecutors;
    private static final String apiKey = "1D9B788C83C1DD0FBB2EBF0D2F5D75AE";
    private static final String key = "Bearer 4C4V4yUMvgI.cwA.cYw.qakgvIUDhYbrydkQgY59UniNz7HxTi5luInXlweDEZ8";
    private static final String apiVersion = "2015-02-28";
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
    public void getProductsList(Context context, GetProductsCallback getProductsCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Call<ProductList> tokenModelCall = mRemoteServices.findProductsByTitle(apiKey, apiVersion, "");
                        tokenModelCall.enqueue(new Callback<ProductList>() {
                            @Override
                            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

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
    public void saveProducts(ProductList productList) {

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
