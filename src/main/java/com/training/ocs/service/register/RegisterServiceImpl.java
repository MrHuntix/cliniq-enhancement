package com.training.ocs.service.register;

import com.training.ocs.beans.Credentials;
import com.training.ocs.dao.register.RegisterDao;
import com.training.ocs.exception.CliniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDao registerDao;

	@Override
	public String registerUser(Credentials credentials) throws CliniqueException {
		try {
			return registerDao.registerUser(credentials);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public String loginUser(Credentials credentials) throws CliniqueException {
		try {
			return registerDao.loginUser(credentials);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public int logoutUser(int userId) throws CliniqueException {
		try {
			return registerDao.logoutUser(userId);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public Credentials getuser(int id) throws CliniqueException {
		try {
			return registerDao.getuser(id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}
}
