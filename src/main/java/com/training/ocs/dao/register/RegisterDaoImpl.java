package com.training.ocs.dao.register;

import com.training.ocs.beans.Credentials;
import com.training.ocs.exception.CliniqueException;
import com.training.ocs.util.UserImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String registerUser(Credentials credentials) throws CliniqueException{
		try{
			System.out.println("register: "+credentials);
			String ret="";
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createSQLQuery("select id_seq.nextval from dual;");
			BigInteger d=(BigInteger) query.uniqueResult();
			int id= Integer.parseInt(String.valueOf(d));
			System.out.println("id: "+id);
			credentials.getProfileBean().setUserId(id);
			credentials.setCredentialId(id);
			session.save(credentials.getProfileBean());
			session.save(credentials);
			session.getTransaction().commit();
			System.out.println("s3");
			session.close();
			return String.valueOf(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String loginUser(Credentials credentials) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			String message=new UserImpl().login(credentials, session);
			session.close();
			return message;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public Credentials getuser(int id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			org.hibernate.query.Query<Credentials> credentialsQuery = session.createNamedQuery("update_login_status");
			credentialsQuery.setParameter("id",id);
			int res=credentialsQuery.executeUpdate();
			//session.getTransaction().commit();
			Query query=session.getNamedQuery("verify");
			query.setParameter("id", id);
			Credentials c=(Credentials)query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			System.out.println("registerdao: "+c);
			return c;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int logoutUser(int userId) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.getNamedQuery("update_logout_status");
			query.setParameter("id", userId);
			int res=query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return res;
		}catch(Exception e){
			e.printStackTrace();
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
