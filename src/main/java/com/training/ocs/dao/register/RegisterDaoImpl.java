package com.training.ocs.dao.register;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.exception.CliniqueException;
import com.training.ocs.util.UserImpl;

@Repository
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String registerUser(CredentialsBean p) throws CliniqueException{
		try{
			System.out.println("register: "+p);
			String ret="";
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createSQLQuery("select id_seq.nextval from dual");
			BigDecimal d=(BigDecimal) query.uniqueResult();
			String id=p.getProfileBean().getFirstName().substring(0,2)+String.valueOf(d);
			System.out.println("id: "+id);
			p.getProfileBean().setUserID(id);
			session.save(p);
			session.getTransaction().commit();
			session.close();
			return id;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String loginUser(CredentialsBean c) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			String message=new UserImpl().login(c, session);
			session.close();
			return message;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public CredentialsBean getuser(String id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query uquery=session.getNamedQuery("update_login_status");
			uquery.setString("id", id);
			int res=uquery.executeUpdate();
			//session.getTransaction().commit();
			Query query=session.getNamedQuery("verify");
			query.setString("id", id);
			CredentialsBean c=(CredentialsBean)query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			System.out.println("registerdao: "+c);
			return c;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int logoutUser(String userid) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.getNamedQuery("update_logout_status");
			query.setString("id", userid);
			int res=query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return res;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
