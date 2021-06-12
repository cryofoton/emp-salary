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
				.map(e -> new EmployeeDto(e.getName(), null, null))
				.orElse(null);
		// return new EmployeeDto("johnny", 12000000, 1570000);
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
