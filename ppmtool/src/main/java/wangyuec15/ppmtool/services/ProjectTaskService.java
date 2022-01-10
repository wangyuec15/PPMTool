package wangyuec15.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wangyuec15.ppmtool.domain.Backlog;
import wangyuec15.ppmtool.domain.Project;
import wangyuec15.ppmtool.domain.ProjectTask;
import wangyuec15.ppmtool.exceptions.ProjectNotFoundException;
import wangyuec15.ppmtool.repositories.BacklogRepository;
import wangyuec15.ppmtool.repositories.ProjectRepository;
import wangyuec15.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		try {
	//		PTs to be added to a specific project, project != null, BL exists
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			
	//		set the bl to pt
			projectTask.setBacklog(backlog);
	//		we want project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
			Integer BacklogSequence = backlog.getPTSequence();
	//		Update the BL SEQUENCE
			BacklogSequence++;
			backlog.setPTSequence(BacklogSequence);
	//		Add Sequence to Project Task
			projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			
	//		Initial priority when priority is null
			if(projectTask.getPriority() == null || projectTask.getPriority() == 0) {
				projectTask.setPriority(3);
			}
	//		Initial status when status is null
			if(projectTask.getStatus() == null || projectTask.getStatus() == "") {
				projectTask.setStatus("TO_DO");
			}
	
			return projectTaskRepository.save(projectTask);
		}catch(Exception e) {
			throw new ProjectNotFoundException("Project not Found");
		}
		
	}
	
	public Iterable<ProjectTask> findBacklogById(String id) {
		
		Project project = projectRepository.findByProjectIdentifier(id.toUpperCase());
		if(project==null) {
			throw new ProjectNotFoundException("Project with ID: '"+id.toUpperCase()+"' does not exist");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}

	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
//		make sure we are searching on the right backlog
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist");
		}
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project Task: '"+pt_id+"' does not exist");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task: '"+pt_id+"' does not exist in project '"+backlog_id+"'");
		}
		
		return projectTask;
	}
}
