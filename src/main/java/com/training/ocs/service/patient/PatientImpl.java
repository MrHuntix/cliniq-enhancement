package com.training.ocs.service.patient;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.dao.patient.PatientDao;
import com.training.ocs.exception.CliniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientImpl implements Patient{

	@Autowired
	PatientDao patientDao;

	@Override
	public List<Doctor> getAvailableDoctors() throws CliniqueException {
		try {
			return patientDao.getAvailableDoctors();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public String checkAppointment(int doctor_id, java.util.Date appointment_date, String appointment_time) throws CliniqueException {
		try {
			return patientDao.checkAppointment(doctor_id, appointment_date,appointment_time);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public String bookAppointment(Appointment appointment) throws CliniqueException {
		try {
			return patientDao.bookAppointment(appointment);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Appointment> getAppointments(int user_id) throws CliniqueException {
		try {
			return patientDao.getAppointments(user_id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Appointment> getAppointmentsByDate(java.util.Date date, int id) throws CliniqueException {
		try {
			return patientDao.getAppointmentsByDate(date, id);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public Doctor getDoctor(java.util.Date date, String time, String specilization) throws CliniqueException {
		try {
			return patientDao.getDoctor(date, time, specilization);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public int updatePaientAilment(int pId, String ailment, String details, String history) throws CliniqueException {
		try {
			return patientDao.updatePaientAilment(pId,ailment,details,history);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}
}
