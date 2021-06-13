/**
 * 
 */
package my.cryofoton.empsalary.exception;

/**
 * @author Osman Sulaiman
 *
 */
public class InvalidRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidRequestException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidRequestException(String message) {
		super(message);
	}

}
