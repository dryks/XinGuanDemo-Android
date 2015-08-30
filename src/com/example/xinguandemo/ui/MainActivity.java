package com.example.xinguandemo.ui;

import com.example.xinguandemo.R;
import com.example.xinguandemo.adapter.UsersAdapter;
import com.example.xinguandemo.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.TextView;//测试用

public class MainActivity extends Activity implements OnItemClickListener {

	private TextView tv;
	private ListView usersListView;
//	public ImageView imageView;
	private UsersAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv_activity_main_greeting_to_admin);
		adapter = new UsersAdapter(this);
		usersListView = (ListView) findViewById(R.id.lv_activity_main);
//		imageView = (ImageView) findViewById(R.id.iv_activity_main_background_egg);
		usersListView.setAdapter(adapter);

		usersListView.setOnItemClickListener(this);//设置事件监听器

//		imageView.setImageResource(R.drawable.main_page_egg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		switch (id) {

		case R.id.item1:
			//Toast.makeText(this, "item1注册", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(MainActivity.this, RegActivity.class);
			startActivityForResult(i, 1);
			break;

		case R.id.item2:
			//Toast.makeText(this, "item2登陆", Toast.LENGTH_SHORT).show();
			LogInDialog logIn = new LogInDialog(this);
			logIn.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void logInSuccess() {
		adapter.notifyDataSetChanged();
		usersListView.setVisibility(View.VISIBLE);
		tv.setVisibility(View.VISIBLE);
//		imageView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		User user = (User) adapter.getItem(arg2);
		//Toast.makeText(this, String.format("姓名：%s", user.getName()), Toast.LENGTH_SHORT).show();

		InfoPageDialog infoPageDialog = new InfoPageDialog(this,user);//修改了user
		infoPageDialog.setTitle(R.string.browse);
		infoPageDialog.show();

	}
}
