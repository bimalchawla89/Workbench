package architecturedemo.pkg.arun.retail.login;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.ViewModelFactory;
import architecturedemo.pkg.arun.retail.constants.Constants;
import architecturedemo.pkg.arun.retail.data.source.PreferenceRepository;
import architecturedemo.pkg.arun.retail.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(this, R
                .layout.activity_splash);
        activitySplashBinding.executePendingBindings();
        mViewModel = obtainViewModel(this);
        // Subscribe to "open task" event
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
