package architecturedemo.pkg.arun.retail.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.MyApplication;
import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.constants.Constants;
import architecturedemo.pkg.arun.retail.data.source.PreferenceRepository;
import architecturedemo.pkg.arun.retail.util.LoginUtil;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginUtil loginUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        loginUtil.initializeGoogleSignInOptions(this);
        ((Button) findViewById(R.id.googleplussignin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUtil.signInWithGoogle(LoginActivity.this);
            }
        });
    }


    //TODO check if this is right way to implement or better option as per Android Arch Components
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RC_SIGN_IN) {
            PreferenceRepository.putBoolean(this, Constants.IS_LOGGED_IN, true);
            startActivity(new Intent(this, HomeActivity.class));
            // Task<GoogleSignInAccount> task = loginUtil.getGoogleAccountData(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                //GoogleSignInAccount account = task.getResult(ApiException.class);
            } catch (Exception e) {
                // Google Sign In failed, update UI appropriately
                Log.w("", "Google sign in failed", e);
            }
        }
    }


}
