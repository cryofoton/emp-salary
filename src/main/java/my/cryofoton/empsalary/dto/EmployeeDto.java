/**
 * 
 */
package my.cryofoton.empsalary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Osman Sulaiman
 *
 */
public class EmployeeDto {

	private String name;
	
	private Integer salary;
	
	@JsonInclude(Include.NON_NULL)
	private Integer tax_payable;
	
	/**
	 * 
	 */
	public EmployeeDto() {
	}

	/**
	 * @param name
	 * @param salary
	 * @param tax_payable
	 */
	public EmployeeDto(String name, Integer salary, Integer tax_payable) {
		this.name = name;
		this.salary = salary;
		this.tax_payable = tax_payable;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}
	
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	/**
	 * @return the tax_payable
	 */
	public Integer getTax_payable() {
		return tax_payable;
	}
	
	/**
	 * @param tax_payable the tax_payable to set
	 */
	public void setTax_payable(Integer tax_payable) {
		this.tax_payable = tax_payable;
	}
	
}
