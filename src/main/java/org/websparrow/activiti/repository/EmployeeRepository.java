package org.websparrow.activiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.websparrow.activiti.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByName(String name);

}