package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel> {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, int status);
}
