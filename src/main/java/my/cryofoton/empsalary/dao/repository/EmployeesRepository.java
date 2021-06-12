/**
 * 
 */
package my.cryofoton.empsalary.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.cryofoton.empsalary.dao.model.Employees;

/**
 * @author Osman Sulaiman
 *
 */
public interface EmployeesRepository extends JpaRepository<Employees, String> {

}
