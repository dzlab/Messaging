package com.dzlab.messaging.data;

import java.util.Date;


public class Contact {
	
	protected long _id;
	protected String name;
	protected String phone;
	protected Date birthday;

	public void setID(long iD) {
		this._id = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setNumber(String phone) {
		this.phone = phone;
	}

	public long getID() {
		return _id;
	}

	public String getName() {
		return name;
	}
	public String getNumber() {
		return phone;
	}

	@Override
	public String toString() {
		return "Contact [_id=" + _id + ", name=" + name + ", phone=" + phone + ", birthday=" + birthday + "]";
	}
}
