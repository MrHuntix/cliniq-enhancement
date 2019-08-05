package com.training.ocs.dao.reporter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.bean.ProfileBean;
import com.training.ocs.exception.CliniqueException;

@Repository
public class ReporterDaoImpl implements ReporterDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<AppointmentBean> intimateAdmin() throws CliniqueException{//Date date, String status
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments a where appointmentdate between "
					+ "(select leavefrom from ocs_tbl_leave l1 where a.doctorid = l1.doctorid) AND"
					+ "(select leaveto from ocs_tbl_leave l2 where a.doctorid = l2.doctorid )");
			query.addEntity(AppointmentBean.class);
			List<AppointmentBean> appointedPatient=query.list();
			session.close();
			System.out.println(appointedPatient);
			return appointedPatient;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public List<DoctorBean> getAvailableDoctors(Date date, String slot) throws CliniqueException{
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from ocs_tbl_doctor where doctorid not in(select doctorid from tbl_appointments where appointmentdate=:appdate and appointmenttime=:appslot) and doctorid not in(select doctorid from  ocs_tbl_leave where :appdate1 between leavefrom and leaveto)");
			query.setDate("appdate", date);
			query.setString("appslot", slot);
			query.setDate("appdate1", date);
			query.addEntity(DoctorBean.class);
			List doctors=query.list();
			session.close();
			System.out.println("reporter: "+doctors);
			return doctors;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
