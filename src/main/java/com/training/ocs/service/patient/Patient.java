package com.training.ocs.service.patient;

import com.training.ocs.beans.Appointment;
import com.training.ocs.beans.Doctor;
import com.training.ocs.exception.CliniqueException;

import java.util.List;

public interface Patient {
	List<Doctor> getAvailableDoctors() throws CliniqueException;
	String checkAppointment(int doctor_id, java.util.Date appointment_date, String appointment_time) throws CliniqueException;
	String bookAppointment(Appointment appointment) throws CliniqueException;
	List<Appointment> getAppointments(int user_id) throws CliniqueException;
	List<Appointment> getAppointmentsByDate(java.util.Date date, int id) throws CliniqueException;
	Doctor getDoctor(java.util.Date date, String time, String specilization) throws CliniqueException;
	int updatePaientAilment(int pId, String ailment, String details, String history) throws CliniqueException;
}
