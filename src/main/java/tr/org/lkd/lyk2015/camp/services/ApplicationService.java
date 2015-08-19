package tr.org.lkd.lyk2015.camp.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.repository.ApplicationDao;
import tr.org.lkd.lyk2015.camp.repository.CourseDao;
import tr.org.lkd.lyk2015.camp.repository.StudentDao;

@Service
@Transactional
public class ApplicationService extends GenericService<Application> {

	@Autowired
	private EmailService emailService;
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private StudentDao studentDao;

	private static String URL_BASE = "http://localhost:8080/camp/applications/validate/";

	public void create(ApplicationFormDto applicationFormDto) {

		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();
		List<Long> courseIds = applicationFormDto.getPreferredCourseIds();

		// Generate email verification url
		String url = URL_BASE + UUID.randomUUID().toString();

		emailService.sendMail(student.getEmail(), "konu", url);

		application.setValidationId(url);

		// Add preferred courses to Application entity
		List<Course> courses = courseDao.getByIds(courseIds);
		application.getPreferredCourses().addAll(courses);

		// Check if user exists
		Student studentFromDb = studentDao.getUserByTckn(student.getTckn());
		if (studentFromDb == null) {
			studentDao.create(student);
			studentFromDb = student;
		}
		// Set application's user
		application.setOwner(studentFromDb);

		applicationDao.create(application);

	}

}
