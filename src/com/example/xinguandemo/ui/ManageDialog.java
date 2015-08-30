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
import android.widget.TextView;
import android.widget.Toast;

public class ManageDialog extends Dialog /*implements android.view.View.OnClickListener*/{

	private User user;
	private TextView name;
	private EditText info1,info2,info3;
	private Context context;


	public ManageDialog(Context context,User user) {//
		super(context);
		this.context = context;
		this.user = user;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_manage);
		name = (TextView) findViewById(R.id.tv_dialog_manage_name);
		info1 = (EditText) findViewById(R.id.et_dialog_manage_info1);
		info2 = (EditText) findViewById(R.id.et_dialog_manage_info2);
		info3 = (EditText) findViewById(R.id.et_dialog_manage_info3);

		name.setText(user.getName());
		info1.setText(Integer.toString(user.getChinese()));
		info2.setText(Integer.toString(user.getMath()));
		info3.setText(Integer.toString(user.getEnglish()));		


		findViewById(R.id.btn_dialog_manage_comfirmtion).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new AlertDialog.Builder(context).setTitle(R.string.warning).
				setMessage(R.string.warning_messege).
				setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//将编辑后的信息写入数组中
						String modifyInfo1 = info1.getText().toString();
						String modifyInfo2= info2.getText().toString();
						String modifyInfo3 = info3.getText().toString();
						user.setChinese(Integer.valueOf(modifyInfo1).intValue());
						user.setMath(Integer.valueOf(modifyInfo2).intValue());
						user.setEnglish(Integer.valueOf(modifyInfo3).intValue());

						Toast.makeText(context, R.string.modify_success, Toast.LENGTH_SHORT).show();
						dismiss();
					}
				}).setPositiveButton(R.string.Cancle, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dismiss();
					}
				}).show();
			}
		});

		findViewById(R.id.btn_dialog_manage_cancle).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

}

