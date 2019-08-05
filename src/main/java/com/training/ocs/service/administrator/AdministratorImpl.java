package com.training.ocs.service.administrator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.bean.LeaveBean;
import com.training.ocs.bean.PatientBean;
import com.training.ocs.bean.ScheduleBean;
import com.training.ocs.dao.administrator.AdministratorDao;
import com.training.ocs.dao.register.RegisterDao;
import com.training.ocs.exception.CliniqueException;

@Service
@Transactional
public class AdministratorImpl implements Administrator {

	@Autowired
	AdministratorDao administratorDao;
	
	@Override
	public String addDoctor(DoctorBean doctorBean) throws CliniqueException{
		try {
			return administratorDao.addDoctor(doctorBean);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public List<DoctorBean> viewAllDoctors() throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.viewAllDoctors();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public int removeDoctor(String doctorID) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.removeDoctor(doctorID);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public String addSchedule(ScheduleBean s) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.addSchedule(s);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public int applyLeave(LeaveBean l) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.applyLeave(l);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public List<AppointmentBean> getIntiamteReport() throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.getIntiamteReport();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public DoctorBean updateAndReplaceDoctorOnLeave(java.sql.Date date, String specilization, String did,String slot) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.updateAndReplaceDoctorOnLeave(date, specilization, did, slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public List<AppointmentBean> getAppointmentsForDate(java.sql.Date date) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.getAppointmentsForDate(date);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public String updateDoctorOnLeave(String oldid, String newid, int aid) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return administratorDao.updateDoctorOnLeave(oldid, newid, aid);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
}
