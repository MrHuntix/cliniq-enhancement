package com.training.ocs.util;

import com.training.ocs.bean.CredentialsBean;

public class AuthenticationImpl implements Authentication {

	@Override
	public boolean authenticate(CredentialsBean user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String authorize(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeLoginStatus(CredentialsBean user, int loginStatus) {
		// TODO Auto-generated method stub
		return false;
	}

}
