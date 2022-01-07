package wangyuec15.ppmtool.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String projectName;
	private String projectIdentifier;
	private String description;
	private Date start_date;
	private Date end_date;
	
	private Date created_At;
	private Date updated_At;
	
	public Project() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier; 
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description; 
	}
	
	public Date getStartDate() {
		return start_date;
	}
	
	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEndDate() {
		return end_date;
	}
	
	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}
	
	public Date getCreatedAt() {
		return created_At;
	}
	
	public void setCreatedAt(Date created_At) {
		this.created_At = created_At;
	}
	
	public Date getUpdatedAt() {
		return updated_At;
	}
	
	public void setUpdatedAt(Date updated_At) {
		this.updated_At = updated_At;
	}
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
		
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}
}
