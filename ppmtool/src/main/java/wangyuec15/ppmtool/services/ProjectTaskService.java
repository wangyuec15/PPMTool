package wangyuec15.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wangyuec15.ppmtool.domain.Backlog;
import wangyuec15.ppmtool.domain.ProjectTask;
import wangyuec15.ppmtool.repositories.BacklogRepository;
import wangyuec15.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		
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
		
	}
}
