package com.training.ocs.dao.reporter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.bean.ProfileBean;
import com.training.ocs.exception.CliniqueException;

public interface ReporterDao {
	List<AppointmentBean> intimateAdmin() throws CliniqueException;//Date date, String status implimented
	List<DoctorBean> getAvailableDoctors(Date date, String slot) throws CliniqueException;
}
