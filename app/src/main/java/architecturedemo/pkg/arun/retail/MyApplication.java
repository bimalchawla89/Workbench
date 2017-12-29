package architecturedemo.pkg.arun.retail;

import android.app.Application;

import architecturedemo.pkg.arun.retail.di.component.AppComponent;
import architecturedemo.pkg.arun.retail.di.component.DaggerAppComponent;
import architecturedemo.pkg.arun.retail.di.module.ApiModule;
import architecturedemo.pkg.arun.retail.di.module.ApplicationModule;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public class MyApplication extends Application{

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
