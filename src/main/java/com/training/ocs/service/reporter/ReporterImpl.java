package com.training.ocs.service.reporter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.dao.reporter.ReporterDao;
import com.training.ocs.exception.CliniqueException;

@Service
public class ReporterImpl implements Reporter{
	@Autowired
	ReporterDao reporterDao;
	
	@Override
	public List<AppointmentBean> intimateAdmin() throws CliniqueException{//Date date, String status
		// TODO Auto-generated method stub
		try {
			return reporterDao.intimateAdmin();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public List<DoctorBean> getAvailableDoctors(Date date, String slot) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return reporterDao.getAvailableDoctors(date, slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
}
