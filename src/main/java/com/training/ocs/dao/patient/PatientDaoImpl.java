package com.training.ocs.dao.patient;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.exception.CliniqueException;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<DoctorBean> getAvailableDoctors() throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from ocs_tbl_doctor where doctorid not in(select doctorid from ocs_tbl_leave)");
			query.addEntity(DoctorBean.class);
			List doctors=query.list();
			session.close();
			return doctors;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public String checkAppointment(String doctor_id,Date appointment_date,String appointment_time) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select count(doctorid) from tbl_appointments where appointmentdate=:date and appointmenttime=:time and doctorid=:id");
			query.setDate("date", appointment_date);
			query.setString("time", appointment_time);
			query.setString("id", doctor_id);
			BigDecimal c=(BigDecimal) query.uniqueResult();
			System.out.println("book:"+c);
			return String.valueOf(c);
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public String bookAppointment(AppointmentBean a) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(a);
			session.getTransaction().commit();
			session.close();
			return "appointment created for"+a.getAppointmentDate()+", on "+a.getAppointmentTime();
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	
	@Override
	public List<AppointmentBean> getAppointments(String user_id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments where patientid in (select patientid from ocs_tbl_patient where userid=:user_id) order by appointmentdate desc");
			query.addEntity(AppointmentBean.class);
			query.setString("user_id", user_id);
			List<AppointmentBean> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public DoctorBean getDoctor(Date date, String time, String specilization) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from (select * from ocs_tbl_doctor where specialization=:ailment and doctorid not in"
					+ "(select doctorid from tbl_appointments where appointmentdate=:dates and appointmenttime=:time) and "
					+ "doctorid not in (select doctorid from ocs_tbl_leave) order by yearsofexperience desc) where rownum=1");
			query.addEntity(DoctorBean.class);
			query.setString("ailment", specilization);
			query.setDate("dates", date);
			query.setString("time", time);
			DoctorBean d=(DoctorBean) query.uniqueResult();
			return d;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public List<AppointmentBean> getAppointmentsByDate(Date date, String id) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			org.hibernate.query.Query query=session.createQuery("from AppointmentBean where doctorid=:id and appointmentdate=:date");
			query.setString("id", id);
			query.setDate("date", date);
			List<AppointmentBean> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		
	}
	@Override
	public int updatePaientAilment(String pid, String ailment, String details, String history) throws CliniqueException{
		try{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query query=session.createQuery("update PatientBean set ailmenttype=:ailment, ailmentdetails=:details, diagnosishistory=:history where patientid=:pid");
			query.setString("ailment", ailment);
			query.setString("details", details);
			query.setString("history", history);
			query.setString("pid", pid);
			int res=query.executeUpdate();
			session.getTransaction().commit();
			return res;
		}catch(Exception e){
			throw new CliniqueException(e.getMessage());
		}
		// TODO Auto-generated method stub
		
	}

}
