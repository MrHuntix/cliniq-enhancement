package com.training.ocs.util;

import com.training.ocs.bean.*;

public interface Authentication {
	boolean authenticate(CredentialsBean user);
	String authorize(String userId);
	boolean changeLoginStatus(CredentialsBean user, int loginStatus);
}
