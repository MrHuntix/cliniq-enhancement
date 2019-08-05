package com.training.ocs.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.training.ocs.bean.AppointmentBean;
import com.training.ocs.bean.CredentialsBean;
import com.training.ocs.bean.DoctorBean;
import com.training.ocs.exception.CliniqueException;
import com.training.ocs.service.reporter.Reporter;

@Controller
public class ReporterController {
	
	@Autowired
	Reporter reporter;
	
	@RequestMapping("reporterhome")
	public ModelAndView reporterHome(HttpSession session) {
		CredentialsBean sessionbean=(CredentialsBean) session.getAttribute("profile");
		System.out.println("current session: "+sessionbean);
		if(sessionbean==null)
			return new ModelAndView("index");
		return new ModelAndView("reporter");
	}
	
	@RequestMapping("genreport")
	public ModelAndView showReport(){
		List<AppointmentBean> appointments = null;
		try {
			appointments = reporter.intimateAdmin();
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(appointments.size()==0)
			return new ModelAndView("reporter","reportermsg","unable to find doctors who are on leave and have an an appointment.");
		return new ModelAndView("reporter","appointments",appointments);
	}
	
	@RequestMapping(value="getavaildoctors",method=RequestMethod.POST)
	public ModelAndView showDoctors(@RequestParam("cdate")Date selecteddate,@RequestParam("slot")String slot){
		System.out.println("selected date: "+selecteddate+", slot: "+slot);
		List<DoctorBean> doctors = null;
		try {
			doctors = reporter.getAvailableDoctors(selecteddate, slot);
		} catch (CliniqueException e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error","errormsg",e.getMessage());
		}
		if(doctors.size()==0)
			return new ModelAndView("reporter","reportermsg","no doctors are available on "+selecteddate);
		return new ModelAndView("reporter","availdoctors",doctors);
	}
}
