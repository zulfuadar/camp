package tr.org.lkd.lyk2015.camp.controller.valid;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.services.BlackListValidationService;
import tr.org.lkd.lyk2015.camp.services.ExamValidationService;
import tr.org.lkd.lyk2015.camp.services.TcknValidationService;

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	private TcknValidationService tcknValidationService;

	@Autowired
	private BlackListValidationService blackListValidationService;

	@Autowired
	private ExamValidationService examListValidationService;

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(ApplicationFormDto.class);

	}

	@Override
	public void validate(Object target, Errors errors) {

		ApplicationFormDto application = (ApplicationFormDto) target;

		System.out.println("=========================validation===================");
		System.out.println(application);

		if (application.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& application.getApplication().getWorkStatus() == WorkStatus.WORKING) {

			errors.rejectValue("workStatus", "error.notWorkingOfficer", "Nasil Memursun");

		}

		application.getPreferredCourseIds().removeAll(Collections.singleton(null)); // null
																					// elemanlari
																					// cikariyor

		// Check course selection size
		if (application.getPreferredCourseIds().size() == 0) {

			errors.rejectValue("preferredCourseIds", "error.preferredCourseNoSelection", "En az bir kurs secmelisiniz");

		}

		int listSize = application.getPreferredCourseIds().size();
		Set<Long> set = new HashSet<>();
		set.addAll(application.getPreferredCourseIds());
		int setSize = set.size();

		if (listSize != setSize) {

			errors.rejectValue("preferredCourseIds", "error.preferredCourseSame",
					"Ayni kursu birden cok kez secemezsin");

		}

		// Validate tckn from web service
		Student student = application.getStudent();
		boolean tcknValid = tcknValidationService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());

		if (!tcknValid) {

			errors.rejectValue("student.tckn", "error.tcknInvalid", "TC Kimlik no hatali");

		}

		boolean blacklist = blackListValidationService.emailControl(student.getTckn(), student.getEmail(),
				student.getName(), student.getSurname());

		if (!blacklist) {
			errors.rejectValue("student.email", "error.emailInvalid", "E-mail hatali");
		}

		boolean examlist = examListValidationService.validate(student.getTckn(), student.getEmail());

		if (examlist) {
			errors.rejectValue("student.email", "error.emailInvalid", "Sýnavý geçememiþsin gardaþþþ");
		}

	}

}
