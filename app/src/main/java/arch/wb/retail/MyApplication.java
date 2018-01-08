package arch.wb.retail;

import android.app.Application;

import arch.wb.retail.di.component.AppComponent;
import arch.wb.retail.di.component.DaggerAppComponent;
import arch.wb.retail.di.module.ApiModule;
import arch.wb.retail.di.module.ApplicationModule;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
