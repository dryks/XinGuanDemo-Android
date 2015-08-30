package com.example.xinguandemo.dao;

import java.util.List;

import com.example.xinguandemo.entity.User;

public interface IUserDao {
	public List<User> findAllUsers();
	public void addUser(User user);
	public User findUserByNameAndPwd(String name,String pwd);
	public User findUserByName(String name);
	public void modify(User user);
	public void dropUserByName(String name);
	public void dropUser(User user);
}
