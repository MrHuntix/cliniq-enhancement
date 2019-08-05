package com.training.ocs.service.register;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.dao.register.RegisterDao;
import com.training.ocs.exception.CliniqueException;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDao registerDao;
	
	@Override
	public String registerUser(CredentialsBean p) throws CliniqueException{
		try {
			return registerDao.registerUser(p);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public String loginUser(CredentialsBean c) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return registerDao.loginUser(c);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public CredentialsBean getuser(String id) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return registerDao.getuser(id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public int logoutUser(String userid) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return registerDao.logoutUser(userid);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
}
