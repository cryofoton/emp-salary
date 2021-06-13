/**
 * 
 */
package my.cryofoton.empsalary.endpoint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import my.cryofoton.empsalary.dto.EmployeeDto;
import my.cryofoton.empsalary.exception.InvalidRequestException;
import my.cryofoton.empsalary.exception.RecordNotFoundException;
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
	ResponseEntity<EmployeeDto> getSalary(
			@RequestParam(value = "name") String name) {
		try {
			// validate requested name
			if (validateName(name)) {
				return ResponseEntity.ok(salaryService.getEmployeeSalary(name));
			} else {
				throw new InvalidRequestException("Not a valid name");
			}
		} catch (RecordNotFoundException | InvalidRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PutMapping("/")
	ResponseEntity<EmployeeDto> updateSalary(@RequestBody EmployeeDto empDto) {
		try {
			// validate salary info
			if (validateSalary(empDto.getSalary())) {
				return ResponseEntity.ok(salaryService.updateEmployeeSalary(empDto));
			} else {
				throw new InvalidRequestException("Not a valid salary");
			}
		} catch (InvalidRequestException | RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	private boolean validateName(String name) {
		if (name == null || name.strip().length() < 3) {
			return false;
		}
		
		Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	private boolean validateSalary(Integer salary) {
		if (salary == null) {
			return false;
		}
		
		return salary >= 0 && salary <= 100000;
	}
}
