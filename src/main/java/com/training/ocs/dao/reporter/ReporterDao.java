package com.training.ocs.dao.reporter;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;

import java.util.Date;
import java.util.List;

public interface ReporterDao {
	List<Appointment> intimateAdmin() throws CliniqueException;//Date date, String status implimented
	List<Doctor> getAvailableDoctors(Date date, String slot) throws CliniqueException;
}
