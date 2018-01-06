package architecturedemo.pkg.arun.retail.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.MyApplication;
import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.constants.Constants;
import architecturedemo.pkg.arun.retail.data.source.PreferenceRepository;
import architecturedemo.pkg.arun.retail.util.LoginUtil;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Inject
    LoginUtil loginUtil;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.googleplussignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserStatus();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    private void checkUserStatus() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (null == currentUser) {
            loginUtil.signInWithGoogle(LoginActivity.this);
        } else {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    //TODO check if this is right way to implement or better option as per Android Arch Components
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RC_SIGN_IN && resultCode == RESULT_OK) {
            Task<GoogleSignInAccount> task = loginUtil.getGoogleAccountData(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                // Google Sign In failed, update UI appropriately
                Log.w("", "Google sign in failed", e);
            }
        } else {
            Toast.makeText(this, "Login failed. Please try again!", Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (null != user) {
            Toast.makeText(LoginActivity.this, "Login Successful : " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            PreferenceRepository.putBoolean(this, Constants.IS_LOGGED_IN, true);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
