package com.training.ocs.service.administrator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.training.ocs.bean.*;
import com.training.ocs.exception.CliniqueException;

public interface Administrator {
	String addDoctor(DoctorBean doctorBean) throws CliniqueException; // returns SUCCESS or FAIL string
	String addSchedule(ScheduleBean s) throws CliniqueException;
	List<DoctorBean> viewAllDoctors() throws CliniqueException;
	int removeDoctor(String doctorID) throws CliniqueException;
	int applyLeave(LeaveBean l) throws CliniqueException;
	String updateDoctorOnLeave(String oldid, String newid, int aid) throws CliniqueException;
	public DoctorBean updateAndReplaceDoctorOnLeave(java.sql.Date date, String specilization, String did, String slot) throws CliniqueException;
	List<AppointmentBean> getIntiamteReport() throws CliniqueException;
	List<AppointmentBean> getAppointmentsForDate(java.sql.Date date) throws CliniqueException;
}
