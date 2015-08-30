package com.example.xinguandemo.ui;


import com.example.xinguandemo.R;
import com.example.xinguandemo.entity.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xinguandemo.dao.IUserDao;
import com.example.xinguandemo.dao.impl.UserDaoListImpl;

public class LogInDialog extends Dialog implements android.view.View.OnClickListener{
	
	private MainActivity context;
	private EditText etName,etPassword;
	private IUserDao userDao;
	
	public LogInDialog(Context context) {
		super(context);
		this.context = (MainActivity) context;
		//��һ�зǳ���Ҫ
		userDao = new UserDaoListImpl();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_login);
		this.setTitle(R.string.Login);
		
		findViewById(R.id.btn_dialog_login_login).setOnClickListener(this);
		findViewById(R.id.btn_dialog_login_cancle).setOnClickListener(this);
		etName = (EditText) findViewById(R.id.et_dialog_login_name);
		etPassword = (EditText) findViewById(R.id.et_dialog_login_password);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_dialog_login_login:
			//Toast.makeText(context, R.string.Login, Toast.LENGTH_SHORT).show();
			String name = etName.getText().toString();
			String pwd = etPassword.getText().toString();
			User user = null;
			//TODO ����һ�д�����ȷ
			user = userDao.findUserByNameAndPwd(name, pwd);//����userDao
			
			if (user == null) {
				Toast.makeText(context, R.string.login_failure, Toast.LENGTH_SHORT).show();
			}else {
				
				if (user.getName().equals("admin") && user.getPwd().equals("123456")) {
					new AlertDialog.Builder(context).setTitle(R.string.welcome).setMessage(R.string.alertDialog_greeting_to_admin).setPositiveButton(R.string.i_have_known_it, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							context.logInSuccess();
							dismiss();
						}
					}).show();
				}else {
					Toast.makeText(context, String.format("%s��%s", R.string.greeting,name), Toast.LENGTH_SHORT).show();
					//context.imageView.setVisibility(View.INVISIBLE);
					new AlertDialog.Builder(context).setTitle("������Ϣ").setMessage(String.format("�û�����%s\n���ģ�%d\n��ѧ��%d\nӢ�%d\n", user.getName(),user.getChinese(),user.getMath(),user.getEnglish())).show();
				}
				
			}
			break;

		case R.id.btn_dialog_login_cancle:
			Toast.makeText(context, R.string.Cancle, Toast.LENGTH_SHORT).show();
			dismiss();
			break;
		}
	}
}
