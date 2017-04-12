package com.hao.e_nsurance.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.activities.ClientHomeActivity;
import com.hao.e_nsurance.activities.ConstateurActivity;
import com.hao.e_nsurance.activities.ConstateurHomeActivity;
import com.hao.e_nsurance.others.BlurBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/03/2017.
 */

public class AuthFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
//auth
    TextView txtCreerCompte, titleApp;
    ActionProcessButton btnAuth;
    ViewPager vp;
    TextInputLayout email, pass;
    ImageView blur;

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "SignInActivity";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ID = "id";
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private static final String PREF_ID = "Id";
    private static final String PREF_ROLE = "Role";

    String emailUser;
    String passwordUser;
    String id;
    String role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_auth, container, false);

        if (checkPreferences()) {

            getActivity().finish();
        }
        else{

            blur = (ImageView) rootView.findViewById(R.id.blur);
            Bitmap resultBmp = BlurBuilder.blur(getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.constat_accident_de_voiturev));
            blur.setImageBitmap(resultBmp);

            titleApp = (TextView) rootView.findViewById(R.id.title);
            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Yananeska.ttf");
            titleApp.setTypeface(typeface);

            email = (TextInputLayout) rootView.findViewById(R.id.email);
            pass = (TextInputLayout) rootView.findViewById(R.id.password);
            txtCreerCompte = (TextView) rootView.findViewById(R.id.txtCreerCompte);

            vp = (ViewPager) getActivity().findViewById(R.id.pager);

            txtCreerCompte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vp.setCurrentItem(vp.getCurrentItem() + 1);
                }
            });

            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            // Set the dimensions of the sign-in button.
            SignInButton signInButton = (SignInButton) rootView.findViewById(R.id.sign_in_button);

            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            });


            btnAuth = (ActionProcessButton) rootView.findViewById(R.id.login);

            btnAuth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email.setErrorEnabled(false);
                    pass.setErrorEnabled(false);
                    emailUser = email.getEditText().getText().toString();
                    passwordUser = pass.getEditText().getText().toString();

                    if (emailUser.equals("") || passwordUser.equals("")) {
                        if (emailUser.equals("")) {
                            email.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                        }

                        if (passwordUser.equals("")) {
                            pass.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                        }
                    } else {
                        if (!validateEmail(emailUser)) {
                            email.setError(getString(R.string.email_non_valide));
                        } else {
                            btnAuth.setMode(ActionProcessButton.Mode.ENDLESS);
                            btnAuth.setProgress(50);
                            String url = "http://www.mnarweb.com/ensurance/web/api/login";
                            AsyncHttpClient client = new AsyncHttpClient();
                            client.setTimeout(20000);
                            RequestParams params = new RequestParams();
                            params.put("email", emailUser);
                            params.put("password", passwordUser);
                            client.post(url, params, new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    try {
                                        String email = response.getString(TAG_EMAIL);
                                        id = response.getString(TAG_ID);
                                        Toast.makeText(getContext(),email+id,Toast.LENGTH_LONG).show();
                                        btnAuth.setProgress(100);
                                        role = response.getJSONArray("roles").getString(0);
                                        savePreferences();
                                        checkRoles(role);
                                        //startActivity(new Intent(getContext(), ClientHomeActivity.class));
                                        //startActivity(new Intent(getContext(), ConstateurActivity.class));
                                        getActivity().finish();
                                    } catch (JSONException e) {

                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                    super.onFailure(statusCode, headers, responseString, throwable);
                                    pass.setErrorEnabled(false);
                                    pass.setError(getString(R.string.mot_de_passe_incorrect));
                                    btnAuth.setProgress(-1);

                                }

                                @Override
                                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                    super.onFailure(statusCode, headers, throwable, errorResponse);
                                    email.setErrorEnabled(false);
                                    email.setError(getString(R.string.email_non_valide));
                                    btnAuth.setProgress(-1);

                                }


                            });
                        }
                    }
                }
            });
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public boolean validateEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void savePreferences() {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_UNAME, emailUser);
        editor.putString(PREF_PASSWORD, passwordUser);
        editor.putString(PREF_ID, id);
        editor.putString(PREF_ROLE, role);

        editor.apply();
    }

    private boolean checkPreferences() {

        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        String UnameValue = settings.getString(PREF_UNAME, "");
        String PasswordValue = settings.getString(PREF_PASSWORD, "");
        String roleValue = settings.getString(PREF_ROLE, "");
        if (!UnameValue.equals("") && !PasswordValue.equals("") && !roleValue.equals("")) {

            checkRoles(roleValue);
            return true;
        } else return false;
    }

    private void checkRoles(String role){
        switch (role){
            case "ROLE_USER":
                startActivity(new Intent(getContext(), ClientHomeActivity.class));
                break;
            case "ROLE_CONSTATEUR":
                startActivity(new Intent(getContext(), ConstateurHomeActivity.class));
                break;
            case "ROLE_EXPERT":

                break;
            case "ROLE_ASSISTANT":

                break;
        }
    }
}
