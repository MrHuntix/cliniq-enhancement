package com.training.ocs.controller;

import com.training.ocs.beans.*;
import com.training.ocs.exception.CliniqueException;
import com.training.ocs.service.administrator.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
	
	@Autowired
    AdministratorService administrator;
	
	@RequestMapping(value="gotoadmin")
	public ModelAndView goHomeAdmin(HttpSession session) {
		Credentials sessionbean=(Credentials) session.getAttribute("profile");
		System.out.println("current session: "+sessionbean);
		if(sessionbean==null)
			return new ModelAndView("index");
		return new ModelAndView("administrator");
	}
	
	@RequestMapping(value= "doctors")
	public ModelAndView showDoctors(HttpSession session) {
		Credentials sessionbean=(Credentials) session.getAttribute("profile");
		System.out.println("current session: "+sessionbean);
		if(sessionbean==null)
			return new ModelAndView("index");
		List<Doctor> doctors = null;
		try {
			doctors = administrator.viewAllDoctors();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(doctors.size()==0)
			return new ModelAndView("administrator","adddocresult","no doctors are available.");
		return new ModelAndView("doctor","doctors",doctors);
	}
	
	@RequestMapping(value="adddoc",method=RequestMethod.POST)
	public ModelAndView addDoctors(@Valid @ModelAttribute("doctor")Doctor d,BindingResult b,@RequestParam("days")String days,@RequestParam("slots")String slots) {
		if(b.hasErrors())
			System.out.println(b.toString());
		System.out.println("doctor: "+d);
		Schedule s=new Schedule();
		s.setAvailableDays(days);
		s.setSlots(slots);
		s.setDoctorBean(d);
		String result;
		try {
			result = administrator.addSchedule(s);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		return new ModelAndView("administrator","adddocresult",result);
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public ModelAndView deleteDoctor(@RequestParam("id")String id) {
		System.out.println("id: "+id);
		int result;
		try {
			result = administrator.removeDoctor(Integer.parseInt(id));
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(result==0)
			return new ModelAndView("administrator","adddocresult","cannot delete doctor at this time.");
		return new ModelAndView("administrator","adddocresult","doctor "+id+" successfully deleted.");
	}
	
	@RequestMapping(value="showappointments",method=RequestMethod.POST)
	public ModelAndView showAppointments(@RequestParam("adate")Date date){		
		System.out.println("showing appointments for date: "+date);
		List<Appointment> appointments = null;
		try {
			appointments = administrator.getAppointmentsForDate(date);
		} catch (CliniqueException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(appointments.size()==0)
			return new ModelAndView("administrator","adddocresult","no appointments have been scheduled yet for "+date);
		return new ModelAndView("administrator","appointmentsdate",appointments);
	}
//	@RequestMapping(value="getuserappointments")
//	public ModelAndView getUserAppointments(@RequestParam("userId")String uid){
//		System.out.println("user id: "+uid);
//		List<AppointmentBean> appointments=administrator.getAppointmentForUser(uid);
//		System.out.println("appointments: "+appointments);
//		return new ModelAndView("administrator","appointmentsuser",appointments);
//	}
	
	@RequestMapping(value="applyleave",method=RequestMethod.POST)
	public ModelAndView applyLeave(@RequestParam("did")String did,@RequestParam("fromdate")Date fromdate,
			@RequestParam("todate")Date todate,@RequestParam("reason")String reason,@RequestParam("status")String status){
		System.out.println("doctor id: "+did+", from date: "+fromdate+", to date: "+todate+", reason: "+reason+", status: "+status);
		Leave l=new Leave();
		l.setDoctorId(did);
		l.setLeaveFrom(fromdate);
		l.setLeaveTo(todate);
		l.setReason(reason);
		l.setStatus(Integer.parseInt(status));
		try {
			int result=administrator.applyLeave(l);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		return new ModelAndView("administrator","adddocresult","leave applied successfully for the period "+fromdate+" - "+todate+".");
	}

	@RequestMapping(value="getappointments")
	public ModelAndView getDoctorsToGetTheirAppointmentsForDay() {
		try {
			return new ModelAndView("patient","doctors",administrator.viewAllDoctors());
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
	}
	
	@RequestMapping(value="intimate")
	public ModelAndView getDoctorsOnLeaveButHaveAppointment() {
		List<Appointment> report = null;
		try {
			report = administrator.getIntiamteReport();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(report.size()==0)
			return new ModelAndView("administrator","adddocresult","unable to find doctors who are on leave and have an an appointment.");
		return new ModelAndView("administrator","reports",report);
	}
	
	@RequestMapping(value="updatedoctor")
	public ModelAndView updateToNewDoctor(@RequestParam("date")String date,@RequestParam("did")String did,
			@RequestParam("ailment")String ailment,@RequestParam("slot")String slot,@RequestParam("aid")String aid) {
		System.out.println("doctor id: "+did+", specilization: "+ailment+",appointment date: "+date);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udate = null;
		try {
			udate = sdf1.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		Date sqlStartDate = new Date(udate.getTime());
		System.out.println("finding an update for appointment "+aid+" having doctor of id: "+did);
		Doctor result = null;
		try {
			result = administrator.updateAndReplaceDoctorOnLeave(sqlStartDate, ailment, Integer.parseInt(did),slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		System.out.println("replacement doctor: "+result);
		ModelAndView mv=new ModelAndView();
		
		if(result.getDoctorId()==Integer.valueOf(did)){
			//mv.addObject("suggestionmsg","could not suggest another doctor");
			return new ModelAndView("administrator","adddocresult","could not suggest another doctor");
		}
		mv.addObject("oldid", did);
		List<Doctor> suggestions=new ArrayList<>();
		suggestions.add(result);
		mv.addObject("suggestion",suggestions);
		mv.addObject("appointmentid", aid);
		mv.setViewName("administrator");
		return mv;
	}
	
	@RequestMapping(value="updatesuggestion",method=RequestMethod.POST)
	public ModelAndView updateSuggestion(@RequestParam("oldid")String oldid,@RequestParam("newid")String newid,@RequestParam("aid")String aid){
		System.out.println("oldid: "+oldid+" new id:"+newid+" appointment: "+aid);
		String result = null;
		try {
			result = administrator.updateDoctorOnLeave(Integer.parseInt(oldid), Integer.parseInt(newid), Integer.parseInt(aid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(result.equals("0"))
			return new ModelAndView("administrator","adddocresult","unable to update doctor at this time.");
		return new ModelAndView("administrator","adddocresult","doctor updated successfully.");
	}
}
