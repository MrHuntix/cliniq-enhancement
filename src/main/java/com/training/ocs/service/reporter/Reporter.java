package com.training.ocs.service.reporter;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;

import java.util.List;
public interface Reporter {
	List<Appointment> intimateAdmin() throws CliniqueException;//Date date, String status implimented
	List<Doctor> getAvailableDoctors(java.util.Date date, String slot) throws CliniqueException;
}
