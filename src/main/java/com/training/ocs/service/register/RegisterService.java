package com.training.ocs.service.register;

import com.training.ocs.beans.Credentials;
import com.training.ocs.exception.CliniqueException;

public interface RegisterService {
	String registerUser(Credentials credentials) throws CliniqueException;
	String loginUser(Credentials credentials) throws CliniqueException;
	int logoutUser(int userId) throws CliniqueException;
	Credentials getuser(int id) throws CliniqueException;
}
