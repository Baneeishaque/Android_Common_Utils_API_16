package ndk.utils_android16.network_task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import ndk.utils_android16.Date_Utils;
import ndk.utils_android16.Float_Utils;
import ndk.utils_android16.JsonUtils;
import ndk.utils_android16.NetworkUtils;
import ndk.utils_android16.Pass_Book_Utils;
import ndk.utils_android16.ToastUtils;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntry;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2;
import ndk.utils_android16.widgets.pass_book.PassBookTableView;
import ndk.utils_android16.widgets.pass_book.PassBookTableViewV2;

import static ndk.utils_android16.Date_Utils.mysql_date_time_format;
import static ndk.utils_android16.ProgressBarUtils.showProgress;

public class LoadPassBookTask extends AsyncTask<Void, Void, String[]> {

    private String url, tag;
    private AppCompatActivity currentActivity;
    private ProgressBar progressBar;
    private View scrollView;
    private PassBookTableView passBookTableView;
    private PassBookTableViewV2 passBookTableViewV2;
    private Pair[] nameValuePair;
    private boolean v2Flag, sortFlag;
    private String currentAccountId;

    public LoadPassBookTask(String url, AppCompatActivity currentActivity, ProgressBar progressBar, View scrollView, String tag, PassBookTableView passBookTableView, Pair[] nameValuePair) {

        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.passBookTableView = passBookTableView;
        this.nameValuePair = nameValuePair;
        this.v2Flag = false;
        this.sortFlag = false;
    }

    public LoadPassBookTask(String url, AppCompatActivity currentActivity, ProgressBar progressBar, View scrollView, String tag, PassBookTableViewV2 passBookTableViewV2, String currentAccountId) {

        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.passBookTableViewV2 = passBookTableViewV2;
        this.currentAccountId = currentAccountId;
        this.v2Flag = true;
        this.sortFlag = false;
    }

    public LoadPassBookTask(String url, AppCompatActivity currentActivity, ProgressBar progressBar, View scrollView, String tag, PassBookTableViewV2 passBookTableViewV2, String currentAccountId, boolean sortFlag) {

        this.url = url;
        this.currentActivity = currentActivity;
        this.progressBar = progressBar;
        this.scrollView = scrollView;
        this.tag = tag;
        this.passBookTableViewV2 = passBookTableViewV2;
        this.currentAccountId = currentAccountId;
        this.v2Flag = true;
        //TODO : Implement Sort Option
        //Sort flag always true
        this.sortFlag = sortFlag;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        if (v2Flag) {
            return NetworkUtils.performHttpClientPostTask(url, new Pair[]{});
        } else {
            return NetworkUtils.performHttpClientPostTask(url, nameValuePair);
        }
    }


    @Override
    protected void onPostExecute(final String[] networkActionResponseArray) {

        showProgress(false, currentActivity, progressBar, scrollView);

        NetworkUtils.displayNetworkActionResponse(tag, networkActionResponseArray);

        ArrayList<PassBookEntry> passBookEntries = new ArrayList<>();
        ArrayList<PassBookEntryV2> passBookEntryV2s = new ArrayList<>();

        if (networkActionResponseArray[0].equals("1")) {

            ToastUtils.errorToast(currentActivity);

        } else {

            try {

                if (sortFlag) {

                    if (networkActionResponseArray[1].equals("[]")) {

                        ToastUtils.noEntriesToast(currentActivity);

                    } else {

                        //TODO : Use Direct Pattern
                        enterTransactions(JsonUtils.sort_JSON_array_by_date_field(networkActionResponseArray[1], Date_Utils.mysql_date_time_format.toPattern(), "event_date_time"), passBookEntryV2s, 0);
                    }

                } else {

                    JSONArray json_array = new JSONArray(networkActionResponseArray[1]);

                    if (json_array.getJSONObject(0).getString("status").equals("2")) {

                        Toast.makeText(currentActivity, "No Entries...", Toast.LENGTH_LONG).show();

                    } else if (json_array.getJSONObject(0).getString("status").equals("0")) {

                        if (v2Flag) {

                            enterTransactions(json_array, passBookEntryV2s, 1);

                        } else {

                            float balance = 0;

                            for (int i = 1; i < json_array.length(); i++) {

                                if (json_array.getJSONObject(i).getString("particulars").contains("Credit")) {

                                    balance = balance + Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                                    passBookEntries.add(new PassBookEntry(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), 0, Double.parseDouble(json_array.getJSONObject(i).getString("amount")), Float_Utils.roundOff_to_two_positions(balance)));
                                    Log.d(tag, String.valueOf(balance));
                                }

                                if (json_array.getJSONObject(i).getString("particulars").contains("Debit")) {

                                    balance = balance - Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                                    passBookEntries.add(new PassBookEntry(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), Double.parseDouble(json_array.getJSONObject(i).getString("amount")), 0, Float_Utils.roundOff_to_two_positions(balance)));
                                    Log.d(tag, String.valueOf(balance));
                                }
                            }

                            Pass_Book_Utils.bind(passBookTableView, currentActivity, passBookEntries);
                        }
                    }
                }
            } catch (JSONException e) {

                Toast.makeText(currentActivity, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(tag, e.getLocalizedMessage());

            } catch (ParseException e) {

                Toast.makeText(currentActivity, "Date Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(tag, e.getLocalizedMessage());
            }
        }
    }

