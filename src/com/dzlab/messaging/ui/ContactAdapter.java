package com.dzlab.messaging.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dzlab.messaging.R;
import com.dzlab.messaging.data.Contact;
import com.dzlab.messaging.data.ContactsCache;

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
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.contacts_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById((R.id.contact_name));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Contact c = (Contact) getItem(position);
		holder.name.setText(c.getName());

		return convertView;
	}

	public static class ViewHolder {
		TextView name;
	}
}
