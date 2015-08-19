package tr.org.lkd.lyk2015.camp.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Application;

@Repository
public class ApplicationDao extends GenericDao<Application> {

	public Application getByIdWithCourses(Long id) {
		Criteria c = createCriteria();
		c.add(Restrictions.idEq(id));
		c.setFetchMode("courses", FetchMode.JOIN);

		return (Application) c.uniqueResult();
	}
}
