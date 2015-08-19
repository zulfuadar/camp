package tr.org.lkd.lyk2015.camp.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course extends AbstractBaseModel {
	
	
	private String name;
	private String description;
	private String prerequisites;
	private String detailPagelink;
	
	private Boolean active;	
	
	@ManyToMany(mappedBy="courses")
	private Set<Instructor> instructors;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getDetailPagelink() {
		return detailPagelink;
	}

	public void setDetailPagelink(String detailPagelink) {
		this.detailPagelink = detailPagelink;
	}

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	

}
