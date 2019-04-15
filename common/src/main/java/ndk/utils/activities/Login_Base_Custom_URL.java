package ndk.utils.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import ndk.utils.R;
import ndk.utils.Validation_Utils;
import ndk.utils_android1.Context_Activity;

//TODO : Create Layout initialization

public abstract class Login_Base_Custom_URL extends Context_Activity {

    // UI references.
    public EditText username;
    public EditText password;
    public View mProgressView;
    public View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        username = findViewById(R.id.username);
        password = findViewById(R.id.passcode);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button sign_in_button = findViewById(R.id.sign_in_button);
        sign_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        Validation_Utils.reset_errors(new EditText[]{username, password});

        Pair<Boolean, EditText> empty_check_result = Validation_Utils.empty_check(new Pair[]{new Pair<>(username, "Please Enter Username..."), new Pair<>(password, "Please Enter Password...")});

        if (empty_check_result.first) {
            // There was an error; don't attempt login and focus the first form field with an error.
            if (empty_check_result.second != null) {
                empty_check_result.second.requestFocus();
            }
        } else {
            InputMethodManager inputManager =
                    (InputMethodManager) getApplicationContext().
                            getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(
                        Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            configure_FURTHER_PROCESSING();
        }
    }

    protected abstract void configure_FURTHER_PROCESSING();

    public Pair[] configure_http_call_parameters() {
        return new Pair[]{new Pair<>("username", username.getText().toString()), new Pair<>("password", password.getText().toString())};
    }

}
