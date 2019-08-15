package com.training.ocs.service.administrator;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.beans.Leave;
import com.training.ocs.beans.Schedule;
import com.training.ocs.exception.CliniqueException;

import java.util.Date;
import java.util.List;

public interface AdministratorService {
	String addDoctor(Doctor doctor) throws CliniqueException; // returns SUCCESS or FAIL string
	String addSchedule(Schedule schedule) throws CliniqueException;
	List<Doctor> viewAllDoctors() throws CliniqueException;
	int removeDoctor(int doctorId) throws CliniqueException;
	int applyLeave(Leave leave) throws CliniqueException;
	String updateDoctorOnLeave(int oldId, int newId, int aId) throws CliniqueException;
	Doctor updateAndReplaceDoctorOnLeave(Date date, String specilization, int dId, String slot) throws CliniqueException;
	List<Appointment> getIntiamteReport() throws CliniqueException;
	List<Appointment> getAppointmentsForDate(Date date) throws CliniqueException;
}
