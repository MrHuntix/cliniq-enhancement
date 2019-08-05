package com.training.ocs.util;

import org.hibernate.Session;

import com.training.ocs.bean.*;
public interface User {
	String login(CredentialsBean credentialsBean, Session session);

}
