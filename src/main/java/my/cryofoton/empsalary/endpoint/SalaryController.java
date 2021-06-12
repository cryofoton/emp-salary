/**
 * 
 */
package my.cryofoton.empsalary.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public EmployeeDto getSalary(
			@RequestParam(value = "name") String name) {
		
		//TODO validate requested name
		return salaryService.getEmployeeSalary(name);
	}
}
