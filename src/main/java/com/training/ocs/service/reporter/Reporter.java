package com.training.ocs.service.reporter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.training.ocs.bean.*;
import com.training.ocs.exception.CliniqueException;
public interface Reporter {
	List<AppointmentBean> intimateAdmin() throws CliniqueException;//Date date, String status
	List<DoctorBean> getAvailableDoctors(Date date, String slot)throws CliniqueException;
}
