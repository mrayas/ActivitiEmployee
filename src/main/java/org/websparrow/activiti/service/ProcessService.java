package org.websparrow.activiti.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.websparrow.activiti.model.Employee;
import org.websparrow.activiti.repository.EmployeeRepository;

@Service
public class ProcessService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	// start the process and set employee as variable
	public String startTheProcess(String assignee) {

		Employee employee = employeeRepository.findByName(assignee);

		Map<String, Object> variables = new HashMap<>();
		variables.put("employee", employee);

		runtimeService.startProcessInstanceByKey("simple-process", variables);
		
		System.out.println("Star process with: "+employee.getName());

		return processInformation();
	}

	// fetching process and task information
	public String processInformation() {

		List<Task> taskList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();

		StringBuilder processAndTaskInfo = new StringBuilder();
		
		processAndTaskInfo.append("Number of process definition available: "
				+ repositoryService.createProcessDefinitionQuery().count() + " | Task Details= ");

		taskList.forEach(task -> {

			processAndTaskInfo.append("ID: " + task.getId() + ", Name: " + task.getName() + ", Assignee: "
					+ task.getAssignee() + ", Description: " + task.getDescription());
			System.out.println("Process information task id: "+Integer.parseInt(task.getId()) +"["+task.getName()+"]");
		});

		
		return processAndTaskInfo.toString();
	}

	// fetch task assigned to employee
	public List<Task> getTasks(String assignee) {
		System.out.println("Get task: "+assignee);
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		for (int i=0;i<tasks.size();i++) {
		      
		      System.out.println(tasks.get(i));
		}

		return tasks;
	}

	// complete the task
	public void completeTask(String taskId) {
		System.out.println("Complete task Id: "+taskId);
		taskService.complete(taskId);
	}
}
