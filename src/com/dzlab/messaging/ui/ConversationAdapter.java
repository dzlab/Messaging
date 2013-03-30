package com.dzlab.messaging.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dzlab.messaging.R;
import com.dzlab.messaging.data.Conversation;
import com.dzlab.messaging.data.Sms;
import com.dzlab.messaging.util.SmsFactory;

public class ConversationAdapter extends BaseAdapter {

	public static final String TAG = ConversationAdapter.class.getSimpleName();
	
	protected Context context;
	protected LayoutInflater inflater;
	protected List<Conversation> threads;
	protected Map<Long, Conversation> map;
	
	public ConversationAdapter(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.threads = new ArrayList<Conversation>();
		this.map = new HashMap<Long, Conversation>();
		read(SmsFactory.getSent(context), true);				
		read(SmsFactory.getReceived(context), false);			
	}
	
	protected void read(List<Sms> msgs, boolean in) {
		for(Sms s: msgs) {
			Conversation conversation = map.get(s.threadId); 
			if(conversation==null) {
				conversation = new Conversation();			
				conversation.threadId = s.threadId;
				threads.add(conversation);
				map.put(s.threadId, conversation);
				conversation.date = s.date;		
				conversation.recipient.setNumber(s.address);
			}
			if(in==true) {
				conversation.in.add(s);
			} else{
				conversation.out.add(s);				
			}
			conversation.hasUnreadMessages |= !s.wasRead;
			conversation.date = Math.max(conversation.date, s.date);
		}
	}

	@Override
	public int getCount() {
		return threads.size();
	}

	@Override
	public Object getItem(int position) {
		return threads.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.contacts_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.contact_name);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		Conversation conversation = threads.get(position);
		holder.name.setText(conversation.recipient.getName());
		if(holder.name.getText()==null || holder.name.getText().equals(""))
			holder.name.setText(conversation.recipient.getNumber());
		Log.i(TAG, "TextView is : "+holder.name.getText()+"\n"+conversation.toString());			
		return convertView;
	}

	public static class ViewHolder {
		TextView name;
	}
}
