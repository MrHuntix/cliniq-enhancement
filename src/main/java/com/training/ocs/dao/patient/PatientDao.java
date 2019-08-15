package com.training.ocs.dao.patient;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;

import java.util.Date;
import java.util.List;

public interface PatientDao {
	List<Doctor> getAvailableDoctors() throws CliniqueException;
	String checkAppointment(int doctor_id, Date appointment_date, String appointment_time) throws CliniqueException;
	String bookAppointment(Appointment appointment) throws CliniqueException;
	List<Appointment> getAppointments(int user_id) throws CliniqueException;
	List<Appointment> getAppointmentsByDate(Date date, int id) throws CliniqueException;
	Doctor getDoctor(Date date, String time, String specilization) throws CliniqueException;
	int updatePaientAilment(int pId, String ailment, String details, String history) throws CliniqueException;
}
