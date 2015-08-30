package com.example.xinguandemo.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.xinguandemo.dao.IUserDao;
import com.example.xinguandemo.entity.User;

public class UserDaoListImpl implements IUserDao{

	//静态，以防每次使用都产生新的数组
	private static List<User> users = new ArrayList<User>();
	static{
		users.add(new User("admin", "123456"));
		users.add(new User("lilinmao", "123456"));
		users.add(new User("tiger", "123456"));
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public User findUserByNameAndPwd(String name, String pwd) {
		for (User user : users) {
			if (name.equals(user.getName()) && pwd.equals(user.getPwd())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findUserByName(String name) {
		for (User user : users) {
			if (name.equals(user.getName())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void modify(User user) {
		for (User tempUser : users) {
			if (user.getName().equals(tempUser.getName())) {
				//TODO 更新修改所有user的成员属性
				tempUser.setAge(user.getAge());
				tempUser.setSex(user.getSex());


			}
		}
	}

	@Override
	public void dropUserByName(String name) {
		//使用迭代器
		Iterator<User> users_ite = users.iterator();//生成迭代器
		while (users_ite.hasNext()) {
			User user = users_ite.next();
			if (name.equals(user.getName())) {
				users_ite.remove();//使用迭代器进行删除
			}
		}
	}

	@Override
	public void dropUser(User user) {
		//使用迭代器
		Iterator<User> users_ite = users.iterator();//生成迭代器
		while (users_ite.hasNext()) {
			User userInList = users_ite.next();
			if (user.equals(userInList)) {
				users_ite.remove();//使用迭代器进行删除
			}
		}
	}

}
