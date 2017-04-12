package com.hao.e_nsurance.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.hao.e_nsurance.R;
import com.hao.e_nsurance.activities.ClientHomeActivity;
import com.hao.e_nsurance.others.BlurBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnregFragment extends Fragment {

    ActionProcessButton btnEnrg;
    TextInputLayout email, pass, pass2;
    String username ;
    String password ;
    TextView titleApp;
    ImageView blur;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private static final String TAG_SUCCESS = "email";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_enreg, container, false);

        blur = (ImageView) rootView.findViewById(R.id.blur);
        Bitmap resultBmp = BlurBuilder.blur(getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.constat_accident_de_voiturev));
        blur.setImageBitmap(resultBmp);

        titleApp = (TextView) rootView.findViewById(R.id.title);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Yananeska.ttf");
        titleApp.setTypeface(typeface);

        btnEnrg = (ActionProcessButton) rootView.findViewById(R.id.btnCreerCompte);
        email = (TextInputLayout) rootView.findViewById(R.id.email);
        pass = (TextInputLayout) rootView.findViewById(R.id.password);
        pass2 = (TextInputLayout) rootView.findViewById(R.id.rePassword);
        btnEnrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass2.setErrorEnabled(false);
                pass.setErrorEnabled(false);
                email.setErrorEnabled(false);
                username = email.getEditText().getText().toString();
                password = pass.getEditText().getText().toString();
                String passwordConfirm = pass2.getEditText().getText().toString();
                if (username.equals("") || password.equals("") || passwordConfirm.equals("")) {
                    if (username.equals("")) {
                        email.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                    }
                    if (password.equals("")) {
                        pass.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                    }
                    if (passwordConfirm.equals("")) {
                        pass2.setError(getString(R.string.ce_champs_ne_doit_etre_vide));
                    }
                } else {
                    if (!validateEmail(username)) {
                        email.setError(getString(R.string.email_non_valide));
                        return;
                    }
                    if (password.equals(passwordConfirm)) {
                        btnEnrg.setMode(ActionProcessButton.Mode.ENDLESS);
                        btnEnrg.setProgress(50);


                        String url = "http://www.mnarweb.com/ensurance/web/api/adduser";
                        AsyncHttpClient client = new AsyncHttpClient();
                        RequestParams params = new RequestParams();
                        params.put("email", username);
                        params.put("password", password);
                        client.post(url, params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                try {
                                    btnEnrg.setProgress(100);
                                    String email = response.getString(TAG_SUCCESS);
                                    savePreferences();
                                    startActivity(new Intent(getContext(), ClientHomeActivity.class));
                                } catch (JSONException e) {

                                }

                            }

                            @Override
                            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                pass.setError(getString(R.string.mot_de_passe_incorrect));
                                btnEnrg.setProgress(-1);
                            }

                            @Override
                            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                email.setError(getString(R.string.email_non_valide));
                                btnEnrg.setProgress(-1);
                            }


                        });
                    } else {
                        pass2.setError(getString(R.string.mot_de_passe_non_identique));

                    }
                }

            }
        });
        return rootView;
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
        editor.putString(PREF_UNAME, username);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }
}
