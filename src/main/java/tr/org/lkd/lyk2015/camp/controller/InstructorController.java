package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.services.CourseService;
import tr.org.lkd.lyk2015.camp.services.InstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInstructorForm(@ModelAttribute Instructor instructor, Model model) {

		model.addAttribute("courses", courseService.getAll());
		return "instructor/instructorForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String sendForm(@ModelAttribute Instructor instructor,@RequestParam("courseIds")List<Long> courseIds) {

		instructorService.create(instructor,courseIds);

		return "redirect:/instructor";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listForm(Model model) {

		model.addAttribute("instructorList", instructorService.getAll());
		return "/instructor/instructorList";

	}

	@RequestMapping("/edit/{id}")
	public String editForm(@PathVariable(value = "id") Long id, Model model) {

	   Instructor instructorNew = instructorService.getInstructorWithCourses(id);
	   model.addAttribute("courses", courseService.getAll());
       model.addAttribute("instructor", instructorNew);

		return "instructor/instructorEdit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String instructorUpdateForm(@ModelAttribute Instructor instructor, Model model) {

		instructorService.update(instructor);
		model.addAttribute("message", "success");

		return "redirect:/instructor";

	}
}
