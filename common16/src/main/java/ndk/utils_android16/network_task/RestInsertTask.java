package ndk.utils_android16.network_task;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android1.NetworkUtils;
import ndk.utils_android1.NetworkUtils.FurtherActions;

import static ndk.utils_android1.NetworkUtils.performHttpClientPostTask;
import static ndk.utils_android1.ProgressBarUtils.showProgress;

public class RestInsertTask extends AsyncTask<Void, Void, String[]> {

    private String url, tag;
    private AppCompatActivity currentActivity;
    private View progressBarView, formView, focusOnError;
    private Pair[] nameValuePairs;
    private Class nextActivity;
    private boolean finishFlag = true;
    private boolean selfFinishFlag = true;
    private boolean furtherActionsFlag = true;
    private boolean clearAndFurtherActionsFlag = true;
    private EditText[] editTextsToClear;
    private boolean clearFieldsFlag = true;
    private FurtherActions furtherActions;
    private Pair[] nextClassExtras;

    //finish with next activity
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError, Class nextActivity) {
        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;
        this.nextActivity = nextActivity;
    }

    //finish with next activity and extras
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError, Class nextActivity, Pair[] nextClassExtras) {
        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;
        this.nextActivity = nextActivity;
        this.nextClassExtras = nextClassExtras;

        this.finishFlag = false;
        this.selfFinishFlag = false;
        this.clearFieldsFlag = false;
        this.furtherActionsFlag = false;
        this.clearAndFurtherActionsFlag = false;
    }

    //further actions
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError, FurtherActions furtherActions) {

        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;

        this.finishFlag = false;
        this.selfFinishFlag = false;
        this.clearFieldsFlag = false;

        this.furtherActions = furtherActions;
    }

    //clear fields & further actions
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError, EditText[] editTextsToClear, FurtherActions furtherActions) {

        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;

        this.finishFlag = false;
        this.selfFinishFlag = false;
        this.clearFieldsFlag = false;
        this.furtherActionsFlag = false;

        this.editTextsToClear = editTextsToClear;
        this.furtherActions = furtherActions;
    }

    //clear fields
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError, EditText[] editTextsToClear) {
        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;
        this.finishFlag = false;
        this.selfFinishFlag = false;
        this.editTextsToClear = editTextsToClear;
    }

    //self finish
    public RestInsertTask(String url, AppCompatActivity currentActivity, View progressBarView, View formView, String tag, Pair[] nameValuePairs, View focusOnError) {
        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBarView = progressBarView;
        this.formView = formView;
        this.tag = tag;
        this.nameValuePairs = nameValuePairs;
        this.focusOnError = focusOnError;
        this.finishFlag = false;
    }

    @Override
    protected String[] doInBackground(Void... params) {
        return performHttpClientPostTask(url, nameValuePairs);
    }

    @Override
    protected void onPostExecute(final String[] networkActionResponseArray) {
        showProgress(false, currentActivity, progressBarView, formView);
        if (finishFlag) {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, new EditText[]{}, focusOnError, tag, 1, new Pair[]{}, furtherActions);
        } else if (selfFinishFlag) {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, new EditText[]{}, focusOnError, tag, 3, new Pair[]{}, furtherActions);
        } else if (clearFieldsFlag) {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, editTextsToClear, focusOnError, tag, 2, new Pair[]{}, furtherActions);
        } else if (furtherActionsFlag) {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, new EditText[]{}, focusOnError, tag, 5, new Pair[]{}, furtherActions);
        } else if (clearAndFurtherActionsFlag) {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, editTextsToClear, focusOnError, tag, 6, new Pair[]{}, furtherActions);
        } else {
            NetworkUtils.handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, nextActivity, new EditText[]{}, focusOnError, tag, 4, nextClassExtras, furtherActions);
        }
    }

    @Override
    protected void onCancelled() {
        showProgress(false, currentActivity, progressBarView, formView);
    }
}
