package tr.org.lkd.lyk2015.camp.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.repository.CourseDao;

@Service
@Transactional
public class CourseService extends GenericService<Course> {

	@Autowired
	private CourseDao courseDao;
}
