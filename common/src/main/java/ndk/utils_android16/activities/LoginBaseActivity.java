package ndk.utils_android16.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.core.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.ErrorUtilsWrapperBase;
import ndk.utils_android16.R;
import ndk.utils_android16.SharedPreferenceUtils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.ValidationUtils;
import ndk.utils_android16.network_task.HttpApiSelectTask;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;

//TODO : Create Layout initialization

public abstract class LoginBaseActivity extends ContextActivity {

    ProgressBar progressBar;
    ScrollView scrollView;

    // UI references.
    private EditText editTextUsername;
    private EditText editTextPassword;

    protected abstract String configure_SELECT_USER_URL();

    protected abstract String configure_APPLICATION_NAME();

    protected abstract Class configure_NEXT_ACTIVITY_CLASS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        scrollView = findViewById(R.id.scrollView);
        progressBar = findViewById(R.id.progressBar);

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        editTextPassword.setOnEditorActionListener((textView, id, keyEvent) -> {

            if (id == EditorInfo.IME_ACTION_DONE) {

                attemptLogin();
                return true;
            }
            return false;
        });

        Button buttonSignIn = findViewById(R.id.button_sign_in);
        buttonSignIn.setOnClickListener(view -> attemptLogin());
    }

    /**
     * Attempts to sign in or register the account specified by the login form. If
     * there are form errors (missing fields, etc.), the errors are presented and no
     * actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        ValidationUtils.resetErrors(new EditText[] { editTextUsername, editTextPassword });

        // TODO : Check Warning, Use org.javatuples
        Pair<Boolean, EditText> emptyCheckEditTextPairsResult = ValidationUtils
                .emptyCheckEditTextPairs(new Pair[] { new Pair<>(editTextUsername, "Please Enter Username..."),
                        new Pair<>(editTextPassword, "Please Enter Password...") });

        if (!Objects.requireNonNull(emptyCheckEditTextPairsResult.first)) {

            // There was an error; don't attempt login and focus the first form field with
            // an error.
            if (emptyCheckEditTextPairsResult.second != null) {

                emptyCheckEditTextPairsResult.second.requestFocus();
            }

        } else {

            InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputManager != null) {

                inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

            performHttpApiSelectTask();
        }
    }

    public void performHttpApiSelectTask() {

        HttpApiSelectTaskWrapper.executePostThenReturnJsonObject(configure_SELECT_USER_URL(), this, progressBar,
                scrollView, configure_APPLICATION_NAME(), configureHttpApiCallParameters(), handleJsonObject());
    }

    public HttpApiSelectTask.AsyncResponseJSONObject handleJsonObject() {

        return new HttpApiSelectTask.AsyncResponseJSONObject() {

            @Override
            public void processFinish(JSONObject jsonObject) {

                class ErrorUtilsWrapper extends ErrorUtilsWrapperBase {

                    private ErrorUtilsWrapper() {

                        super(configure_APPLICATION_NAME());
                    }
                }

                try {
                    String userCount = jsonObject.getString("user_count");

                    switch (userCount) {

                        case "1":
                            SharedPreferenceUtils.commitSharedPreferences(getApplicationContext(),
                                    configure_APPLICATION_NAME(),
                                    new Pair[] { new Pair<>("user_id", jsonObject.getString("id")) });
                            ActivityUtils.startActivityWithFinish(LoginBaseActivity.this,
                                    configure_NEXT_ACTIVITY_CLASS());
                            break;

                        case "0":
                            ToastUtils.longToast(LoginBaseActivity.this, "Login Failure!");
                            editTextUsername.requestFocus();
                            break;

                        default:
                            ErrorUtilsWrapper.displayJSONFieldMiss(LoginBaseActivity.this, jsonObject);
                    }

                } catch (JSONException e) {

                    ErrorUtilsWrapper.displayException(LoginBaseActivity.this, e);
                }
            }
        };
    }

    public Pair[] configureHttpApiCallParameters() {

        return new Pair[] { new Pair<>("username", editTextUsername.getText().toString()),
                new Pair<>("password", editTextPassword.getText().toString()) };
    }
}
