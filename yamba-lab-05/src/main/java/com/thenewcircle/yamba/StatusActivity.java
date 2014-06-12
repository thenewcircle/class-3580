package com.thenewcircle.yamba;

import android.app.Activity;
import android.os.Bundle;

public class StatusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if this activity was created before
        if (savedInstanceState == null) {
            // Create a fragment
            StatusFragment fragment = new StatusFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment,
                            fragment.getClass().getSimpleName()).commit();
        }
    }

}
