package com.dzlab.messaging.data;

public class Sms {
	
	public static String RECEIVED  = "content://sms/inbox";	
	public static String SENT      = "content://sms/sent";
	public static String CONVERSATIONS = "content://sms/conversations";
	
	public long threadId;
	public String address;
	public long date;
	public boolean wasRead;
	public String body;
	public boolean hasError;

	public String toString() {
		return "{'threadId':"+threadId+", 'address':'"+address+"', 'date':"+date+", 'wasRead':"+wasRead+", 'body':'"+body+"', 'hasError':"+hasError+"}";
	}
	
	public static final String COLUMN_ID         = "_id";
	public static final String COLUMN_THREAD_ID  = "thread_id";
	public static final String COLUMN_ADDRESS    = "address";
	public static final String COLUMN_DATE       = "date";
	public static final String COLUMN_READ       = "read";
	public static final String COLUMN_BODY       = "body";
	public static final String COLUMN_ERROR_CODE = "error_code";
	public static final String COLUMN_SEEN       = "seen";
	
}
