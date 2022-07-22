package org.websparrow.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.websparrow.activiti.service.EmployeeService;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) 
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("http://localhost:8080/process?assignee=Mario");
		System.out.println("http://localhost:8080/tasks?assignee=Mario");
		System.out.println("http://localhost:8080/completetask?taskId=9");
		System.out.println("http://localhost:8080/tasks?assignee=Mario");
		System.out.println("http://localhost:8080/completetask?taskId=12");
	}

	@Bean
	public CommandLineRunner init(final EmployeeService employeeService) {

		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				employeeService.createEmployee();
			}
		};
	}

	/*
	@Bean
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("user");
				group.setName("users");
				group.setType("security-role");
				identityService.saveGroup(group);

				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				identityService.saveUser(admin);
			}
		};
	}
	*/
}
