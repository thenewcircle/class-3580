package com.thenewcircle.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.List;

public class RefreshService extends IntentService {

    private static final String TAG = RefreshService.class.getSimpleName() ;

    public RefreshService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int MAX_POSTS = 100;

        YambaClient yambaClient = new YambaClient("student","password");

        try {
            List<YambaClient.Status> timeline = yambaClient.getTimeline(MAX_POSTS);
            for (YambaClient.Status status : timeline) {
                Log.d(TAG, String.format("%s: %s", status.getUser(), status.getMessage()));
            }
        } catch (YambaClientException e) {
            e.printStackTrace();
        }
    }
}
