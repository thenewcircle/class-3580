package com.thenewcircle.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity {

    private static final String TAG = StatusActivity.class.getSimpleName();
    private Button mButtonTweet;
	private EditText mTextStatus;
	private TextView mTextCount;
	private int mDefaultColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		mButtonTweet = (Button) findViewById(R.id.status_button_tweet);
		mTextStatus = (EditText) findViewById(R.id.status_text);
		mTextCount = (TextView) findViewById(R.id.status_text_count);
		mTextCount.setText(Integer.toString(140));
		mDefaultColor = mTextCount.getTextColors().getDefaultColor();

	}

}
