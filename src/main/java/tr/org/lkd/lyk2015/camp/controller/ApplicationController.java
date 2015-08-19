package tr.org.lkd.lyk2015.camp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.controller.valid.ApplicationFormValidator;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.services.ApplicationService;
import tr.org.lkd.lyk2015.camp.services.CourseService;

@Controller
@RequestMapping("/basvuru")
public class ApplicationController {

	@Autowired
	CourseService courseService;

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@Autowired
	private ApplicationService applicationService;

	@InitBinder
	private void initBinder(final WebDataBinder webDataBinder) {
		webDataBinder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String applicationFrom(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model) {

		model.addAttribute("courses", courseService.getAll());

		return "application/applicationForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String applicationFormCreate(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model) {

		if (false) {
			model.addAttribute("courses", this.applicationService.getAll());
		}

		else {
			applicationService.create(applicationFormDto);
		}
		return "application/applicationForm";
	}

}
