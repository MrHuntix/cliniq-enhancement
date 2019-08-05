package com.training.ocs.dao.patient;

import java.sql.Date;
import java.util.List;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.exception.CliniqueException;

public interface PatientDao {
	public List<DoctorBean> getAvailableDoctors() throws CliniqueException;
	public String checkAppointment(String doctor_id, Date appointment_date, String appointment_time) throws CliniqueException;
	public String bookAppointment(AppointmentBean a) throws CliniqueException;
	public List<AppointmentBean> getAppointments(String user_id) throws CliniqueException;
	public List<AppointmentBean> getAppointmentsByDate(Date date, String id) throws CliniqueException;
	public DoctorBean getDoctor(Date date, String time, String specilization) throws CliniqueException;
	public int updatePaientAilment(String pid, String ailment, String details, String history) throws CliniqueException;
}
