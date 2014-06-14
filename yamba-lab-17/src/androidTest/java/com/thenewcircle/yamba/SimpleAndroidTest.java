package com.thenewcircle.yamba;

import android.test.AndroidTestCase;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.List;

public class SimpleAndroidTest extends AndroidTestCase {

    public void testGetAllEvents() {

        YambaClient yambaClient = new YambaClient("student","password");

        try {
            List<YambaClient.Status> timeline = yambaClient.getTimeline(100);
            assertTrue(timeline.size() > 0);
        } catch (YambaClientException e) {
            e.printStackTrace();
            fail("Yamba call failed");
        }
    }

}
