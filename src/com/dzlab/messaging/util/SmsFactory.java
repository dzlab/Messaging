package com.dzlab.messaging.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;

import com.dzlab.messaging.data.Sms;

public class SmsFactory {
	
	protected Context mContext;
	
	public SmsFactory(Context context) {
		this.mContext = context; 
	}
	
	public static List<Sms> getSent(Context context) {
		query(context, Sms.SENT);
		return null;
	}
	public static void startAsyncQuery(Context context) {
		query(context, Sms.SENT);
		query(context, Sms.RECEIVED);		
	}
	
	public static void query(Context context, String uriString) {
		List<Sms> messages = new ArrayList<Sms>();
		Uri uriSMSURI = Uri.parse(uriString);	      
		Cursor cur = context.getContentResolver().query(uriSMSURI, null, null, null,null);
		System.out.println(uriString+": "+"Number of messages: " + cur.getCount());
		int columns = cur.getColumnCount();
	    //String sms = "";
	    while (cur.moveToNext()) {	
	    	Sms s = new Sms();
	    	s.threadId = cur.getLong(cur.getColumnIndex(Sms.COLUMN_THREAD_ID));
	    	s.date = cur.getLong(cur.getColumnIndex(Sms.COLUMN_DATE));
	    	s.address = cur.getString(cur.getColumnIndex(Sms.COLUMN_ADDRESS));
	    	boolean read = Boolean.valueOf(cur.getString(cur.getColumnIndex(Sms.COLUMN_READ)));
	    	boolean seen = Boolean.valueOf(cur.getString(cur.getColumnIndex(Sms.COLUMN_SEEN)));
	    	s.wasRead = read || seen;
	    	s.body = cur.getString(cur.getColumnIndex(Sms.COLUMN_BODY));
	    	int error_code = cur.getInt(cur.getColumnIndex(Sms.COLUMN_ERROR_CODE));
	    	s.hasError = (error_code != 0);
	    	System.out.println(uriString+": "+s);
	    	messages.add(s);	    	   	
	    }	    
	}
	
	/**send a short SMS message with a maximum length of 160 characters*/
	public void sendShortSMS(String phoneNumber, String message) {
		SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	    addToSent(phoneNumber, message);
	}

	/**send a long SMS message with a length more than 160 characters*/
	public void sendLongSMS(String phoneNumber, String message) {		 
	    SmsManager smsManager = SmsManager.getDefault();
	    ArrayList<String> parts = smsManager.divideMessage(message); 
	    smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
	    addToSent(phoneNumber, message);
	}
	
	protected void addToSent(String address, String body) {
		 ContentValues values = new ContentValues(); 		 
		 values.put("address", address); 
		 values.put("body", body);
		 mContext.getContentResolver().insert(Uri.parse(Sms.SENT), values);
	}
	
}
