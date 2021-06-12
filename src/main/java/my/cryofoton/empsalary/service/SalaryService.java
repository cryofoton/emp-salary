/**
 * 
 */
package my.cryofoton.empsalary.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import my.cryofoton.empsalary.dao.repository.EmployeesRepository;
import my.cryofoton.empsalary.dto.EmployeeDto;

/**
 * @author Osman Sulaiman
 *
 */
@Service
public class SalaryService {

	private EmployeesRepository employeesRepo;
	
	/**
	 * @param employeesRepo
	 */
	public SalaryService(EmployeesRepository employeesRepo) {
		this.employeesRepo = employeesRepo;
	}

	public EmployeeDto getEmployeeSalary(String name) {
		Objects.requireNonNull(name);
		
		//TODO throw exception if employee name not found in db
		return employeesRepo.findById(name)
				.map(e -> new EmployeeDto(e.getName(), 
						calcYearlySalary(e.getMonthlySalary()), null))
				.orElse(null);
	}
	
	/**
	 * This method calculates yearly salary from monthly salary.
	 * 
	 * @param monthly salary in RM
	 * @return yearly salary in sen
	 */
	private Integer calcYearlySalary(Integer salary) {
		if (salary == null) {
			return null;
		}
		
		return salary * 12 * 100;
	}
	
	public EmployeeDto updateEmployeeSalary(EmployeeDto emp) {
		Objects.requireNonNull(emp);
		
		//TODO throw exception if employee name not found in db
		return employeesRepo.findById(emp.getName())
				.map(e -> {
					e.setMonthlySalary(emp.getSalary());
					return employeesRepo.save(e);
					})
				.map(e -> new EmployeeDto(e.getName(), e.getMonthlySalary(), null))
				.get();
	}
}
