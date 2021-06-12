/**
 * 
 */
package my.cryofoton.empsalary.service;

import org.springframework.stereotype.Service;

import my.cryofoton.empsalary.dto.EmployeeDto;

/**
 * @author Osman Sulaiman
 *
 */
@Service
public class SalaryService {

	public EmployeeDto getEmployeeSalary(String name) {
		return new EmployeeDto("johnny", 12000000, 1570000);
	}
}
