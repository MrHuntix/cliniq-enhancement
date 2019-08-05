package com.training.ocs.util;

import org.hibernate.Query;
import org.hibernate.Session;
import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.bean.ProfileBean;

public class UserImpl implements User{

	@Override
	public String login(CredentialsBean credentialsBean,Session session) {
		System.out.println("user: "+credentialsBean);
		session.beginTransaction();
		Query query=session.getNamedQuery("verify");
		query.setString("id", credentialsBean.getProfileBean().getUserID());
		CredentialsBean c=(CredentialsBean)query.uniqueResult();
		System.out.println("db: "+c);
		if(c==null) {
			return "invalid";
		}else if(!c.getPassword().equals(credentialsBean.getPassword())) {
			return "fail";
		}
		return c.getUserType();
	}
}
