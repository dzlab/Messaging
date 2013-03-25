package com.dzlab.messaging.ui;

import com.dzlab.messaging.data.ContactsCache;
import com.dzlab.messaging.util.ContactFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContactAdapter extends BaseAdapter {

	protected Context context;
	protected LayoutInflater inflater;
	protected ContactsCache cache;
		
	public ContactAdapter(Context context) {
		this.context  = context;
		this.inflater = LayoutInflater.from(context);
		cache = ContactsCache.getInstance().init(context);		
	}
	
	@Override
	public int getCount() {
		return cache.count();
	}

	@Override
	public Object getItem(int position) {		
		return cache.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
