package arch.wb.retail.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;

import arch.wb.retail.util.SingleLiveEvent;


public class SplashViewModel extends AndroidViewModel {

    public final ObservableBoolean isLoggedIn = new ObservableBoolean();

    private final Context mContext;

    private final SingleLiveEvent<Boolean> loginCheckEvent = new SingleLiveEvent<>();


    public SplashViewModel(Application context) {
        super(context);
        mContext = context.getApplicationContext();
    }

    public void checkForLogin() {
        loginCheckEvent.call();
    }

    public SingleLiveEvent<Boolean> checkForLoginEvent() {
        return loginCheckEvent;
    }
}
