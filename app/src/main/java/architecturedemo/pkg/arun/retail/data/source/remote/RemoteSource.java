package architecturedemo.pkg.arun.retail.data.source.remote;

import android.support.annotation.NonNull;

import architecturedemo.pkg.arun.retail.data.source.ProductsDataSource;
import architecturedemo.pkg.arun.retail.data.source.local.LocalSource;
import architecturedemo.pkg.arun.retail.util.AppExecutors;

/**
 * Created by Arun.Kumar04 on 12/20/2017.
 */

public class RemoteSource implements ProductsDataSource {

    private static volatile RemoteSource INSTANCE;
    private AppExecutors mAppExecutors;

    private RemoteSource(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
    }

    public static RemoteSource getInstance(@NonNull AppExecutors appExecutors) {
        synchronized (LocalSource.class) {
            if (null == INSTANCE) {
                INSTANCE = new RemoteSource(appExecutors);
            }
            return INSTANCE;
        }
    }


    @Override
    public void getProductsList(GetProductsCallback getProductsCallback) {

    }
}
