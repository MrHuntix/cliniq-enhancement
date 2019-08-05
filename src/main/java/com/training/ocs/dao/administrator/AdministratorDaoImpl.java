package com.training.ocs.dao.administrator;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.bean.LeaveBean;
import com.training.ocs.bean.ScheduleBean;
import com.training.ocs.exception.CliniqueException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String addDoctor(DoctorBean doctorBean)  throws CliniqueException {
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(doctorBean);
			session.getTransaction().commit();
			session.close();
			return "doctor "+doctorBean.getDoctorName()+" successfully added";
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
		
	}

	@Override
	public List<DoctorBean> viewAllDoctors()  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from DoctorBean");
			List<DoctorBean> doctors=query.list();
			session.close();
			return doctors;
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int removeDoctor(String doctorID)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			ScheduleBean s=(ScheduleBean)session.get(ScheduleBean.class,Integer.parseInt(doctorID));
			DoctorBean d=(DoctorBean)session.get(DoctorBean.class,Integer.parseInt(doctorID));
			session.delete(d);
			session.getTransaction().commit();
			session.close();
			return d.getDoctorID();
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String addSchedule(ScheduleBean s)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(s);
			session.getTransaction().commit();
			session.close();
			return "doctor "+s.getDoctor().getDoctorName()+" has been added to the database";
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public int applyLeave(LeaveBean l)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			session.save(l);
			session.getTransaction().commit();
			session.close();
			return 1;
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}		
		
	}

	@Override
	public List<AppointmentBean> getIntiamteReport()  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from tbl_appointments a where appointmentdate between "
					+ "(select leavefrom from ocs_tbl_leave l1 where a.doctorid = l1.doctorid) AND"
					+ "(select leaveto from ocs_tbl_leave l2 where a.doctorid = l2.doctorid )");
			query.addEntity(AppointmentBean.class);
			List<AppointmentBean> intimateReport=query.list();
			session.close();
			return intimateReport;
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public DoctorBean updateAndReplaceDoctorOnLeave(java.sql.Date date, String specilization, String did,String slot)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			SQLQuery query=session.createSQLQuery("select * from (select * from ocs_tbl_doctor where doctorid not in "
					+ "(select doctorid from tbl_appointments where appointmentdate=:ad1 and appointmenttime=:slot) "
					+ "and doctorid not in(select doctorid from  ocs_tbl_leave where :ad2 between leavefrom and leaveto) "
					+ "and doctorid in(select doctorid from ocs_tbl_doctor where specialization=:splz) "
					+ "order by yearsofexperience) "
					+ "where rownum=1");
			query.addEntity(DoctorBean.class);
			query.setDate("ad1", date);
			query.setString("slot", slot);
			query.setDate("ad2", date);
			query.setString("splz", specilization);
			DoctorBean nd=(DoctorBean) query.uniqueResult();
			return nd;
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public List<AppointmentBean> getAppointmentsForDate(java.sql.Date date)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from AppointmentBean where appointmentdate=:date");
			query.setDate("date", date);
			List<AppointmentBean> appointments=query.list();
			session.close();
			return appointments;
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

	@Override
	public String updateDoctorOnLeave(String oldid, String newid, int aid)  throws CliniqueException{
		try {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Query q=session.createQuery("update AppointmentBean set doctorid=:newid where doctorid=:oldid");
			q.setString("newid", newid);
			q.setString("oldid", oldid);
			int result=q.executeUpdate();
			session.getTransaction().commit();
			session.close();	
			return String.valueOf(result);
		}catch(Exception e) {
			throw new CliniqueException(e.getMessage());
		}
		
	}

}
