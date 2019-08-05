package com.training.ocs.service.patient;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.dao.patient.PatientDao;
import com.training.ocs.exception.CliniqueException;

@Service
@Transactional
public class PatientImpl implements Patient{

	@Autowired
	PatientDao patientDao;
	@Override
	public List<DoctorBean> getAvailableDoctors() throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.getAvailableDoctors();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public String checkAppointment(String doctor_id, Date appointment_date, String appointment_time) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.checkAppointment(doctor_id, appointment_date, appointment_time);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public String bookAppointment(AppointmentBean a) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.bookAppointment(a);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public List<AppointmentBean> getAppointments(String user_id) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.getAppointments(user_id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public DoctorBean getDoctor(Date date, String time, String specilization) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.getDoctor(date, time, specilization);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public List<AppointmentBean> getAppointmentsByDate(Date date, String id) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.getAppointmentsByDate(date, id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}
	@Override
	public int updatePaientAilment(String pid, String ailment, String details, String history) throws CliniqueException{
		// TODO Auto-generated method stub
		try {
			return patientDao.updatePaientAilment(pid, ailment, details, history);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

}
