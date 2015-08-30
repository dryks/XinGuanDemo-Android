package com.example.xinguandemo.adapter;

import java.util.List;

import com.example.xinguandemo.R;
import com.example.xinguandemo.dao.IUserDao;
import com.example.xinguandemo.dao.impl.UserDaoListImpl;
import com.example.xinguandemo.entity.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class UsersAdapter extends BaseAdapter{

	private Context context;
	private IUserDao userDao;
	private List<User> users;

	public UsersAdapter(Context context) {
		this.context = context;
		userDao = new UserDaoListImpl();
		users = userDao.findAllUsers();
	}

	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public Object getItem(int position) {
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.list_item, null);
			vh = new ViewHolder();
			vh.listItemName = (TextView) convertView.findViewById(R.id.tv_list_item_name);
			vh.listItemSex = (TextView) convertView.findViewById(R.id.tv_list_item_sex);
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.listItemName.setText(users.get(position).getName());
		//vh.listItemSex.setText(users.get(position).getHobbys().toString());≤‚ ‘
		return convertView;
	}
}

class ViewHolder{
	public TextView listItemSex;
	public TextView listItemName;
}

