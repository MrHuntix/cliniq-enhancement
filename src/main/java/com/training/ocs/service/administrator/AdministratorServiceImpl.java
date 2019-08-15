package com.training.ocs.service.administrator;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.beans.Leave;
import com.training.ocs.beans.Schedule;
import com.training.ocs.dao.administrator.AdministratorDao;
import com.training.ocs.exception.CliniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	AdministratorDao administratorDao;

	@Override
	public String addDoctor(Doctor doctor) throws CliniqueException {
		try {
			return administratorDao.addDoctor(doctor);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}
	}

	@Override
	public String addSchedule(Schedule schedule) throws CliniqueException {
		try {
			return administratorDao.addSchedule(schedule);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Doctor> viewAllDoctors() throws CliniqueException {
		try {
			return administratorDao.viewAllDoctors();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public int removeDoctor(int doctorId) throws CliniqueException {
		try {
			return administratorDao.removeDoctor(doctorId);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public int applyLeave(Leave leave) throws CliniqueException {
		try {
			return administratorDao.applyLeave(leave);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public String updateDoctorOnLeave(int oldId, int newId, int aId) throws CliniqueException {
		try {
			return administratorDao.updateDoctorOnLeave(oldId, newId, aId);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public Doctor updateAndReplaceDoctorOnLeave(Date date, String specilization, int dId, String slot) throws CliniqueException {
		try {
			return administratorDao.updateAndReplaceDoctorOnLeave(date, specilization, dId, slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Appointment> getIntiamteReport() throws CliniqueException {
		try {
			return administratorDao.getIntiamteReport();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Appointment> getAppointmentsForDate(Date date) throws CliniqueException {
		try {
			return administratorDao.getAppointmentsForDate(date);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}
}
