package com.training.ocs.dao.register;

import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.exception.CliniqueException;

public interface RegisterDao {
	public String registerUser(CredentialsBean p) throws CliniqueException;
	public String loginUser(CredentialsBean c) throws CliniqueException;
	public int logoutUser(String userid) throws CliniqueException;
	public CredentialsBean getuser(String id) throws CliniqueException;
	//public CredentialsBean getUpdateduser(String id);
}
