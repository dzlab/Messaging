package com.dzlab.messaging.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.dzlab.messaging.R;
import com.dzlab.messaging.data.Sms;
import com.dzlab.messaging.util.SmsFactory;

public class MainActivity extends Activity {

	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView) findViewById(R.id.list);
		
		SmsFactory.getSent(MainActivity.this);
		
		SmsFactory.query(MainActivity.this, Sms.SENT);
		SmsFactory.query(MainActivity.this, Sms.RECEIVED);
		//mSmsHelper.readSMS(SmsFactory.CONVERSATIONS);
		/*
	    ((Button) findViewById(R.id.btnSendLongSms)).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			    String message = "Hello World! Now we are going to demonstrate " + 
			            "how to send a message with more than 160 characters from your Android application.";
				//mSmsHelper.sendLongSMS("0123456789", message);
			}
		});
	    ((Button) findViewById(R.id.btnSendShortSms)).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//mSmsHelper.sendShortSMS("0123456789", "Hello World!");
			}
		});
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
