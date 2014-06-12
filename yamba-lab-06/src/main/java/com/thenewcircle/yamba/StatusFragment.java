package com.thenewcircle.yamba;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusFragment extends Fragment {
	private static final String TAG = StatusFragment.class.getSimpleName();
    private Button mButtonTweet;
	private EditText mTextStatus;
	private TextView mTextCount;
	private int mDefaultColor;
    PostTask postTask;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_status, null, false);

		mButtonTweet = (Button) v.findViewById(R.id.status_button_tweet);
		mTextStatus = (EditText) v.findViewById(R.id.status_text);
		mTextCount = (TextView) v.findViewById(R.id.status_text_count);
		mTextCount.setText(Integer.toString(140));
		mDefaultColor = mTextCount.getTextColors().getDefaultColor();

		mButtonTweet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                if (postTask != null) {
                    String status = mTextStatus.getText().toString();
                    postTask = new PostTask(getActivity());
                    postTask.execute(status);
                    Log.d(TAG, "onClicked");
                }
			}

		});

		mTextStatus.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				int count = 140 - s.length();
				mTextCount.setText(Integer.toString(count));

				if (count < 50) {
					mTextCount.setTextColor(Color.RED);
				} else {
					mTextCount.setTextColor(mDefaultColor);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

		});

		Log.d(TAG, "onCreated");

		return v;
	}

    @Override
    public void onStop() {
        super.onStop();
        if (postTask != null) {
            postTask.cancel(true);
        }
    }
}
