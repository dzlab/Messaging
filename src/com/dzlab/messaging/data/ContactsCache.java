package com.dzlab.messaging.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;

import com.dzlab.messaging.util.ContactFactory;


public class ContactsCache {
	
	private static ContactsCache sContactCache;
	private static List<Contact> list;
	
	private ContactsCache() {
		list = new ArrayList<Contact>();
	}
	
	public static ContactsCache getInstance() {
		if(sContactCache == null)
			sContactCache = new ContactsCache();
		return sContactCache;
	}
	
	public ContactsCache init(Context context) {		
		ContactFactory.getAll(context);
		return getInstance();
	}
	
	public void add(Contact contact) {
		list.add(contact);
	}
	
	public Contact get(String name) {
		return null;
	}
	
	public Contact get(int position) {	 
		return list.get(position);
	}
	
	public int count() {
		return list.size();
	}
}
