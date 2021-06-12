/**
 * 
 */
package my.cryofoton.empsalary.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.cryofoton.empsalary.dto.EmployeeDto;
import my.cryofoton.empsalary.service.SalaryService;

/**
 * @author Osman Sulaiman
 *
 */
@RestController
public class SalaryController {

	private SalaryService salaryService;
	
	/**
	 * @param salaryService
	 */
	public SalaryController(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	@GetMapping("/")
	public EmployeeDto getSalary(
			@RequestParam(value = "name") String name) {
		
		//TODO validate requested name
		return salaryService.getEmployeeSalary(name);
	}
	
	@PutMapping("/")
	public EmployeeDto updateSalary(@RequestBody EmployeeDto empDto) {
		
		//TODO validate salary info
		//TODO check for proper response code
		return salaryService.updateEmployeeSalary(empDto);
	}
}
