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
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.services.AdminService;
import tr.org.lkd.lyk2015.camp.services.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String courseCreateForm(@ModelAttribute Course course){
	
		return "course/courseForm";
	
	}

	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String sendForm(@ModelAttribute Course course){
		
		courseService.create(course);
		
		
		return "redirect:/course";
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public String courseForm(Model model){
	
		model.addAttribute("courseList", courseService.getAll());
		return "/course/courseList";
	
	}

	@RequestMapping("/edit/{id}")
	public String courseEditForm(@PathVariable(value="id") Long id,Model model){
	
		Course courseNew= courseService.getById(id);
		model.addAttribute("course",courseNew);
		
		return "/course/courseEdit";
	
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
  public String courseUpdate(@ModelAttribute Course course){
		courseService.update(course);
		
		return "redirect:/course";
	}

	
}
