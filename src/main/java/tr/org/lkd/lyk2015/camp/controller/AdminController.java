package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.services.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(@ModelAttribute Admin admin){
	
		return "/admin/adminForm";
	
	}

	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String sendForm(@ModelAttribute Admin admin){
		
		adminService.create(admin);
		
		
		return "redirect:/admin";
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listForm(Model model){
	
		model.addAttribute("adminList", adminService.getAll());
		return "/admin/adminList";
	
	}
	@RequestMapping("/edit/{id}")
	public String editForm(@PathVariable(value = "id") Long id,Model model) {

		Admin adminNew = adminService.getById(id);

		model.addAttribute("admin", adminNew);


		return "admin/adminEdit";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String adminUpdateForm(@ModelAttribute Admin admin,Model model) {

		adminService.update(admin);
		model.addAttribute("message","success");
		
		return "redirect:/admin";

	}

}
