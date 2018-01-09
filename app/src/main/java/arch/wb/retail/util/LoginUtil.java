package arch.wb.retail.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

import arch.wb.retail.R;
import arch.wb.retail.constants.Constants;

public class LoginUtil {

    private GoogleSignInClient mGoogleSignInClient;

    public LoginUtil(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.wb_web_client_id))
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
