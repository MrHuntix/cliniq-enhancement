package com.training.ocs.controller;//package com.training.ocs.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.portlet.ModelAndView;
//
//import com.training.ocs.bean.AppointmentBean;
//import com.training.ocs.bean.DoctorBean;
//import com.training.ocs.bean.PatientBean;
//import com.training.ocs.service.patient.Patient;
//
//@Controller
//public class PatientController {
//	
//	@Autowired
//	Patient patientService;
//	
//	@RequestMapping(value="confirmation",method=RequestMethod.POST)
//	public ModelAndView bookapp(@RequestParam("date")java.sql.Date date,@RequestParam("slot")String slot,@RequestParam("did")String id
//			,@RequestParam("ailment")String ailment,@RequestParam("details")String details,
//			@RequestParam("history")String history,@RequestParam("pid")String pid){
//		//System.out.println("date: "+date+", slot: "+slot+", id: "+id+", ailment: "+ailment+", history: "+history+", details: "+details+", patientid: "+pid);
//		System.out.println("date: "+date.toString());
//		String c=patientService.checkAppointment(id, date, slot);
//		System.out.println("test controller success fail: "+c);
//		String message="";
//		if(c.equals("1"))
//			message="slot "+slot+" for date "+date+" is not available";
//		else{
//			AppointmentBean a=new AppointmentBean();
//			PatientBean p=new PatientBean();
//			p.setUserID(pid);
//			p.setAppointmentDate(date);
//			p.setAilmentDetails(details);
//			p.setAilmentType(ailment);
//			p.setDiagnosisHistory(history);
//			a.setPatient(p);
//			a.setDoctorID(id);
//			a.setAppointmentDate(date);
//			a.setAppointmentTime(slot);
//			System.out.println("appointment: "+a);
//			message=patientService.bookAppointment(a);
//		}
//		return new ModelAndView("patient","status",message);
//	}
//
//	
//	@RequestMapping(value="bookappointments",method=RequestMethod.POST)
//	public ModelAndView bookappointment(@RequestParam("date")java.sql.Date date,@RequestParam("slot")String slot,
//			@RequestParam("ailment")String ailment,@RequestParam("details")String details,
//			@RequestParam("history")String history,@RequestParam("pid")String patient){
//		ModelAndView mv=new ModelAndView();
//		String msg="";
//		DoctorBean doctorBean=patientService.getDoctor(date, slot, ailment);
//		System.out.println("doctor result: "+doctorBean);
//		if(doctorBean==null) {
//			msg="no doctor available today( "+date+" ) for the time slot ( "+slot+" ).";
//		}else {
//			PatientBean p=new PatientBean();
//			p.setUserID(patient);
//			p.setAppointmentDate(date);
//			p.setAilmentType(ailment);
//			p.setAilmentDetails(details);
//			p.setDiagnosisHistory(history);
//			AppointmentBean a=new AppointmentBean();
//			a.setDoctorID(String.valueOf(doctorBean.getDoctorID()));
//			a.setPatient(p);
//			a.setAppointmentDate(date);
//			a.setAppointmentTime(slot);
//			patientService.bookAppointment(a);
//			msg="appointment booked successfully for the date "+date+" with doctor "+doctorBean.getDoctorName();
//		}
//		mv.addObject("bookingmsg", msg);
//		mv.setViewName("patient");
//		return mv;
//	}
//	
//	@RequestMapping(value="showappointments")
//	public ModelAndView showappointments(@RequestParam("patientId")String id) {
//		System.out.println("id: "+id);
//		List<AppointmentBean> apps=patientService.getAppointments(id);
//		System.out.println("appointments: "+apps);
//		return new ModelAndView("patient","appointments",apps);
//	}
//	
//	@RequestMapping(value="getappointmentsfordate",method=RequestMethod.POST)
//	public ModelAndView getAppointmentsForDate(@RequestParam("did")String id,@RequestParam("date")java.sql.Date date) {
//
//		return new ModelAndView("patient","doctorapp",patientService.getAppointmentsByDate(date, id));
//	}
//}
