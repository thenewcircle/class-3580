package com.thenewcircle.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class SubActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Action bar stuff
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
