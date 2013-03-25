package com.dzlab.messaging.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;

import com.dzlab.messaging.data.Contact;
import com.dzlab.messaging.data.ContactsCache;

public class ContactFactory {

	public static void getAll(Context context) {
		ContactsCache contacts = ContactsCache.getInstance();		
		Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			Contact Contact = new Contact();
			int idxColumnID = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName._ID);
			long ID = cursor.getLong(idxColumnID);
			Contact.setID(ID);
			int idxColumnName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			Contact.setName(cursor.getString(idxColumnName));
			int idxColumnHasPhone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.HAS_PHONE_NUMBER);
			Contact.setBirthday(getBirthday(context, ID));
			boolean hasPhone = Boolean.parseBoolean(cursor.getString(idxColumnHasPhone));	
			if (hasPhone) {
				Contact.setNumber(findPhoneById(context, ID));
			}
			if(Contact.getName() != null)
				contacts.put(Contact.getName(), Contact);
		}	
		cursor.close();	
		Log.v("ContactProvider", "Number of contacts: " + contacts.count());		
	}
	
	public static String findPhoneById(Context context, long ID) {
		String phone = null;
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String where = ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ ID;
		Cursor cursor = context.getContentResolver().query(uri, null, where, null, null);	
		if (cursor.moveToNext()) {
			String column = PhoneLookup.NUMBER;
			//String column = ContactsContract.CommonDataKinds.Phone.NUMBER;
			int idxColumnNumber = cursor.getColumnIndex(column);
			String phoneNumber = cursor.getString(idxColumnNumber);
			phone = phoneNumber;
		}
		cursor.close();
		return phone;
	}
	
	public static Date getBirthday(Context context, long ID) {
		Date birthday = null;
		Cursor cursor = null;
		try {
			Uri uri = ContactsContract.Data.CONTENT_URI;
			String[] projection = new String[] {
					ContactsContract.CommonDataKinds.Event.CONTACT_ID,
					ContactsContract.CommonDataKinds.Event.START_DATE
			};
			
			String where =
					ContactsContract.CommonDataKinds.Event.CONTACT_ID + "=" + ID + " AND " +
							ContactsContract.Data.MIMETYPE + "= ? AND " +
							ContactsContract.CommonDataKinds.Event.TYPE + "=" + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
			String[] selectionArgs = new String[] {ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE};
			String sortOrder = null;	
			cursor = context.getContentResolver().query(uri, projection, where, selectionArgs, sortOrder);
			
			if (cursor.moveToNext()) {
				int idxColumnBirthday = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);	
				if(idxColumnBirthday!=-1) {
					String dateStr = cursor.getString(idxColumnBirthday);	
					SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
					birthday = curFormater.parse(dateStr);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cursor.close();
		}			
		return birthday;
	}
}