    private void enterTransactions(JSONArray json_array, ArrayList<PassBookEntryV2> pass_book_entries_v2, int json_array_start_index) {

        float balance = 0;

        try {
            for (int i = json_array_start_index; i < json_array.length(); i++) {

                if (json_array_start_index == 0) {

                    if (json_array.getJSONObject(i).getString("from_account_id").equals(json_array.getJSONObject(i).getString("parent_account_id"))) {

                        balance = balance - Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                        Log.d(tag, "Event Date : " + json_array.getJSONObject(i).getString("event_date_time"));

                        pass_book_entries_v2.add(new PassBookEntryV2(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), "", json_array.getJSONObject(i).getString("to_account_name"), 0, Double.parseDouble(json_array.getJSONObject(i).getString("amount")), Float_Utils.roundOff_to_two_positions(balance), json_array.getJSONObject(i).getInt("from_account_id"), json_array.getJSONObject(i).getInt("to_account_id"), json_array.getJSONObject(i).getInt("id"), json_array.getJSONObject(i).getString("from_account_full_name"), json_array.getJSONObject(i).getString("to_account_full_name")));

                    } else {

                        balance = balance + Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                        pass_book_entries_v2.add(new PassBookEntryV2(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), json_array.getJSONObject(i).getString("from_account_name"), "", Double.parseDouble(json_array.getJSONObject(i).getString("amount")), 0, Float_Utils.roundOff_to_two_positions(balance), json_array.getJSONObject(i).getInt("from_account_id"), json_array.getJSONObject(i).getInt("to_account_id"), json_array.getJSONObject(i).getInt("id"), json_array.getJSONObject(i).getString("from_account_full_name"), json_array.getJSONObject(i).getString("to_account_full_name")));
                    }

                } else {

                    if (json_array.getJSONObject(i).getString("from_account_id").equals(currentAccountId)) {

                        balance = balance - Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                        Log.d(tag, "Event Date : " + json_array.getJSONObject(i).getString("event_date_time"));

                        pass_book_entries_v2.add(new PassBookEntryV2(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), json_array.getJSONObject(i).getString("to_account_name"), "", 0, Double.parseDouble(json_array.getJSONObject(i).getString("amount")), Float_Utils.roundOff_to_two_positions(balance), json_array.getJSONObject(i).getInt("from_account_id"), json_array.getJSONObject(i).getInt("to_account_id"), json_array.getJSONObject(i).getInt("id"), json_array.getJSONObject(i).getString("from_account_full_name"), json_array.getJSONObject(i).getString("to_account_full_name")));

                    } else {

                        balance = balance + Float.parseFloat(json_array.getJSONObject(i).getString("amount"));

                        pass_book_entries_v2.add(new PassBookEntryV2(mysql_date_time_format.parse(json_array.getJSONObject(i).getString("event_date_time")), json_array.getJSONObject(i).getString("particulars"), json_array.getJSONObject(i).getString("from_account_name"), "", Double.parseDouble(json_array.getJSONObject(i).getString("amount")), 0, Float_Utils.roundOff_to_two_positions(balance), json_array.getJSONObject(i).getInt("from_account_id"), json_array.getJSONObject(i).getInt("to_account_id"), json_array.getJSONObject(i).getInt("id"), json_array.getJSONObject(i).getString("from_account_full_name"), json_array.getJSONObject(i).getString("to_account_full_name")));
                    }
                }
            }

            Pass_Book_Utils.bindv2(passBookTableViewV2, currentActivity, pass_book_entries_v2);

        } catch (JSONException e) {

            Toast.makeText(currentActivity, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.d(tag, e.getLocalizedMessage());

        } catch (ParseException e) {

            Toast.makeText(currentActivity, "Date Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            Log.d(tag, e.getLocalizedMessage());
        }
    }

    @Override
    protected void onCancelled() {
        showProgress(false, currentActivity, progressBar, scrollView);
    }
}
