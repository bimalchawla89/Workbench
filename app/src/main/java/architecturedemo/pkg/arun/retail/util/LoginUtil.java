package architecturedemo.pkg.arun.retail.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.constants.Constants;
import architecturedemo.pkg.arun.retail.di.component.AppComponent;

public class LoginUtil {

    public LoginUtil(AppComponent appComponent) {
        appComponent.inject(this);
    }


    private GoogleSignInClient mGoogleSignInClient;

    public void initializeGoogleSignInOptions(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public void signInWithGoogle(Context context) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        ((Activity) context).startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    public Task<GoogleSignInAccount> getGoogleAccountData(Intent data) {
        return GoogleSignIn.getSignedInAccountFromIntent(data);
    }
}
