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

import java.util.ArrayList;
import java.util.Objects;

import ndk.utils_android1.ContextActivity;
import ndk.utils_android14.ActivityUtils;
import ndk.utils_android16.BuildConfig;
import ndk.utils_android16.ErrorUtilsWrapperBase;
import ndk.utils_android16.R;
import ndk.utils_android16.SharedPreferenceUtils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.ValidationUtils;
import ndk.utils_android16.network_task.HttpApiSelectTask;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.network_task.RestGetTask;

//TODO : Create Layout initialization

public abstract class LoginBaseActivity extends ContextActivity {

    public ProgressBar progressBar;
    public ScrollView scrollView;

    private EditText editTextUsername;
    private EditText editTextPassword;

    public abstract String configure_SELECT_USER_URL();

    public abstract String configure_APPLICATION_NAME();

    public abstract Class configure_NEXT_ACTIVITY_CLASS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        scrollView = findViewById(R.id.scrollView);
        progressBar = findViewById(R.id.progressBar);

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);

//        if(BuildConfig.DEBUG){
//
//            editTextUsername.setText(configureTestUsername());
//            editTextPassword.setText(configureTestPassword());
//        }

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

    public abstract String configureTestPassword();

    public abstract String configureTestUsername();

    private void attemptLogin() {

        // Reset errors.
        ValidationUtils.resetErrors(new EditText[]{editTextUsername, editTextPassword});

        ArrayList<org.javatuples.Pair<EditText, String>> editTextsWithErrorMessages = new ArrayList<>();
        editTextsWithErrorMessages.add(org.javatuples.Pair.with(editTextUsername, "Please Enter Username..."));
        editTextsWithErrorMessages.add(org.javatuples.Pair.with(editTextPassword, "Please Enter Password..."));
        org.javatuples.Pair<Boolean, EditText> nonEmptyCheckEditTextPairsResult = ValidationUtils.nonEmptyCheckEditTextPairs(editTextsWithErrorMessages);

        if (nonEmptyCheckEditTextPairsResult.getValue0()) {

            InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputManager != null) {

                inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

            performHttpApiSelectTask();

        } else {

            nonEmptyCheckEditTextPairsResult.getValue1().requestFocus();
        }
    }

    public void performHttpApiSelectTask() {

        HttpApiSelectTaskWrapper.executeGetThenReturnJsonObject(RestGetTask.prepareGetUrl(configure_SELECT_USER_URL(), configureHttpApiCallParameters()), this, progressBar, scrollView, configure_APPLICATION_NAME(), handleJsonResponseObject());
    }

    public HttpApiSelectTask.AsyncResponseJSONObject handleJsonResponseObject() {

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
                            SharedPreferenceUtils.commitSharedPreferences(LoginBaseActivity.this.getApplicationContext(), LoginBaseActivity.this.configure_APPLICATION_NAME(), new Pair[]{new Pair<>("user_id", jsonObject.getString("id"))});
                            ActivityUtils.startActivityWithFinish(currentActivityContext, LoginBaseActivity.this.configure_NEXT_ACTIVITY_CLASS());
                            break;

                        case "0":
                            ToastUtils.longToast(currentActivityContext, "Login Failure!");
                            editTextUsername.requestFocus();
                            break;

                        default:
                            ErrorUtilsWrapper.displayJSONFieldMiss(currentActivityContext, jsonObject);
                    }

                } catch (JSONException e) {

                    ErrorUtilsWrapper.displayException(currentActivityContext, e);
                }
            }
        };
    }

    public Pair[] configureHttpApiCallParameters() {

        return new Pair[]{new Pair<>("username", editTextUsername.getText().toString()), new Pair<>("password", editTextPassword.getText().toString())};
    }
}
