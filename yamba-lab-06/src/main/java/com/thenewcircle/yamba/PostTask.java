package com.thenewcircle.yamba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;

/**
 * Created by jamesharmon on 6/11/14.
 */
class PostTask extends AsyncTask<String, Void, String> {

    private static final String TAG = PostTask.class.getSimpleName();
    private ProgressDialog progress;
    private Activity mContext;

    public PostTask(Activity context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(mContext, "Posting",
                "Please wait...");
        progress.setCancelable(true);
    }

    // Executes on a non-UI thread
    @Override
    protected String doInBackground(String... params) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
            String username = prefs.getString("username", "");
            String password = prefs.getString("password", "");

            // Check that username and password are not empty
            // If empty, Toast a message to set login info and bounce to
            // SettingActivity
            // Hint: TextUtils.
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                mContext.startActivity(
                        new Intent(mContext, SettingsActivity.class));
                return "Please update your username and password";
            }

            YambaClient cloud = new YambaClient(username, password);
            cloud.postStatus(params[0]);

            Log.d(TAG, "Successfully posted to the cloud: " + params[0]);
            return "Successfully posted";
        } catch (Exception e) {
            Log.e(TAG, "Failed to post to the cloud", e);
            e.printStackTrace();
            return "Failed to post";
        }
    }

    // Called after doInBackground() on UI thread
    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        if (mContext != null && result != null)
            Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();

    }

}
