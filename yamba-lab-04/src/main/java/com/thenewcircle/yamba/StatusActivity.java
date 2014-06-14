package com.thenewcircle.yamba;


import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LogPrinter;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity {
    private static final String TAG = StatusActivity.class.getSimpleName();
    private Button mButtonTweet;
    private EditText mTextStatus;
    private TextView mTextCount;
    private int mDefaultColor;
    final Activity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mButtonTweet = (Button) findViewById(R.id.status_button_tweet);
        mTextStatus = (EditText) findViewById(R.id.status_text);
        mTextCount = (TextView) findViewById(R.id.status_text_count);
        mTextCount.setText(Integer.toString(140));
        mDefaultColor = mTextCount.getTextColors().getDefaultColor();

        //final Activity me = this;

        mButtonTweet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ProgressDialog progress = new ProgressDialog(me);
                progress.setTitle("Processing");
                progress.setMessage("Please wait...");
                progress.setCancelable(true);
                progress.show();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //progress.dismiss();

                Looper.getMainLooper();

                Handler handler = new Handler();

                handler.postAtFrontOfQueue(new Runnable() {
                    public void run() {
                        showProgressDialog();
                    }
                });

                //showProgressDialog(me);

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

//        ProgressDialog progress = new ProgressDialog(StatusActivity.this);
//        progress.setTitle("Processing");
//        progress.setMessage("Please wait...");
//        progress.setCancelable(true);
//        progress.show();

        Looper looper = Looper.getMainLooper();
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.d(TAG, "This is the main looper");
        }
        Printer printer = new LogPrinter(Log.DEBUG, "LooperLog");
        looper.setMessageLogging(printer);

    }

    private void showProgressDialog() {
        Log.d(TAG, "onClicked");
        ProgressDialog progress = new ProgressDialog(me);
        progress.setTitle("Processing");
        progress.setMessage("Please wait...");
        progress.setCancelable(true);
        progress.show();

        try {
            Log.d(TAG, "Sleep for 3 seconds");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.dismiss();
    }

}
