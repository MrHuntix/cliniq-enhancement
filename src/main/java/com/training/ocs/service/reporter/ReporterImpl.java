package com.training.ocs.service.reporter;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.dao.reporter.ReporterDao;
import com.training.ocs.exception.CliniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporterImpl implements Reporter{
	@Autowired
	ReporterDao reporterDao;

	@Override
	public List<Appointment> intimateAdmin() throws CliniqueException {
		try {
			return reporterDao.intimateAdmin();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}

	@Override
	public List<Doctor> getAvailableDoctors(java.util.Date date, String slot) throws CliniqueException {
		try {
			return reporterDao.getAvailableDoctors(date, slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			throw new CliniqueException(e.getMessage());
		}

	}
}
