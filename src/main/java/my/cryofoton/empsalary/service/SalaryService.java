/**
 * 
 */
package my.cryofoton.empsalary.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import my.cryofoton.empsalary.dao.repository.EmployeesRepository;
import my.cryofoton.empsalary.dto.EmployeeDto;
import my.cryofoton.empsalary.exception.RecordNotFoundException;

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

	public EmployeeDto getEmployeeSalary(String name) throws RecordNotFoundException {
		Objects.requireNonNull(name);
		
		// throw exception if employee name not found in db
		return employeesRepo.findById(name)
				.map(e -> {
					BigDecimal yrSalary = calcYearlySalary(e.getMonthlySalary());
					return new EmployeeDto(e.getName(), 
							convertToSen(yrSalary), 
							convertToSen(calcTaxPayable(yrSalary)));
				})
				.orElseThrow(() -> new RecordNotFoundException("Employee not found"));
	}
	
	private BigDecimal calcYearlySalary(Integer salary) {
		return Optional.ofNullable(salary)
				.map(s -> s * 12)
				.map(y -> new BigDecimal(y))
				.orElse(null);
	}
	
	private BigDecimal calcTaxPayable(BigDecimal yrSalary) {
		if (yrSalary == null) {
			return null;
		}
		
		if (yrSalary.compareTo(BigDecimal.valueOf(5001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(20000)) <= 0) {
			return calcTaxPayable(yrSalary, 5000, 0, 1.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(20001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(35000)) <= 0) {
			return calcTaxPayable(yrSalary, 20000, 150, 3.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(35001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(50000)) <= 0) {
			return calcTaxPayable(yrSalary, 35000, 600, 8.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(50001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(70000)) <= 0) {
			return calcTaxPayable(yrSalary, 50000, 1800, 14.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(70001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(100000)) <= 0) {
			return calcTaxPayable(yrSalary, 70000, 4600, 21.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(100001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(250000)) <= 0) {
			return calcTaxPayable(yrSalary, 100000, 10900, 24.0);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(250001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(400000)) <= 0) {
			return calcTaxPayable(yrSalary, 250000, 46900, 24.5);
			
		} else if (yrSalary.compareTo(BigDecimal.valueOf(400001)) >= 0 
				&& yrSalary.compareTo(BigDecimal.valueOf(600000)) <= 0) {
			return calcTaxPayable(yrSalary, 400000, 83650, 25.0);
		}
		
		return BigDecimal.ZERO;
	}
	
	private BigDecimal calcTaxPayable(BigDecimal yrSalary, Integer firstAmt, 
			Integer firstTaxAmt, Double taxRate) {
		
		BigDecimal balance = new BigDecimal(yrSalary.toString());
		BigDecimal tax = BigDecimal.ZERO;
		
		// process for first tier
		balance = balance.subtract(BigDecimal.valueOf(firstAmt));
		tax = tax.add(BigDecimal.valueOf(firstTaxAmt));
		
		// process for next tier
		BigDecimal nextTax = balance
				.multiply(BigDecimal.valueOf(taxRate))
				.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
		tax = tax.add(nextTax);
		
		return tax;
	}
	
	private Integer convertToSen(BigDecimal value) {
		return Optional.ofNullable(value)
				.map(v -> v.multiply(BigDecimal.valueOf(100)))
				.map(v -> v.intValue())
				.orElse(null);
	}
	
	public EmployeeDto updateEmployeeSalary(EmployeeDto emp) throws RecordNotFoundException {
		Objects.requireNonNull(emp);
		
		// throw exception if employee name not found in db
		return employeesRepo.findById(emp.getName())
				.map(e -> {
					e.setMonthlySalary(emp.getSalary());
					return employeesRepo.save(e);
					})
				.map(e -> new EmployeeDto(e.getName(), e.getMonthlySalary(), null))
				.orElseThrow(() -> new RecordNotFoundException("Employee not found"));
	}
}
