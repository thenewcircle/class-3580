package com.thenewcircle.yamba;

import android.app.IntentService;
import android.content.Intent;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.List;

public class FetchTimelineDataService extends IntentService {

    public FetchTimelineDataService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int MAX_POSTS = 100;

        YambaClient yambaClient = new YambaClient("student","password");

        try {
            List<YambaClient.Status> timeline = yambaClient.getTimeline(MAX_POSTS);
        } catch (YambaClientException e) {
            e.printStackTrace();
        }
    }
}
