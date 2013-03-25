package com.dzlab.messaging.data;

import java.util.HashMap;

import android.content.Context;

import com.dzlab.messaging.util.ContactFactory;


public class ContactsCache {
	
	private static ContactsCache sContactCache;
	private static HashMap<String, Contact> map;
	
	private ContactsCache() {
		map = new HashMap<String, Contact>();
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
	
	public void put(String name, Contact contact) {
		map.put(name, contact);
	}
	
	public Contact get(String name) {
		return map.get(name);
	}
	
	public Contact get(int position) {
		
		return null;
	}
	
	public int count() {
		return map.size();
	}
}
