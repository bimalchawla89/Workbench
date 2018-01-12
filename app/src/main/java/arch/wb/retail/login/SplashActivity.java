package arch.wb.retail.login;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import arch.wb.retail.HomeActivity;
import arch.wb.retail.R;
import arch.wb.retail.ViewModelFactory;
import arch.wb.retail.constants.Constants;
import arch.wb.retail.data.source.PreferenceRepository;
import arch.wb.retail.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(this, R
                .layout.activity_splash);
        activitySplashBinding.executePendingBindings();
        mViewModel = obtainViewModel(this);
        // Subscribe to "open productDetails" event
        mViewModel.checkForLoginEvent().observe(SplashActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loginStatus) {
                mViewModel.isLoggedIn.set(PreferenceRepository.getBoolean(SplashActivity.this,
                        Constants
                                .IS_LOGGED_IN, false));
                Intent intent = null;
                if (mViewModel.isLoggedIn.get()) {
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });

        // Subscribe to login event
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewModel.checkForLogin();
            }
        }, 2000);
    }

    private SplashViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        SplashViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(SplashViewModel.class);

        return viewModel;
    }
}
