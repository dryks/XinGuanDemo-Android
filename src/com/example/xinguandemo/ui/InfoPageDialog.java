package com.example.xinguandemo.ui;

import com.example.xinguandemo.R;
import com.example.xinguandemo.dao.IUserDao;
import com.example.xinguandemo.dao.impl.UserDaoListImpl;
import com.example.xinguandemo.entity.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPageDialog extends Dialog {
	private IUserDao userDao;
	private User user;
	private TextView name,info1,info2,info3;
	private ImageView imageView;
	private Context context;

	public InfoPageDialog(Context context,User user) {
		super(context);
		this.context = context;
		this.user = user;	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_info_page);
		userDao = new UserDaoListImpl();
		
		name = (TextView) findViewById(R.id.tv_dialog_info_page_name);
		info1 = (TextView) findViewById(R.id.tv_dialog_info_page_info1);
		info2 = (TextView) findViewById(R.id.tv_dialog_info_page_info2);
		info3 = (TextView) findViewById(R.id.tv_dialog_info_page_info3);
		imageView = (ImageView) findViewById(R.id.iv_dialog_info_page_sex);
		
		name.setText(user.getName());
		info1.setText(Integer.toString(user.getChinese()));
		info2.setText(Integer.toString(user.getMath()));
		info3.setText(Integer.toString(user.getEnglish()));
		
		imageView.setImageResource(R.drawable.avatar_none);
//		if (user.getSex().equals(Sex.MAN)) {
//			imageView.setImageResource(R.drawable.avatar_man);
//		}else if (user.getSex().equals(Sex.WOMAN)) {
//			imageView.setImageResource(R.drawable.avatar_woman);
//		}else{
//			imageView.setImageResource(R.drawable.avatar_none);
//		}
		
		findViewById(R.id.btn_dialog_info_page_modifyInfo).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ManageDialog manageDialog = new ManageDialog(context,user);//
				manageDialog.setTitle(R.string.manage);
				manageDialog.show();
			}
		});
		findViewById(R.id.btn_dialog_info_page_cancle).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		findViewById(R.id.btn_dialog_info_page_dropUser).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(context).setTitle(R.string.warning).
				setMessage(R.string.warning_messege).
				setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						userDao.dropUser(user);
						Toast.makeText(context, R.string.success_in_droping_user, Toast.LENGTH_SHORT).show();
						dismiss();
					}
				}).setPositiveButton(R.string.Cancle, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dismiss();
					}
				}).show();
				
				
				ManageDialog manageDialog = new ManageDialog(context,user);//
				manageDialog.setTitle(R.string.manage);
				manageDialog.show();
			}
		});
	}
}
