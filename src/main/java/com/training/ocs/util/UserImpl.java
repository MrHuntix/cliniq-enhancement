package com.training.ocs.util;

import com.training.ocs.beans.Credentials;
import org.hibernate.Session;

public class UserImpl implements User{

	@Override
	public String login(Credentials credentials, Session session) {
		System.out.println("user: "+credentials);
		session.beginTransaction();
		org.hibernate.query.Query<Credentials> credentialsQuery = session.createNamedQuery("verify", Credentials.class);
		credentialsQuery.setParameter("id",credentials.getCredentialId());
		Credentials c=credentialsQuery.getSingleResult();
		System.out.println("db: "+c);
		if(c==null) {
			return "invalid";
		}else if(!c.getPassword().equals(credentials.getPassword())) {
			return "fail";
		}
		return c.getUserType();
	}
}
