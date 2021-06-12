/**
 * 
 */
package my.cryofoton.empsalary.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Osman Sulaiman
 *
 */
@Entity
public class Employees {

	@Id
	private String name;
	
	private Integer monthlySalary;

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
	 * @return the monthlySalary
	 */
	public Integer getMonthlySalary() {
		return monthlySalary;
	}

	/**
	 * @param monthlySalary the monthlySalary to set
	 */
	public void setMonthlySalary(Integer monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
}
