package com.training.ocs.service.register;

import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.exception.CliniqueException;

public interface RegisterService {
	public String registerUser(CredentialsBean p) throws CliniqueException;
	public String loginUser(CredentialsBean c) throws CliniqueException;
	public CredentialsBean getuser(String id) throws CliniqueException;
	public int logoutUser(String userid) throws CliniqueException;
}
