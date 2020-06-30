package sg.edu.iss.sa50.t8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.sa50.t8.model.AnnualLeave;
import sg.edu.iss.sa50.t8.model.CompensationLeave;
import sg.edu.iss.sa50.t8.model.MedicalLeave;
import sg.edu.iss.sa50.t8.service.ILeaveService;
import sg.edu.iss.sa50.t8.service.LeaveServiceImpl;

//split to architecture design controller
//need to discuss to shift methods to respective controllers
@Controller
@RequestMapping("/leaves")
public class LeaveController {

	@Autowired
	protected ILeaveService leaveService;
	
	@Autowired
	public void setILeaveService(LeaveServiceImpl leaveSerImpl) {
		this.leaveService = leaveSerImpl;
	}

	@RequestMapping("/apply")
	public String apply() {
		return "leaves-apply";
	}

	@RequestMapping("/annualAdd")
	public String annualaddForm(Model model) {
		model.addAttribute("annualLeave", new AnnualLeave());
		return "leaves-apply-annual";
	}

	@RequestMapping("/medicalAdd")
	public String medicaladdForm(Model model) {
		model.addAttribute("medicalLeave", new MedicalLeave());
		return "leaves-apply-medical";
	}

	@RequestMapping("/compensationAdd")
	public String compensationaddForm(Model model) {
		model.addAttribute("compensationLeave", new CompensationLeave());
		return "leaves-apply-compensation";
	}

	
	@RequestMapping("/annual/save")
	public String saveAnnualForm(@ModelAttribute("annualLeave") AnnualLeave annualLeave, 
			Model model) {
		leaveService.saveAnnualLeave(annualLeave);
		model.addAttribute("Leaves", leaveService.findAllLeaves());
		return "leaves-history";
	}
	
	@RequestMapping("/medical/save")
	public String saveMedicalForm(@ModelAttribute("medicalLeave") MedicalLeave medicalLeave, 
			Model model) {
		leaveService.saveMedicalLeave(medicalLeave);
		model.addAttribute("Leaves", leaveService.findAllLeaves());
		return "leaves-history";
	}
	
	@RequestMapping("/compensation/save")
	public String saveCompensationForm(@ModelAttribute("compLeave") CompensationLeave compLeave, 
			Model model) {
		leaveService.saveCompensationLeave(compLeave);
		model.addAttribute("Leaves", leaveService.findAllLeaves());
		return "leaves-history";
	}
	
	@RequestMapping("/history")
	public String History(Model model) {
		model.addAttribute("Leaves", leaveService.findAllLeaves());
		return "leaves-history";
	}
	
}
