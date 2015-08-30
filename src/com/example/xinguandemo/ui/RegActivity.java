package com.example.xinguandemo.ui;

import com.example.xinguandemo.R;
import com.example.xinguandemo.dao.IUserDao;
import com.example.xinguandemo.dao.impl.UserDaoListImpl;
import com.example.xinguandemo.entity.Sex;
import com.example.xinguandemo.entity.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegActivity extends Activity implements OnClickListener{

	private EditText etName,etAge,etPwd,etPwtAgain,etChinese,etMath,etEnglish;
	private Button btnReg,btnCancle;
	@SuppressWarnings("unused")
	private RadioButton rbMan,rbWoman;//
	
	private CheckBox[] cbHobbys;
	private int[] cbHabbysIds = {
			R.id.cb_activity_reg_hobby1,
			R.id.cb_activity_reg_hobby2,
			R.id.cb_activity_reg_hobby3
	};

	private IUserDao userDao;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		userDao = new UserDaoListImpl();

		cbHobbys = new CheckBox[cbHabbysIds.length];
		for (int i = 0; i < cbHabbysIds.length; i++) {
			cbHobbys[i] = (CheckBox) findViewById(cbHabbysIds[i]);
		}

		etName = (EditText) findViewById(R.id.et_acyicity_reg_name);
		etPwd = (EditText) findViewById(R.id.et_acyicity_reg_password);
		etPwtAgain = (EditText) findViewById(R.id.et_acyicity_reg_passwordAgain);
		etAge = (EditText) findViewById(R.id.et_activity_reg_age);
		etChinese = (EditText) findViewById(R.id.et_activity_reg_chinese);
		etMath = (EditText) findViewById(R.id.et_activity_reg_math);
		etEnglish = (EditText) findViewById(R.id.et_activity_reg_english);
		btnCancle = (Button) findViewById(R.id.btn_activity_reg_cancle);
		btnReg = (Button) findViewById(R.id.btn_activity_reg_register);
		rbMan = (RadioButton) findViewById(R.id.rb_activity_reg_man);
		rbWoman = (RadioButton) findViewById(R.id.rb_activity_reg_woman);
		btnCancle.setOnClickListener(this);
		btnReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_activity_reg_register:
			
			if (!etName.getText().toString().equals("")
					&& !etPwd.getText().toString().equals("")
					&& !etPwtAgain.getText().toString().equals("")
					&& etPwd.getText().toString().equals(etPwtAgain.getText().toString())) {
				user = new User(etName.getText().toString(), etPwd.getText().toString());
				if(rbWoman.isChecked()){
					user.setSex(Sex.WOMAN);
				}else {
					user.setSex(Sex.MAN);
				}
				
				
				if (!etAge.getText().toString().equals("")) {
					user.setAge(Integer.parseInt(etAge.getText().toString()));
				}

				if (!etChinese.getText().toString().equals("")) {
					user.setChinese(Integer.parseInt(etChinese.getText().toString()));
				}

				if (!etMath.getText().toString().equals("")) {
					user.setMath(Integer.parseInt(etMath.getText().toString()));
				}

				if(!etEnglish.getText().toString().equals("")){
					user.setEnglish(Integer.parseInt(etEnglish.getText().toString()));
				}
				
				StringBuffer temp = new StringBuffer();
				for (int j = 0; j < FOCUSED_STATE_SET.length; j++) {
					if (cbHobbys[j].isChecked()) {
						temp.append(cbHobbys[j].getText().toString());
					}
				}
				String str = temp.toString();
				
				if (str != null) {
					user.setHobbys(str);
				}
				userDao.addUser(user);
				Toast.makeText(this, R.string.successIOnRegister, Toast.LENGTH_SHORT).show();
				finish();
			}else {
				Toast.makeText(this, R.string.register_fail, Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_activity_reg_cancle:
			finish();
			break;
		}
	}
}
